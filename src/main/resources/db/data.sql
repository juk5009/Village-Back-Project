insert into place_address_tb(address, sigungu, zonecode, detail_address, x, y)
values ('부산 부산진구 중앙대로 688 한준빌딩 2층', '부산 부산진구', '47296', '201호' ,'12', '15');
insert into place_address_tb(address, sigungu, zonecode, detail_address, x, y)
values ('부산 부산진구 중앙대로 688 한준빌딩 3층', '부산 부산진구', '47296', '301호' ,'112', '115');
insert into place_address_tb(address, sigungu, zonecode, detail_address, x, y)
values ('부산 부산진구 중앙대로 688 한준빌딩 4층', '부산 부산진구', '47296', '401호' ,'12', '215');
INSERT INTO place_address_tb(address,sigungu,zonecode,detail_address,x,y)
VALUES('서울 강남구 역삼동 123-4', '서울 강남구', '06351', '302호', '127.027943', '37.497907');
INSERT INTO place_address_tb(address,sigungu,zonecode,detail_address,x,y)
VALUES('대구 북구 대학로23길 23', '대구 북구', '41588', '101호', '128.607348', '35.886049');
INSERT INTO place_address_tb(address,sigungu,zonecode,detail_address,x,y)
VALUES('인천 계양구 장제로 22', '인천 계양구', '21072', '301호', '126.735034', '37.538197');
INSERT INTO place_address_tb(address,sigungu,zonecode,detail_address,x,y)
VALUES('부산 해운대구 구남로 29', '부산 해운대구', '48060', '401호', '129.160326', '35.163042');


insert into user_tb (name, password, email, tel, role, profile, status, created_at)
values ('ssar', '$2a$10$AY7h/0uPoe5UtfMZxmxNq.d4KOTZiKbZFvbM3k7vlsfpjAJd0fTvW', 'ssar@naver.com', '01012345678', 'USER', '/images/dora.png', 'ACTIVE', '2023-05-07 21:52:13');
insert into user_tb (name, password, email, tel, role, profile, status, created_at)
values ('Jane', '$2a$10$AY7h/0uPoe5UtfMZxmxNq.d4KOTZiKbZFvbM3k7vlsfpjAJd0fTvW', 'Jane@naver.com', '01023455678', 'HOST', '/images/dora.png', 'ACTIVE', '2019-05-07 21:52:13');
insert into user_tb (name, password, email, tel, role, profile, status, created_at)
values ('Bob', '$2a$10$AY7h/0uPoe5UtfMZxmxNq.d4KOTZiKbZFvbM3k7vlsfpjAJd0fTvW', 'Bob@naver.com', '01067895678', 'ADMIN', '/images/dora.png', 'ACTIVE', '2022-12-01 21:52:13');
INSERT INTO user_tb(name,password,email,tel,role,profile, status, created_at)
VALUES('Alice','$2a$10$AY7h/0uPoe5UtfMZxmxNq.d4KOTZiKbZFvbM3k7vlsfpjAJd0fTvW','Alice@google.com','01012345678','USER','/images/spongebob.png', 'ACTIVE', '2023-05-07 21:32:03');
INSERT INTO user_tb(name,password,email,tel,role,profile, status, created_at)
VALUES('Charlie','$2a$10$AY7h/0uPoe5UtfMZxmxNq.d4KOTZiKbZFvbM3k7vlsfpjAJd0fTvW','Charlie@gmail.com','01022223333','HOST','/images/unicorn.png', 'ACTIVE', '2022-05-09 10:10:13');
INSERT INTO user_tb(name,password,email,tel,role,profile, status, created_at)
VALUES('David','$2a$10$AY7h/0uPoe5UtfMZxmxNq.d4KOTZiKbZFvbM3k7vlsfpjAJd0fTvW','David@yahoo.com','01055556666','HOST','/images/pikachu.png', 'ACTIVE', '2023-05-17 21:52:13');
INSERT INTO user_tb(name,password,email,tel,role,profile, status, created_at)
VALUES('Emma','$2a$10$AY7h/0uPoe5UtfMZxmxNq.d4KOTZiKbZFvbM3k7vlsfpjAJd0fTvW','Emma@hotmail.com','01098765432','USER','/images/totoro.png', 'ACTIVE', '2023-01-01 21:00:13');


insert into account_tb(user_id, account_num)
values (1, '123456-01-123456');
insert into account_tb(user_id, account_num)
values (1, '123434-01-123354');
insert into account_tb(user_id, account_num)
values (1, '333456-01-111244');

insert into place_tb (user_id, title, address_id, tel, notice, place_introduction_info, max_people, max_parking,
                      price_per_hour, status, is_confirmed,
                      start_time, end_time)
values (2, '스튜디오 르온드', 1, '01012345678', '좋은 공간입니다.', '좋은 공간입니다. 이용해보세요!', 10, 5, 4000, 'ACTIVE', true, NOW(), NOW());
insert into place_tb (user_id, title, address_id, tel, notice, place_introduction_info, max_people, max_parking,
                      price_per_hour, status, is_confirmed,
                      start_time, end_time)
values (2, '스튜디오 STUDIO', 2, '01012345679', '멋진 공간입니다.', '멋진 공간입니다. 이용해보세요!', 10, 10, 5000, 'ACTIVE', true, NOW(), NOW());
insert into place_tb (user_id, title, address_id, tel, notice, place_introduction_info, max_people, max_parking,
                      price_per_hour, status, is_confirmed,
                      start_time, end_time)
values (5, '업타운댄스뮤직 아카데미', 3, '01012345680', '편안한 공간입니다.', '편안한 공간입니다. 이용해보세요!', 10, 15, 3000, 'ACTIVE', false, NOW(), NOW());

insert into place_tb (user_id, title, address_id, tel, notice, place_introduction_info, max_people, max_parking,
                      price_per_hour, status, is_confirmed,
                      start_time, end_time)
VALUES (6, '홍대 쉐어하우스', 2, '02-1234-5678', '파티, 모임에 적합한 공간입니다.', '이 곳은 홍대 중심에 위치한 쉐어하우스로, 친구들과 함께 모여 파티를 즐길 수 있는 공간입니다.',
           20, 5,  20000, 'ACTIVE', true, '2023-06-01 18:00:00',  '2023-06-01 23:00:00' );

insert into place_tb (user_id, title, address_id, tel, notice, place_introduction_info, max_people, max_parking,
                      price_per_hour, status, is_confirmed,
                      start_time, end_time)
VALUES ( 7, '강남 스튜디오', 2, '02-999-8888', '조용한 모임에 적합한 공간입니다.', '강남 중심지에 위치한 스튜디오로, 작은 모임에서 부터 회식까지 이용 가능합니다.',
         10, 2, 30000, 'ACTIVE', true, '2023-06-01 14:00:00', '2023-06-01 18:00:00');

insert into place_tb (user_id, title, address_id, tel, notice, place_introduction_info, max_people, max_parking,
                      price_per_hour, status, is_confirmed,
                      start_time, end_time)
VALUES ( 7, '종로 룸카페', 1,'02-555-7777', '소규모 모임, 스터디, 세미나 등에 적합한 공간입니다.', '종로 중심지에 위치한 룸카페로, 개인적인 모임부터 간단한 세미나까지 이용 가능합니다.',
          8, 0, 25000, 'ACTIVE', true,'2023-06-01 10:00:00', '2023-06-01 13:00:00');

insert into place_tb (user_id, title, address_id, tel, notice, place_introduction_info, max_people, max_parking,
                      price_per_hour, status, is_confirmed,
                      start_time, end_time)
VALUES ( 7, '부산 룸카페', 1,'02-555-7777', '소규모 모임, 스터디, 세미나 등에 적합한 공간입니다.', '부산 중심지에 위치한 룸카페로, 개인적인 모임부터 간단한 세미나까지 이용 가능합니다.',
         8, 0, 25000, 'ACTIVE', true,'2023-06-01 10:00:00', '2023-06-01 13:00:00');


insert into review_tb (user_id, place_id, star_rating, content, image, like_count, created_at)
values (1, 1, 5, '좋은 상품이에요', NULL, 10, now());
insert into review_tb (user_id, place_id, star_rating, content, image, like_count, created_at)
values (2, 2, 4, '조금 아쉬운 부분도 있지만 전체적으로 만족스러웠어요', NULL, 5, now());
insert into review_tb (user_id, place_id, star_rating, content, image, like_count, created_at)
values (3, 3, 3, '그저 그랬어요', NULL, 2, now());
INSERT INTO review_tb (user_id, place_id, star_rating, content, image, like_count, created_at)
VALUES(3,4,3,'그저 그랬어요',NULL,2,NOW());
INSERT INTO review_tb (user_id, place_id, star_rating, content, image, like_count, created_at)
VALUES(5,6,4,'너무 좋았어요! 다음에 또 방문할게요.','/images/review_1.jpg',6,NOW());
INSERT INTO review_tb (user_id, place_id, star_rating, content, image, like_count, created_at)
VALUES(4,5,2,'많이 실망했어요.','/images/review_2.jpg',1,NOW());
INSERT INTO review_tb (user_id, place_id, star_rating, content, image, like_count, created_at)
VALUES(4,6,4,'가성비 좋은 숙소에요.','/images/review_3.jpg',3,NOW());
INSERT INTO review_tb (user_id, place_id, star_rating, content, image, like_count, created_at)
VALUES(2,1,5,'최고의 여행이었어요!','/images/review_4.jpg',10,NOW());


insert into host_tb (user_id, nick_name, address_id, business_num, place_id, status) values (1, 'ssar', 1, '457-10-784',  1, 'WAIT');
insert into host_tb (user_id, nick_name, address_id, business_num, place_id, status) values (2, 'Jane', 2, '424-52-724',  2, 'SIGN');
insert into host_tb (user_id, nick_name, address_id, business_num, place_id, status) values (3, 'Bob', 3, '552-18-254',  3, 'NONE');
INSERT INTO host_tb (user_id, nick_name, address_id, business_num, place_id, status) VALUES(4,'Alice',2,'222-13-111', 4, 'SIGN');
INSERT INTO host_tb (user_id, nick_name, address_id, business_num, place_id, status) VALUES(5,'John',3,'449-08-975', 5, 'SIGN');
INSERT INTO host_tb (user_id, nick_name, address_id, business_num, place_id, status) VALUES(6,'Kate',1,'001-01-001', 6, 'NONE');
INSERT INTO host_tb (user_id, nick_name, address_id, business_num, place_id, status) VALUES(7,'Emma',1,'001-01-001', 7, 'NONE');

insert into facility_info_tb (facility_name, place_id)values ('Wifi', 1);
insert into facility_info_tb (facility_name, place_id)values ('화장실', 1);
insert into facility_info_tb (facility_name, place_id)values ('주차장', 1);
INSERT INTO facility_info_tb (facility_name, place_id)values ('냉장고', 1);
INSERT INTO facility_info_tb (facility_name, place_id)values ('정수기', 1);

insert into facility_info_tb (facility_name, place_id)values ('Wifi',  2);
INSERT INTO facility_info_tb (facility_name, place_id)values ('냉장고', 2);
INSERT INTO facility_info_tb (facility_name, place_id)values ('정수기', 2);

insert into facility_info_tb (facility_name, place_id)values ('Wifi', 3);
insert into facility_info_tb (facility_name, place_id)values ('화장실', 3);
INSERT INTO facility_info_tb (facility_name, place_id)values ('정수기', 3);

insert into facility_info_tb (facility_name, place_id)values ('Wifi', 4);
INSERT INTO facility_info_tb (facility_name, place_id)values ('냉장고', 4);
INSERT INTO facility_info_tb (facility_name, place_id)values ('정수기', 4);

insert into facility_info_tb (facility_name, place_id)values ('Wifi', 5);
insert into facility_info_tb (facility_name, place_id)values ('화장실', 5);
insert into facility_info_tb (facility_name, place_id)values ('주차장', 5);
INSERT INTO facility_info_tb (facility_name, place_id)values ('냉장고', 5);
INSERT INTO facility_info_tb (facility_name, place_id)values ('정수기', 5);

insert into facility_info_tb (facility_name, place_id)values ('Wifi', 6);
insert into facility_info_tb (facility_name, place_id)values ('화장실', 6);
INSERT INTO facility_info_tb (facility_name, place_id)values ('정수기', 6);

insert into facility_info_tb (facility_name, place_id)values ('Wifi', 7);
insert into facility_info_tb (facility_name, place_id)values ('화장실', 7);
insert into facility_info_tb (facility_name, place_id)values ('주차장', 7);
INSERT INTO facility_info_tb (facility_name, place_id)values ('정수기', 7);

insert into category_tb (category_name, place_id)
values ('연습실', 1);
insert into category_tb (category_name, place_id)
values ('스터디룸', 2);
insert into category_tb (category_name, place_id)
values ('공유오피스', 3);
insert into category_tb (category_name, place_id)
values ('연습실', 4);
insert into category_tb (category_name, place_id)
values ('스터디룸', 5);
insert into category_tb (category_name, place_id)
values ('공유오피스', 6);


insert into reservation_tb (user_id, place_id, date, start_time, end_time, people_num, status)
values (1, 1, '2023-04-20', '2023-04-20T00:01', '2023-04-20T04:01', 2, 'WAIT');
insert into reservation_tb (user_id, place_id, date, start_time, end_time, people_num, status)
values (2, 2, '2023-04-21', '2023-04-21T00:01', '2023-04-21T00:01', 3, 'COMPLETE');
insert into reservation_tb (user_id, place_id, date, start_time, end_time, people_num, status)
values (3, 7, '2023-04-22', '2023-04-22T10:01', '2023-04-22T20:01', 4, 'FAIL');
insert into reservation_tb (user_id, place_id, date, start_time, end_time, people_num, status)
values (1, 4, '2023-04-30', '2023-04-30T00:01', '2023-04-30T04:01', 2, 'WAIT');
insert into reservation_tb (user_id, place_id, date, start_time, end_time, people_num, status)
values (2, 5, '2023-05-10', '2023-05-10T00:01', '2023-05-10T00:01', 3, 'COMPLETE');
insert into reservation_tb (user_id, place_id, date, start_time, end_time, people_num, status)
values (5, 2, '2023-05-20', '2023-05-20T10:01', '2023-05-20T20:01', 4, 'FAIL');
insert into reservation_tb (user_id, place_id, date, start_time, end_time, people_num, status)
values (3, 3, '2023-05-22', '2023-05-22T00:01', '2023-05-22T04:01', 2, 'WAIT');
insert into reservation_tb (user_id, place_id, date, start_time, end_time, people_num, status)
values (4, 2, '2023-05-23', '2023-05-23T00:01', '2023-05-23T00:01', 3, 'COMPLETE');
insert into reservation_tb (user_id, place_id, date, start_time, end_time, people_num, status)
values (6, 6, '2023-06-20', '2023-06-20T10:01', '2023-06-20T20:01', 4, 'FAIL');

insert into scrap_tb (user_id, place_id)
values (1, 1);
insert into scrap_tb (user_id, place_id)
values (2, 2);
insert into scrap_tb (user_id, place_id)
values (3, 3);

insert into chat_room_tb (user_id, place_id, created_at)
values (1, 1, NOW());
insert into chat_room_tb (user_id, place_id, created_at)
values (2, 2, NOW());
insert into chat_room_tb (user_id, place_id, created_at)
values (3, 3, NOW());

insert into search_tb (user_id, place_id, keyword)
values (1, 1, '연습실');
insert into search_tb (user_id, place_id, keyword)
values (2, 2, '스터디룸');
insert into search_tb (user_id, place_id, keyword)
values (3, 3, '커피숍');

insert into chat_tb (user_id, send, chat_room_id, created_at)
values (1, '안녕하세요!', 1, NOW());
insert into chat_tb (user_id, send, chat_room_id, created_at)
values (2, '반갑습니다!', 1, NOW());
insert into chat_tb (user_id, send, chat_room_id, created_at)
values (1, '어디에 계시나요?', 1, NOW());
insert into chat_tb (user_id, send, chat_room_id, created_at)
values (2, '서울에 있습니다.', 1, NOW());


insert into payment_tb (user_id, place_id, reservation_id, status, total_price)
values (2, 5, 5, 'WAIT', 30000);
insert into payment_tb (user_id, place_id, reservation_id, status, total_price)
values (1, 2, 4, 'COMPLETE', 20000);
insert into payment_tb (user_id, place_id, reservation_id, status, total_price)
values (2, 3, 2, 'FAIL', 10000);
insert into payment_tb (user_id, place_id, reservation_id, status, total_price)
values (4, 7, 1, 'WAIT', 30000);
insert into payment_tb (user_id, place_id, reservation_id, status, total_price)
values (3, 2, 1, 'COMPLETE', 20000);
insert into payment_tb (user_id, place_id, reservation_id, status, total_price)
values (2, 1, 4, 'FAIL', 10000);
insert into payment_tb (user_id, place_id, reservation_id, status, total_price)
values (6, 2, 4, 'WAIT', 30000);
insert into payment_tb (user_id, place_id, reservation_id, status, total_price)
values (7, 4, 5, 'COMPLETE', 20000);
insert into payment_tb (user_id, place_id, reservation_id, status, total_price)
values (5, 5, 6, 'FAIL', 10000);

insert into dates_tb (day_of_week_name, place_id)
values ('월요일', 1);
insert into dates_tb (day_of_week_name, place_id)
values ('화요일', 1);
insert into dates_tb (day_of_week_name, place_id)
values ('수요일', 1);
insert into dates_tb (day_of_week_name, place_id)
values ('목요일', 1);
insert into dates_tb (day_of_week_name, place_id)
values ('금요일', 1);
insert into dates_tb (day_of_week_name, place_id)
values ('토요일', 1);

insert into dates_tb (day_of_week_name, place_id)values ('월요일', 2);
insert into dates_tb (day_of_week_name, place_id)values ('화요일', 2);
insert into dates_tb (day_of_week_name, place_id)values ('수요일', 2);
insert into dates_tb (day_of_week_name, place_id)values ('목요일', 2);

insert into dates_tb (day_of_week_name, place_id)values ('월요일', 3);
insert into dates_tb (day_of_week_name, place_id)values ('화요일', 3);
insert into dates_tb (day_of_week_name, place_id)values ('수요일', 3);
insert into dates_tb (day_of_week_name, place_id)values ('금요일', 3);

insert into dates_tb (day_of_week_name, place_id)values ('월요일', 4);
insert into dates_tb (day_of_week_name, place_id)values ('화요일', 4);
insert into dates_tb (day_of_week_name, place_id)values ('수요일', 4);
insert into dates_tb (day_of_week_name, place_id)values ('목요일', 4);
insert into dates_tb (day_of_week_name, place_id)values ('금요일', 4);

insert into dates_tb (day_of_week_name, place_id)values ('월요일', 5);
insert into dates_tb (day_of_week_name, place_id)values ('화요일', 5);
insert into dates_tb (day_of_week_name, place_id)values ('수요일', 5);
insert into dates_tb (day_of_week_name, place_id)values ('목요일', 5);
insert into dates_tb (day_of_week_name, place_id)values ('금요일', 5);

insert into dates_tb (day_of_week_name, place_id)values ('월요일', 6);
insert into dates_tb (day_of_week_name, place_id)values ('화요일', 6);
insert into dates_tb (day_of_week_name, place_id)values ('수요일', 6);
insert into dates_tb (day_of_week_name, place_id)values ('목요일', 6);
insert into dates_tb (day_of_week_name, place_id)values ('금요일', 6);
insert into dates_tb (day_of_week_name, place_id)values ('토요일', 6);
insert into dates_tb (day_of_week_name, place_id)values ('일요일', 6);

insert into dates_tb (day_of_week_name, place_id)values ('월요일', 7);
insert into dates_tb (day_of_week_name, place_id)values ('화요일', 7);
insert into dates_tb (day_of_week_name, place_id)values ('수요일', 7);
insert into dates_tb (day_of_week_name, place_id)values ('토요일', 7);
insert into dates_tb (day_of_week_name, place_id)values ('일요일', 7);

insert into notice_tb (user_id, place_id, payment_id, content, status)
values (1, 1, 1, '내용1', 'WAIT');
insert into notice_tb (user_id, place_id, payment_id, content, status)
values (2, 2, 2, '내용2', 'WAIT');
insert into notice_tb (user_id, place_id, payment_id, content, status)
values (3, 3, 3, '내용3', 'WAIT');

insert into hashtag_tb (hashtag_name, place_id)values ('파티룸', 1);
insert into hashtag_tb (hashtag_name, place_id)values ('주차장', 1);
insert into hashtag_tb (hashtag_name, place_id)values ('파티', 1);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('부산연습실', 1);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('서울카페', 1);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('제주한달살기', 1);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('강남룸메이트', 1);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('홍대스튜디오',1);

insert into hashtag_tb (hashtag_name, place_id)values ('파티룸', 2);
insert into hashtag_tb (hashtag_name, place_id)values ('주차장', 2);
insert into hashtag_tb (hashtag_name, place_id)values ('파티', 2);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('제주한달살기', 2);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('강남룸메이트', 2);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('홍대스튜디오',2);

insert into hashtag_tb (hashtag_name, place_id)values ('파티룸', 3);
insert into hashtag_tb (hashtag_name, place_id)values ('주차장', 3);
insert into hashtag_tb (hashtag_name, place_id)values ('파티', 3);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('부산연습실', 3);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('서울카페', 3);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('홍대스튜디오',3);

insert into hashtag_tb (hashtag_name, place_id)values ('파티룸', 4);
insert into hashtag_tb (hashtag_name, place_id)values ('주차장', 4);
insert into hashtag_tb (hashtag_name, place_id)values ('파티', 4);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('서울카페', 4);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('강남룸메이트', 4);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('홍대스튜디오',4);

insert into hashtag_tb (hashtag_name, place_id)values ('파티룸', 5);
insert into hashtag_tb (hashtag_name, place_id)values ('주차장', 5);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('제주한달살기', 5);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('강남룸메이트', 5);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('홍대스튜디오',5);

insert into hashtag_tb (hashtag_name, place_id)values ('파티룸', 6);
insert into hashtag_tb (hashtag_name, place_id)values ('주차장', 6);
insert into hashtag_tb (hashtag_name, place_id)values ('파티', 6);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('제주한달살기', 6);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('강남룸메이트', 6);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('홍대스튜디오',6);

insert into hashtag_tb (hashtag_name, place_id)values ('파티룸', 7);
insert into hashtag_tb (hashtag_name, place_id)values ('주차장', 7);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('부산연습실', 7);
INSERT INTO hashtag_tb (hashtag_name, place_id)VALUES ('홍대스튜디오',7);

insert into file_info_tb(type) values ('PLACE');
insert into file_info_tb(type) values ('FACILITY');

insert into file_tb(place_id, file_info_id, file_name, file_url, status) values (1, 1, '8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'WAIT');
insert into file_tb(place_id, file_info_id, file_name, file_url, status) values (2, 2, 'aa.jpg', 'https://news.dbhasjuhwuha.com/wp-content/uploads/2021/08/aa.jpg', 'WAIT');
insert into file_tb(place_id, file_info_id, file_name, file_url, status) values (3, 2, 'bb8.jpg', 'https://news.ahjsbaghuwssplay.com/wp-content/uploads/2011/08/bb8.jpg', 'WAIT');

insert into fcm_tb(user_id, target_token) values (null,'dVimDFTAQJCHMrFDJD2W18:APA91bFef_eC8HUP_PPjtGnt3_1hJR4m-BJMDr2PSfFqA9eNtnYh4XTOqCStmPKnWgv6XDCkzur7kCrxlvghvtTPttD58zYKrz8OhkZn8Pc40vO9YCRIpJhHPaMT3wEMEkF7l7TCZkDx');

commit;