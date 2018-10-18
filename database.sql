------employee table-----
create table employeetm(employeeName varchar(20), employeeId varchar(20), empPassword varchar(20));
insert into employeetm values ('Ramesh','101','abc123');
insert into employeetm values ('Suresh','102','def456');
insert into employeetm values ('Ganesh','103','ghi789');
select * from employeetm;



-----Customer table-------
create table customerstm(customerId number,customerName varchar(20), mobileNumber varchar(20));
alter table customerstm add constraint customerstmId_pk primary key(customerId);
alter table customerstm add constraint customerName_uc unique(customerName);
alter table customerstm add constraint customerNumber_uc unique(mobileNumber);

select * from customerstm;


------Vehicle table----------
create table cartm(customerId number, vehicleNo varchar(20));
alter table cartm add constraint vehicletmId_pk primary key(vehicleNo);
alter table cartm add constraint customertmId_fk foreign key(customerId) references customerstm(customerId);
alter table cartm add constraint customertmId_fk foreign key(customerId) references customerstm(customerId) on delete cascade;
select * from cartm;


------Vehicle Services table---------
create table vehicleServicestm(serviceId number, vehicleNo varchar(20),serviceName varchar(20));
alter table vehicleServicestm add constraint serviceId_pk primary key(serviceId);
alter table vehicleServicestm add constraint vehicleNo_fk foreign key(vehicleNo) references cartm(vehicleNo) on delete cascade;
alter table VehicleServicestm add constraint serviceName_fk foreign key(serviceName) references servicetm(serviceName) on delete cascade;
select * from vehicleServicestm;


------Services table---------------
create table servicetm(serviceName varchar(20), servicePrice number);
alter table servicetm add constraint serviceName_pk primary key(serviceName);

insert into servicetm values ('polishing',2000);
insert into servicetm values ('wheelBalancing',3000);
insert into servicetm values ('decors',1000);

select * from servicetm;

-------Insurance table--------------
create table insurancetm(vehicleNo varchar(20),service varchar(30),insuranceAmount Number);
insert into insurancetm values('ab123','polishing',1000);
insert into insurancetm values('ab123','wheelBalancing',1000);
insert into insurancetm values('ab123','decors',1000);

insert into insurancetm values('cd456','polishing',500);
insert into insurancetm values('cd456','wheelBalancing',2000);
insert into insurancetm values('cd456','decors',200);

insert into insurancetm values('qwert123','polishing',1000);
insert into insurancetm values('qwert123','wheelBalancing',1500);
insert into insurancetm values('qwert123','decors',1200);

alter table insurancetm add constraint insurancetm_pk primary key(vehicleNo,service);

select * from insurancetm;

  

--------------employeeId sequence-----------
create sequence seqm_empid
minvalue 1
start with 1
increment by 1;
cache 100;

-------------customer Id sequence-----------
create sequence seqtm_customerId
minvalue 1
start with 1
increment by 1
cache 100;
------------serviceId sequence------------
create sequence seqtm_serviceId
minvalue 1
start with 1
increment by 1
cache 100;



commit;


-------------------------------------------
alter table cartm drop constraint customertmId_fk;
alter table vehicleServicestm drop constraint serviceName_fk;
alter table vehicleServicestm drop constraint vehicleNo_fk;

select * from customerstm;
customerId	customerName	mobileNumber
17			Ramesh			2345678901
13			Suresh			5678901234

select * from cartm;
customerId   vehicleNo
17				qwert123
17				qwe123
13				sur12

select * from vehicleServicestm;
serviceId	vehicleNo		serviceName
26			qwe123			polishing
27			qwe123			decors
22			sur12			wheelBalancing
23			sur12			decors





































