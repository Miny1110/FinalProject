//주문상세(주문상품정보)
사용자 
- sys/system으로 로그인

//생성
CREATE USER cozy IDENTIFIED BY a123;


//권한부여
GRANT CONNECT,RESOURCE,DBA TO cozy;
GRANT CREATE TABLE, CREATE VIEW TO cozy;
GRANT CONNECT,DBA TO cozy;

//ITEM
CREATE TABLE ITEM
(itemNum NUMBER(8) PRIMARY KEY,
itemName varchar2(100),
itemMainType varchar2(20),
itemSubType varchar2(20),
itemPrice NUMBER(10),
itemDiscount NUMBER(10),
itemContent varchar2(50),
itemImage1 varchar2(100),
itemImage2 varchar2(100),
itemImage3 varchar2(100),
itemImage4 varchar2(100),
detailImage varchar2(100),
itemHitcount NUMBER (10),
itemStock NUMBER(10),
itemState varchar2(30),
todaydeal varchar2(30),
itemColor varchar2(100),
itemSize varchar2(100));

//CART
CREATE TABLE CART
(CARTNUM NUMBER(8) PRIMARY KEY,
ITEMNUM NUMBER(8),
customerEmail VARCHAR2(50),
itemQty NUMBER(20),
itemColor varchar2(100),
itemSize varchar2(100),
FOREIGN KEY (customerEmail) REFERENCES CUSTOMER(customerEmail),
FOREIGN KEY (ITEMNUM) REFERENCES ITEM(ITEMNUM));

//CUSTOMER
CREATE TABLE CUSTOMER(
CUSTOMEREMAIL VARCHAR2(100) PRIMARY KEY,
CUSTOMERPWD VARCHAR2(100),
CUSTOMERNAME VARCHAR2(45),
CUSTOMERTEL VARCHAR2(45),
CUSTOMERZIPCODE VARCHAR2(10),
CUSTOMERRADDR VARCHAR2(100),
CUSTOMERJADDR VARCHAR2(100),
CUSTOMERDADDR VARCHAR2(100),
CUSTOMERPROFILE VARCHAR2(100),
CUSTOMERDATE DATE,
CUSTOMERGRADE VARCHAR2(45),
CUSTOMERTYPE NUMBER(30),
CUSTOMERPOINT NUMBER(38));

//POINT
CREATE TABLE POINT (
POINTNUM NUMBER(30) PRIMARY KEY,
POINTTITLE VARCHAR2(100),
POINTCONTENT VARCHAR2(100),
POINTAMOUNT NUMBER(30),
POINTSTATE VARCHAR2(45),
POINTDATE DATE,
POINTSTARTDATE DATE,
POINTENDDATE DATE,
CUSTOMEREMAIL VARCHAR2(100),
FOREIGN KEY (CUSTOMEREMAIL) REFERENCES CUSTOMER(CUSTOMEREMAIL)
ON DELETE CASCADE);

//SERVICENOTICE
CREATE TABLE notice(
noticeNum number PRIMARY KEY,
noticeTitle VARCHAR2(150) NOT NULL,
noticeContent VARCHAR2(3000) NOT NULL,
noticeDate DATE DEFAULT SYSDATE);

//DELIVER
create table deliver(
deliverNum number(10) primary key,
customerEmail VARCHAR2(100),
deliverName VARCHAR2(20),
deliverRAddr VARCHAR2(100),
deliverJAddr VARCHAR2(100),
deliverDAddr VARCHAR2(100),
deliverZipCode VARCHAR2(10),
deliverTel VARCHAR2(45),
deliverType VARCHAR2(45),
FOREIGN KEY (customerEmail) REFERENCES CUSTOMER(customerEmail)
ON DELETE CASCADE);

//Item이미지
CREATE table file_store (
no NUMBER(8) PRIMARY KEY,
original_file_name VARCHAR2(100),
uuid_file_name VARCHAR2(100),
file_path VARCHAR2(100),
reg_dt DATE,
FILE_EXTENSION VARCHAR2(50)
);

/*상품답변 qnaAnswer -> itemAnswer 명칭변경 (10/27일 수정)*/
CREATE TABLE itemAnswer 
(itemAnsNum NUMBER PRIMARY KEY,
itemQueNum NUMBER, 
itemAnsCreate date, 
itemAnsContent VARCHAR2(4000), 
FOREIGN KEY (itemQueNum) REFERENCES itemQuestion(itemQueNum)
ON DELETE CASCADE);


/*상품질문 ITEMQNA -> itemQuestion 명칭변경 (10/27일 수정)*/
CREATE TABLE itemQuestion 
(itemQueNum NUMBER PRIMARY KEY, 
itemNum NUMBER, 
itemQueTitle varchar2(500), 
itemQueCreate date, 
itemQueContent varchar2(4000), 
customerEmail varchar2(4000), 
FOREIGN KEY (itemNum) REFERENCES item(itemNum)
ON DELETE CASCADE);

//고객센터질문
CREATE TABLE serviceQuestion
(serviceQueNum NUMBER(8) PRIMARY KEY,
CUSTOMEREMAIL VARCHAR2(100),
serviceQueTitle varchar2(500),
serviceQueContent varchar2(4000),
serviceQueDate date,
FOREIGN KEY (CUSTOMEREMAIL) REFERENCES customer(CUSTOMEREMAIL)
ON DELETE CASCADE);

//고객센터답변
CREATE TABLE serviceAnswer
(serviceAnsNum NUMBER(8) PRIMARY KEY,
serviceQueNum NUMBER(8),
serviceAnsContent varchar2(4000),
serviceAnsDate date,
FOREIGN KEY (serviceQueNum) REFERENCES serviceQuestion(serviceQueNum)
ON DELETE CASCADE);

//주문(공통정보)
CREATE TABLE COZYORDER
(orderNum VARCHAR2(100) PRIMARY KEY,
customerEmail VARCHAR2(100),
payment VARCHAR2(20), 
orderState VARCHAR2(20),
deliverCost number(10),
deliverName VARCHAR2(20),
deliverRAddr VARCHAR2(100),
deliverJAddr VARCHAR2(100),
deliverDAddr VARCHAR2(100),
deliverZipCode VARCHAR2(10),
deliverTel VARCHAR2(45),
deliverMessage VARCHAR2(100),
orderDate date default sysdate,
usePoint number(10),
FOREIGN KEY (customerEmail) REFERENCES CUSTOMER(customerEmail));

//주문상세(주문상품정보) 10/30수정
create table orderdetail
(odnum VARCHAR2(100) primary Key,
orderNum VARCHAR2(100),
itemNum number(8),
itemQty NUMBER(20),
itemColor VARCHAR2(100),
itemSize VARCHAR2(100),
FOREIGN KEY (orderNum) REFERENCES COZYORDER(orderNum),
FOREIGN KEY (itemNum) REFERENCES item(itemNum));

//상품리뷰
CREATE TABLE Reply
(replyId NUMBER(38) PRIMARY KEY,
itemNum NUMBER(38),
customerEmail varchar2(100),
regDate date,
content varchar2(2000),
rating varchar2(10),
FOREIGN KEY (CUSTOMEREMAIL) REFERENCES CUSTOMER(CUSTOMEREMAIL)
ON DELETE CASCADE);
