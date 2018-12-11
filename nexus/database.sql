use trynexus;

drop table if exists staff;
drop table if exists jobseeker;
drop table if exists `character`;
drop table if exists jscareer;
drop table if exists jscharacter;
drop table if exists kyujin;
drop table if exists pastkyujin;
drop table if exists hwkyujin;
drop table if exists todouhuken;
drop table if exists jobcategory;
drop table if exists job;
drop table if exists matchingcase;
drop table if exists saiban;


create table staff(
id char(4) not null,
name  varchar(100) not null,
kana varchar(100),
authority char(1) not null,
password varchar(50) not null,
createdt timestamp not null default current_timestamp,
createuserid char(4) not null,
updatedt timestamp not null default current_timestamp,
updateuserid char(4) not null,
deleteflag char(4) not null,
primary key(id)
) charset =utf8;

create table jobseeker(
id varchar(8) not null,
name varchar(100) not null,
kana varchar(100),
birthdt date,
sex char(1),
age int,
postal char(8),
address varchar(50),
nearstation varchar(50),
phone char(20),
mobile char(20),
partner char(1),
huyou int,
education varchar(100),
hopejob1 char(6),
hopejob2 char(6),
hopejob3 char(6),
hopejobcategory char(3),
hopeworkplace varchar(100),
hopekoyoukeitai char(1),
hopeworkingdate int,
hopebegintime int,
hopeendtime int,
hopesalary int,
hopejikyu int,
hopeetc varchar(200),
driverlicense char(1),
licenseetc varchar(500),
pasokonskill varchar(200),
caution varchar(200),
tantoustaffid char(4),
password varchar(50),
createdt timestamp not null default current_timestamp,
createuserid char(4) not null,
updatedt timestamp not null default current_timestamp,
updateuserid char(4) not null,
deleteflag char(1) not null,
primary key(id)
)charset =utf8;

create table `character`(
id int auto_increment not null,
`character` varchar(20) not null,
primary key(id)
)charset =utf8;

create table jscharacter(
jobseekerid char(8) not null,
characterid int not null,
primary key(jobseekerid)
)charset =utf8;

create table jscareer(
jobseekerid char(8) not null,
career varchar(200) not null,
nyushokudt int,
taishokudt int,
primary key(jobseekerid)
)charset =utf8;


create table kyujin(
no char(14) not null, 
receptiondt date, 
perioddt date, 
companyno char(13), 
addresscd char(2),
jobsmallcd1 char(3), 
jobsmallcd2 char(3), 
jobsmallcd3 char(3), 
joblargecd1 char(1), 
joblargecd2 char(1), 
joblargecd3 char(1), 
jobcategorysmallcd char(3), 
jobcategorylargecd char(1),  
companykana varchar(54), 
companyname varchar(60), 
companypostal char(8), 
companyplace varchar(75), 
companyurl varchar(100), 
postal char(8), 
address varchar(90), 
nearstation varchar(30), 
job varchar(28), 
hakencd char(1), 
detail varchar(297), 
koyoukeitaicd char(1), 
koyoukikan char(9), 
koyoukikankaishi date, 
koyoukikanowari date, 
education varchar(64), 
experience varchar(84), 
license varchar(84), 
agemin int, 
agemax int, 
salarymin int, 
salarymax int, 
salaryformcd varchar(1), 
begintime int, 
endtime int, 
establishdt int(4), 
capital long, 
companyfeature varchar(90), 
tantouyakushoku varchar(28), 
tantoukana varchar(28), 
tantou varchar(14), 
tantoustaff_id char(4), 
applicationform varchar(500), 
background varchar(1000), 
hiddensex char(1), 
hiddenagemin int, 
hiddenagemax int, 
hiddenetc varchar(1000), 
createdt timestamp not null default current_timestamp, 
createuserid char(4) not null, 
updatedt timestamp not null default current_timestamp, 
updateuserid char(4) not null, 
deleteflag char(1) not null, 
primary key(no)
)charset =utf8;

create table pastkyujin(
no char(14) not null, 
receptiondt date, 
perioddt date, 
companyno char(13), 
addresscd char(2),
jobsmallcd1 char(3), 
jobsmallcd2 char(3), 
jobsmallcd3 char(3), 
joblargecd1 char(1), 
joblargecd2 char(1), 
joblargecd3 char(1), 
jobcategorysmallcd char(3), 
jobcategorylargecd char(1),  
companykana varchar(54), 
companyname varchar(60), 
companypostal char(8), 
companyplace varchar(75), 
companyurl varchar(100), 
postal char(8), 
address varchar(90), 
nearstation varchar(30), 
job varchar(28), 
hakencd char(1), 
detail varchar(297), 
koyoukeitaicd char(1), 
koyoukikan char(9), 
koyoukikankaishi date, 
koyoukikanowari date, 
education varchar(64), 
experience varchar(84), 
license varchar(84), 
agemin int, 
agemax int, 
salarymin int, 
salarymax int, 
salaryformcd varchar(1), 
begintime int, 
endtime int, 
establishdt int(4), 
capital long, 
companyfeature varchar(90), 
tantouyakushoku varchar(28), 
tantoukana varchar(28), 
tantou varchar(14), 
tantoustaff_id char(4), 
applicationform varchar(500), 
background varchar(1000), 
hiddensex char(1), 
hiddenagemin int, 
hiddenagemax int, 
hiddenetc varchar(1000), 
createdt timestamp not null default current_timestamp, 
createuserid char(4) not null, 
updatedt timestamp not null default current_timestamp, 
updateuserid char(4) not null, 
deleteflag char(1) not null, 
primary key(no)
)charset =utf8;

create table hwkyujin(
no char(14) not null, 
receptiondt date, 
perioddt date, 
companyno char(13), 
addresscd char(2),
jobsmallcd1 char(3), 
jobsmallcd2 char(3), 
jobsmallcd3 char(3), 
joblargecd1 char(1), 
joblargecd2 char(1), 
joblargecd3 char(1), 
jobcategorysmallcd char(3), 
jobcategorylargecd char(1),  
companykana varchar(54), 
companyname varchar(60), 
companypostal char(8), 
companyplace varchar(75), 
companyurl varchar(100), 
postal char(8), 
address varchar(90), 
nearstation varchar(30), 
job varchar(28), 
hakencd char(1), 
detail varchar(297), 
koyoukeitaicd char(1), 
koyoukikan char(9), 
koyoukikankaishi date, 
koyoukikanowari date, 
education varchar(64), 
experience varchar(84), 
license varchar(84), 
agemin int, 
agemax int, 
salarymin int, 
salarymax int, 
salaryformcd varchar(1), 
begintime int, 
endtime int, 
establishdt int(4), 
capital long, 
companyfeature varchar(90), 
tantouyakushoku varchar(28), 
tantoukana varchar(28), 
tantou varchar(14), 
tantoustaff_id char(4), 
applicationform varchar(500), 
background varchar(1000), 
hiddensex char(1), 
hiddenagemin int, 
hiddenagemax int, 
hiddenetc varchar(1000), 
createdt timestamp not null default current_timestamp, 
createuserid char(4) not null, 
updatedt timestamp not null default current_timestamp, 
updateuserid char(4) not null, 
deleteflag char(1) not null, 
primary key(no)
)charset =utf8;

create table todouhuken(
cd char(2) not null,
name varchar(20) not null,
primary key(cd)
)charset =utf8;

create table jobcategory(
id int auto_increment,
largecd char(1),	
middlecd char(2),	
smallcd char(3),	
name varchar(128),	
primary key(id)
)charset =utf8;

create table job(
id int auto_increment,
largecd char(1),	
middlecd char(2),	
smallcd char(3),	
name varchar(128),	
primary key(id)
)charset =utf8;

create table matchingcase(
id int auto_increment not null, 
companyno char(13),
kyujinno char(14), 
jobseekerid varchar(8), 
staffid char(4), 
interviewdt date, 
enterdt date, 
assessment char(1), 
note varchar(200), 
createdt timestamp not null default current_timestamp, 
createuserid char(4) not null, 
updatedt timestamp not null default current_timestamp, 
updateuserid char(4) not null,
primary key(id)
)charset =utf8;

create table comment(
id int auto_increment not null, 
companyno char(13),
kyujinno char(14), 
jobseekerid varchar(8), 
staffid char(4), 
matchid int,
genre varchar(15), 
important char(1), 
title varchar(60), 
note varchar(4000), 
createdt timestamp not null default current_timestamp, 
createuserid char(4) not null, 
updatedt timestamp not null default current_timestamp, 
updateuserid char(4) not null,
primary key(id)
)charset =utf8;

create table saiban(
jobseekersaiban int,
kyujinsaiban int,
companysaiban int,
staffsaiban int
)charset =utf8;





