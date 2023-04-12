-- create table user_tb (
--     id int auto_increment primary key,
--     name varchar(30) unique not null,
--     password varchar(30) unique not null,
--     email varchar(30) unique not null,
--     tel varchar(30) unique not nu,
--     role enum
--     profile varchar(30),
--     account_id unique,
--     created_at timestamp
-- );

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

-- 결제
-- id pk int
-- user User  - 유저 정보
-- place Place  - 공간 정보
-- reservation Reservation FK >- place.id - 예약 정보
-- status string FK >- Reservation.id - 결제 상태
-- total_price int - 전체 금액
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


-- 주소
-- id pk int FK >- place.address
-- roadFullAddr string  - 도로명 주소
-- sggNm string  - 시군구명
-- zipNo string null - 우편번호
-- lat string null - 위도
-- lng string null - 경도
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

-- 채팅기록
-- chat_room_id int FK >- Chat_room.id - 채팅방 정보


-- 검색
-- id pk int
-- user_id int - 유저아이디
-- keyword string - 키워드  
-- ->enum으로 만들어서 키워드 정하고 클릭시 이동????


-- 알림
-- id pk int
-- user_id int FK >- User.id  - 유저아이디
-- place_id int FK >- Place.id  - 공간(호스트)아이디
-- Payment_total_price int FK >- Payment."..."  - 총결제금액
-- content string - 알림내용
-- status enum - 알림상태

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