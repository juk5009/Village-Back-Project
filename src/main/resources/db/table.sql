create table user_tb (
    id int auto_increment primary key,
    name varchar(255) unique not null,
    password varchar(255) not null,
    email varchar(255) unique not null,
    tel varchar(255),
    role varchar,
    profile varchar(255),
    created_at timestamp
);



create table place_tb (
    id int primary key AUTO_INCREMENT,
    user_id int,
    title varchar(255),
    address_id int,
    tel varchar(255),
    notice varchar,
    place_introduction_info varchar,
    max_people int,
    max_parking int,
    price_per_hour int,
    start_time timestamp,
    end_time timestamp,
    category_id int
);


create table address_tb (
    id int primary key auto_increment,
    road_full_addr varchar(255),
    sgg_nm varchar(255),
    zip_no varchar(255),
    lat varchar(255),
    lng varchar(255)
);



create table facility_info_tb (
    id int primary key AUTO_INCREMENT,
    facility_name varchar(255),
    facility_info_id,
    place_id int


);

create table hashtag_tb (
    id int primary key AUTO_INCREMENT,
    hashtag_name VARCHAR(255),
    hashtag_id int,
    place_id int
);


create table account_tb (
    id int primary key AUTO_INCREMENT,
    user_id int not null,
    account_num VARCHAR(255) not null

);

create table category_tb (
    id int primary key AUTO_INCREMENT,
    category_name VARCHAR,
    place_id int,
);

create table review_tb (
    id int primary key AUTO_INCREMENT,
    user_id int,
    place_id int,
    star_rating int,
    content VARCHAR(255),
    image VARCHAR(255),
    like_count int,
    created_at TIMESTAMP
);



create table reservation_tb (
    id int primary key AUTO_INCREMENT,
    user_id int,
    place_id int,
    date timestamp,
    start_time timestamp,
    end_time timestamp,
    people_num int
);

CREATE TABLE scrap_tb (
    id int primary key AUTO_INCREMENT,
    user_id int,
    place_id int,
    count int
);


create table chatroom_tb (
    id int primary key auto_increment,
    user_id int not null,
    place_id int not null,
    created_at timestamp not null,
);
--
--
create table search_tb (
    id int AUTO_INCREMENT primary key,
    user_id int,
    place_id int,
    keyword varchar(255),

);

create table chat_tb (
    id int PRIMARY KEY AUTO_INCREMENT,
    user_id int not null,
    send varchar,
    chatroom_id int not null,
    created_at timestamp

);
--
--
create table payment_tb (
    id int AUTO_INCREMENT PRIMARY KEY,
    user_id int,
    place_id int,
    reservation_id int,
    status VARCHAR(20) NOT NULL,
    total_price int NOT NULL,
    created_at TIMESTAMP
);
--
--
create table notice_tb (
    id int AUTO_INCREMENT PRIMARY KEY,
    user_id int,
    place_id int,
    payment_id int,
    content VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL,

);
--
create table dates_tb
(
    id int PRIMARY KEY AUTO_INCREMENT,
    day_of_week_name varchar NOT NULL,
    place_id int,
    dates_id int
);

create table file_tb
(
    id int PRIMARY KEY AUTO_INCREMENT,
    place_id int,
    file_info_id int,
    file_name VARCHAR(255),
    file_url VARCHAR,
    status VARCHAR
);


commit;





-- 예약
-- id pk int
-- user User FK >- User.id   - 유저정보
-- place Place FK >- place.id - 공간정보
-- date string  - 예약 날짜
-- start_time string - 예약 시작 시간
-- end_time string - 예약 마감 시간
-- people_num int - 사람 수
-- status enum - 예약 상태
-- ->


-- 계좌
-- id pk int  
-- user_id int FK >- User.id -  호스트 아이디
-- account string - 유저의 계좌번호 


-- 공간
-- id pk int
-- user User FK >- User.id  - 유저(호스트 정보)
-- title string - 공간 제목
-- address Address - 주소
-- tel stirng - 전화번호
-- review Review - 리뷰
-- place_introduction string - 공간 정보
-- guide string - 공간 소개
-- facilityInfo string  - 시설 정보
-- hashtag string  - 해쉬태그
-- images string  - 공간 사진들
-- max_people int - 최대인원수?
-- price_per_hour int - 한시간당 금액
-- start_time string - 시작 시간
-- end_time string  - 마감 시간
-- category Category FK >- Category.id - 공간의 카테고리들
-- -> 거의 다 텍스트

-- 리뷰
-- id pk int FK >- place.review
-- user User FK >- User.id  - 유저 정보 (결제를 한 사람 대상으로 할 수 있게)
-- star_rating int  - 별점
-- content string  - 내용
-- image string - 리뷰 사진
-- like_count int  -좋아요 수
-- craete_at timestamp  - 시간
-- -> 




-- 카테고리
-- name - 카테고리별   - 이름 Enum
-- -> 연습실, 스터디룸, 공유오피스, ?? 세팅

-- 채팅방
-- id pk int
-- user_id int FK >- User.id   - 유저아이디 
-- place_id int FK >- Place.id - User.id  - 호스트아이디
-- u_content string - 유저 채팅 내용@@@@@@@@@@@@@@
-- h_content string - 호스트 채팅내용@@@@@@@@@@@@@@@
-- created timestamp - 시간

-- 채팅
-- id pk int
-- user_id int FK >- User.id  - 유저아이디
-- send string - 송신 문자
-- chat_room_id int FK >- Chat_room.id - 채팅방 정보
-- created_at timestamp - 시간










-- 스크랩
-- id pk int
-- user_id int FK >- User.id - 유저아이디
-- place_id int FK >- Place.id  - 공간아이디 
-- count int - 스크랩 수
-- -> 유저 아이디와 공간 아이디가 있을 시 count 올라가게 구현


-- 1일반유저 2 호스트  3 관리자

-- 채팅기록
-- 3번이 모든채팅 기록을 관리하는거고

-- 채팅방은
-- 1번과 2번의 대화내용을 관리