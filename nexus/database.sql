use trynexus;
-- ↑ローカルで試すときは自身のuser名に変更

drop table if exists staff;
drop table if exists jobseeker;
drop table if exists zokuseijobseeker;
drop table if exists `character`;
drop table if exists jscharacter;
drop table if exists jscareer;
drop table if exists company;
drop table if exists kyujin;
drop table if exists comment;
drop table if exists pastkyujin;
drop table if exists hwkyujin;
drop table if exists todouhuken;
drop table if exists jobcategory;
drop table if exists job;
drop table if exists matchingcase;
drop table if exists saiban;

-- 職業紹介者・開拓者 --
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

-- 求職者 --
create table jobseeker(
id varchar(8) not null,
name varchar(100) not null,
kana varchar(100),
birthdt date,
sex char(1),
age int(3),
postal char(8),
address varchar(50),
seekermail varchar(50),
nearstation varchar(50),
phone char(20),
mobile char(20),
partner char(1),
huyou int(2),
education varchar(1),
career varchar(1000),
HOPEJOB1 char(6),
HOPEJOB2 char(6),
HOPEJOB3 char(6),
HOPEJOBCATEGORY char(3),
HOPEJOBCATEGORY2 char(3),
HOPEJOBCATEGORY3 char(3),
hopeworkplace varchar(100),
hopekoyoukeitai char(1),
hopeweekday char(7),
hopeworkingdate int(1),
hopebegintime int(4),
hopeendtime int(4),
hopesalary int(8),
hopejikyu int(4),
hopeetc varchar(200),
driverlicense char(3),
licenseetc varchar(500),
pasokonskill varchar(200),
caution varchar(200),
tantoustaffid char(4),
password varchar(50),
note varchar(200),
createdt timestamp not null default current_timestamp,
createuserid char(4) not null,
updatedt timestamp not null default current_timestamp,
updateuserid char(4) not null,
deleteflag char(1) not null,
primary key(id)
)charset =utf8;

-- 求職者の属性情報（将来的に個人情報と属性情報とを分離するときに使う） --
create table zokuseijobseeker(
id varchar(8) not null,
birthdt date,
sex char(1),
age int(3),
postal char(8),
nearstation varchar(50),
partner char(1),
huyou int(2),
education varchar(1),
career varchar(1000),
HOPEJOB1 char(6),
HOPEJOB2 char(6),
HOPEJOB3 char(6),
HOPEJOBCATEGORY char(3),
HOPEJOBCATEGORY2 char(3),
HOPEJOBCATEGORY3 char(3),
hopeworkplace varchar(100),
hopekoyoukeitai char(1),
hopeweekday char(7),
hopeworkingdate int(1),
hopebegintime int(4),
hopeendtime int(4),
hopesalary int(8),
hopejikyu int(4),
hopeetc varchar(200),
driverlicense char(3),
licenseetc varchar(500),
pasokonskill varchar(200),
caution varchar(200),
tantoustaffid char(4),
password varchar(50),
note varchar(200),
createdt timestamp not null default current_timestamp,
createuserid char(4) not null,
updatedt timestamp not null default current_timestamp,
updateuserid char(4) not null,
deleteflag char(1) not null,
primary key(id)
)charset =utf8;

-- 特性 --
create table `character`(
id int auto_increment not null,
`character` varchar(20) not null,
primary key(id)
)charset =utf8;

-- 求職者特性 --
create table jscharacter(
jobseekerid char(8) not null,
characterid int not null,
primary key(jobseekerid)
)charset =utf8;

-- 求職者職歴 --
create table jscareer(
jobseekerid char(8) not null,
career varchar(200) not null,
nyushokudt int,
taishokudt int,
primary key(jobseekerid)
)charset =utf8;

-- 企業情報 --
create table company(
corporatenumber char(13),
companyno char(13),
companyname varchar(60),
companykana varchar(54),
companypostal char(8),
companyplace varchar(75),
nearstation varchar(30),
companyurl varchar(100),
jobcategorysmallcd char(3),
jobcategorymiddlecd char(2),
jobcategorylargecd char(1),
capital bigint(16),
employees varchar(6),
establishdt int(4),
tantouyakushoku varchar(28),
tantou varchar(14),
tantoukana varchar(28),
tantoutel varchar(20),
tantoufax varchar(20),
tantoumail varchar(50),
tantounote varchar(50),
tantoustaff_id char(4),
salesrank char(1),
salesnote varchar(200),
createdt timestamp not null default current_timestamp,
createuserid char(4) not null,
updatedt timestamp not null default current_timestamp,
updateuserid char(4) not null,
deleteflag char(1) not null,
primary key(companyno)
)charset =utf8;

-- 求人情報 --
create table kyujin(
no char(14) not null,
companyno char(13),
companykana varchar(54),
postal char(8),
address varchar(90),
nearline varchar(30),
nearstation varchar(30),
addresscd char(2),
jobsmallcd1 char(3),
jobsmallcd2 char(3),
jobsmallcd3 char(3),
joblargecd1 char(1),
joblargecd2 char(1),
joblargecd3 char(1),
job varchar(28),
detail varchar(297),
koyoukeitaicd char(1),
hakencd char(1),
koyoukikan char(30),
koyoukikankaishi date,
koyoukikanowari date,
education varchar(64),
experience varchar(84),
license varchar(84),
agemin int(2),
agemax int(2),
salaryformcd varchar(1),
salarymin int(7),
salarymax int(7),
bouns varchar(10),
koutuhi varchar(30),
teate varchar(30),
begintime int(4),
endtime int(4),
shift varchar(60),
flex varchar(60),
jitan char(1),
jikangai int(2),
siyoukikan int(1),
workdays int(1),
nenkanholiday varchar(30),
applicationform varchar(500),
background varchar(1000),
bosyunumbers varchar(4),
hiddensex char(1),
hiddenagemin int(3),
hiddenagemax int(3),
hiddenetc varchar(1000),
receptiondt date,
perioddt date,
createdt timestamp not null default current_timestamp,
createuserid char(4) not null,
updatedt timestamp not null default current_timestamp,
updateuserid char(4) not null,
deleteflag char(1) not null,
primary key(no)
)charset =utf8;

-- 備考コメント欄 --
create table comment(
id int auto_increment not null,
companyno char(13),
kyujinno char(14),
jobseekerid varchar(8),
staffid char(4),
matchid int,
genre char(1),
important char(1),
title varchar(60),
note varchar(4000),
createdt timestamp not null default current_timestamp,
createuserid char(4) not null,
updatedt timestamp not null default current_timestamp,
updateuserid char(4) not null,
primary key(id)
)charset =utf8;

-- 過去求人情報 --
create table pastkyujin(
no char(14) not null,
companyno char(13),
companykana varchar(54),
postal char(8),
address varchar(90),
nearline varchar(30),
nearstation varchar(30),
addresscd char(2),
jobsmallcd1 char(3),
jobsmallcd2 char(3),
jobsmallcd3 char(3),
joblargecd1 char(1),
joblargecd2 char(1),
joblargecd3 char(1),
job varchar(28),
detail varchar(297),
koyoukeitaicd char(1),
hakencd char(1),
koyoukikan char(30),
koyoukikankaishi date,
koyoukikanowari date,
education varchar(64),
experience varchar(84),
license varchar(84),
agemin int(2),
agemax int(2),
salaryformcd varchar(1),
salarymin int(7),
salarymax int(7),
bouns varchar(10),
koutuhi varchar(30),
teate varchar(30),
begintime int(4),
endtime int(4),
shift varchar(60),
flex varchar(60),
jitan char(1),
jikangai int(2),
siyoukikan int(1),
workdays int(1),
nenkanholiday varchar(30),
applicationform varchar(500),
background varchar(1000),
bosyunumbers varchar(4),
hiddensex char(1),
hiddenagemin int(2),
hiddenagemax int(2),
hiddenetc varchar(1000),
receptiondt date,
perioddt date,
createdt timestamp not null default current_timestamp,
createuserid char(4) not null,
updatedt timestamp not null default current_timestamp,
updateuserid char(4) not null,
deleteflag char(1) not null,
primary key(no)
)charset =utf8;

-- ハローワーク求人票マスタ --
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

-- 都道府県マスタ --
create table todouhuken(
cd char(2) not null,
name varchar(20) not null,
primary key(cd)
)charset =utf8;

-- 業種(産業分類)マスタ --
create table jobcategory(
id int auto_increment,
largecd char(1),
middlecd char(2),
smallcd char(3),
name varchar(128),
primary key(id)
)charset =utf8;

-- 職種マスタ --
create table job(
id int auto_increment,
largecd char(1),
middlecd char(2),
smallcd char(3),
name varchar(128),
primary key(id)
)charset =utf8;

-- マッチング事例 --
create table matchingcase(
id int auto_increment not null ,
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

-- 採番 --
create table saiban(
jobseekersaiban int(14),
kyujinsaiban int(14),
companysaiban int(14),
staffsaiban int(14),
matchingsaiban int(14),
commentsaiban int(14)
)charset =utf8;


