insert into user_tb (name, password, email, tel, role, profile, created_at)
values ('ssar', '$2a$10$U02MC9ZTsjf9DwysQcEqz.nZ3az2cGhxPPgUBAiJ0I27/7vao5bx2', 'ssar@naver.com', '01012345678', 'USER',
        '/images/dora.png', NOW());
insert into user_tb (name, password, email, tel, role, profile, created_at)
values ('Jane', '$2a$10$U02MC9ZTsjf9DwysQcEqz.nZ3az2cGhxPPgUBAiJ0I27/7vao5bx2', 'Jane@naver.com', '01023455678',
        'MANAGER', '/images/dora.png', NOW());
insert into user_tb (name, password, email, tel, role, profile, created_at)
values ('Bob', '$2a$10$U02MC9ZTsjf9DwysQcEqz.nZ3az2cGhxPPgUBAiJ0I27/7vao5bx2', 'Bob@naver.com', '01067895678', 'ADMIN',
        '/images/dora.png', NOW());

insert into address_tb(road_full_addr, sgg_nm, zip_no, lat, lng)
values ('부산 부산진구 중앙대로 688 한준빌딩 2층', '부산진구', '47296', '12', '15');
insert into address_tb(road_full_addr, sgg_nm, zip_no, lat, lng)
values ('부산 부산진구 중앙대로 688 한준빌딩 12층', '사상구', '43296', '111', '115');
insert into address_tb(road_full_addr, sgg_nm, zip_no, lat, lng)
values ('부산 부산진구 중앙대로 688 한준빌딩 22층', '사하구', '27296', '412', '125');

insert into facility_info_tb (facility_name)
values ('카페');
insert into facility_info_tb (facility_name)
values ('화장실');
insert into facility_info_tb (facility_name)
values ('수유실');

insert into hashtag_tb (hashtag_name)
values ('가까운곳');
insert into hashtag_tb (hashtag_name)
values ('저렴한곳');
insert into hashtag_tb (hashtag_name)
values ('내주변인곳');

insert into category_tb (category_name)
values ('연습실');
insert into category_tb (category_name)
values ('스터디룸');
insert into category_tb (category_name)
values ('공유오피스');

insert into account_tb(user_id, account_num)
values (1, '123456-01-123456');
insert into account_tb(user_id, account_num)
values (1, '123434-01-123354');
insert into account_tb(user_id, account_num)
values (1, '333456-01-111244');


insert into review_tb (user_id, star_rating, content, image, like_count, created_at)
values (1, 5, '좋은 상품이에요', NULL, 10, now());
insert into review_tb (user_id, star_rating, content, image, like_count, created_at)
values (2, 4, '조금 아쉬운 부분도 있지만 전체적으로 만족스러웠어요', NULL, 5, now());
insert into review_tb (user_id, star_rating, content, image, like_count, created_at)
values (3, 3, '그저 그랬어요', NULL, 2, now());

insert into place_tb (user_id, title, address_id, tel, notice, place_introduction_info, max_people, price_per_hour,
                      start_time, end_time)
values (1, '좋은 공간', 1, '01012345678', '좋은 공간입니다.', '좋은 공간입니다. 이용해보세요!', 10, 5000, NOW(), NOW());
insert into place_tb (user_id, title, address_id, tel, notice, place_introduction_info, max_people, price_per_hour,
                      start_time, end_time)
values (2, '멋진 공간', 2, '01012345679', '멋진 공간입니다.', '멋진 공간입니다. 이용해보세요!', 10, 4000, NOW(), NOW());
insert into place_tb (user_id, title, address_id, tel, notice, place_introduction_info, max_people, price_per_hour,
                      start_time, end_time)
values (3, '편안한 공간', 3, '01012345680', '편안한 공간입니다.', '편안한 공간입니다. 이용해보세요!', 10000, 3, NOW(), NOW());


insert into reservation_tb (user_id, place_id, date, start_time, end_time, people_num)
values (1, 1, '2023-04-20', '2021-01-01T00:01', '2021-01-01T20:01', 2);
insert into reservation_tb (user_id, place_id, date, start_time, end_time, people_num)
values (2, 2, '2023-04-20', '2021-01-01T00:01', '2021-01-02T00:01', 3);
insert into reservation_tb (user_id, place_id, date, start_time, end_time, people_num)
values (1, 1, '2023-04-20', '2021-01-01T00:01', '2022-01-01T00:01', 4);

insert into scrap_tb (user_id, place_id, count)
values (1, 1, 3);
insert into scrap_tb (user_id, place_id, count)
values (1, 2, 5);
insert into scrap_tb (user_id, place_id, count)
values (2, 1, 4);

insert into chat_room_tb (user_id, place_id, created_at)
values (1, 1, NOW());
insert into chat_room_tb (user_id, place_id, created_at)
values (2, 2, NOW());
insert into chat_room_tb (user_id, place_id, created_at)
values (3, 3, NOW());

insert into search_tb (user_id, keyword)
values (1, '연습실');
insert into search_tb (user_id, keyword)
values (1, '스터디룸');
insert into search_tb (user_id, keyword)
values (1, '커피숍');

insert into chat_tb (user_id, send, chat_room_id, created_at)
values (1, '안녕하세요!', 1, NOW());
insert into chat_tb (user_id, send, chat_room_id, created_at)
values (2, '반갑습니다!', 1, NOW());
insert into chat_tb (user_id, send, chat_room_id, created_at)
values (1, '어디에 계시나요?', 1, NOW());
insert into chat_tb (user_id, send, chat_room_id, created_at)
values (2, '서울에 있습니다.', 1, NOW());

insert into payment_tb (user_id, place_id, reservation_id, status, total_price)
values (1, 1, 1, '결제완료', 30000);
insert into payment_tb (user_id, place_id, reservation_id, status, total_price)
values (1, 1, 1, '결제대기', 20000);
insert into payment_tb (user_id, place_id, reservation_id, status, total_price)
values (1, 1, 1, '환불완료', 10000);
--
insert into dates_tb (day_of_week_name, dates_id, place_id)
values ('월요일,화요일,수요일,목요일', 1, 1);
insert into dates_tb (day_of_week_name, dates_id, place_id)
values ('월요일,목요일,금요일,토요일', 2, 1);
insert into dates_tb (day_of_week_name, dates_id, place_id)
values ('수요일', 2, 2);

insert into notice_tb (user_id, place_id, payment_id, content, status)
values (1, 1, 1, '내용1', 'WAIT');
insert into notice_tb (user_id, place_id, payment_id, content, status)
values (2, 2, 2, '내용2', 'WAIT');
insert into notice_tb (user_id, place_id, payment_id, content, status)
values (3, 3, 3, '내용3', 'WAIT');


commit;



-- insert into user_tb(name, password, email, tel, role, profile) values ('cos', '1234', 'cos@nate.com', '010-1111-2222', 'USER', 'profile1')
-- insert into user_tb(name, password, email, tel, role, profile) values ('ssar1', '1234', 'ssar@nate.com', '010-1244-2232', 'USER', 'profile2')
-- insert into user_tb(name, password, email, tel, role, profile) values ('tan', '1234', 'tan@nate.com', '010-1411-2422', 'USER', 'profile3')
--
-- insert into address_tb(road_full_addr, sgg_nm, zip_no, lat, lng) values ('부산 부산진구 중앙대로 688 한준빌딩 2층', '부산진구', '47296', '12', '15')
-- insert into address_tb(road_full_addr, sgg_nm, zip_no, lat, lng) values ('부산 부산진구 중앙대로 688 한준빌딩 12층', '사상구', '43296', '111', '115')
-- insert into address_tb(road_full_addr, sgg_nm, zip_no, lat, lng) values ('부산 부산진구 중앙대로 688 한준빌딩 22층', '사하구', '27296', '412', '125')
--
--
-- insert into chatRoom_tb(user_id, place_id) values (1, 2)
-- insert into chatRoom_tb(user_id, place_id) values (1, 1)
-- insert into chatRoom_tb(user_id, place_id) values (2, 1)
--
-- insert into search_tb(user_id, keyword) values (1, 'keyword1')
-- insert into search_tb(user_id, keyword) values (2, 'keyword2')
-- insert into search_tb(user_id, keyword) values (1, 'keyword3')
--
--
-- insert into search_tb(user_id, place_id, count) values (1, 2, 1)
-- insert into search_tb(user_id, place_id, count) values (2, 2, 2)
-- insert into search_tb(user_id, place_id, count) values (1, 1, 3)
--
-- insert into chat_tb(user_id, send, chat_room_id) values (1 , '안녕', 1)
-- insert into chat_tb(user_id, send, chat_room_id) values (2 , '반가워',1)
-- insert into chat_tb(user_id, send, chat_room_id) values (1 , '잘가', 2)
--
-- insert into category_tb(name) values ('스터디룸')
-- insert into category_tb(name) values ('연습실')
-- insert into category_tb(name) values ('공유 오피스')
--
--
-- insert into account_tb(user_id, account_num) values (1,'123456789123')
-- insert into account_tb(user_id, account_num) values (1,'1234545848721')
-- insert into account_tb(user_id, account_num) values (2,'4556415615154')
--
-- insert into review_tb(user_id, star_rating, content, image, like_count) values(1, '5', '내용1', 'image1', 1)
-- insert into review_tb(user_id, star_rating, content, image, like_count) values(1, '5', '내용2', 'image2', 2)
-- insert into review_tb(user_id, star_rating, content, image, like_count) values(2, '5', '내용3', 'image3', 3)
--
--
-- insert into place_tb(user_id, title, address, tel, review, place_introduction_info, notice, image, max_people, price_per_hour, start_time, end_time)
-- values(1, '제목1', address_id, '전번1', '공간정보1', '공간소개1', 3, 30, now(), now())
-- insert into place_tb(user_id, title, address, tel, review, place_introduction_info, notice, image, max_people, price_per_hour, start_time, end_time)
-- values(2, '제목2', address_id, '전번2', '공간정보2', '공간소개2', 4, 30, now(), now())
-- insert into place_tb(user_id, title, address, tel, review, place_introduction_info, notice, image, max_people, price_per_hour, start_time, end_time)
-- values(1, '제목3', address_id, '전번3', '공간정보3', '공간소개3', 5, 30, now(), now())
--
--
-- insert into reservation_tb(user_id, place_id, date, startTime, endTime, peopleNum, status) values(1 , 2, now(), now(), now(), 3, 'WAIT')
-- insert into reservation_tb(user_id, place_id, date, startTime, endTime, peopleNum, status) values(1 , 1, now(), now(), now(), 4, 'WAIT')
-- insert into reservation_tb(user_id, place_id, date, startTime, endTime, peopleNum, status) values(2 , 2, now(), now(), now(), 2, 'WAIT')
--
-- insert into payment_tb(user_id, place_id, reservation, status, totalPrice) values(1 , 2, Reservation, 'WAIT', 10000)
-- insert into payment_tb(user_id, place_id, reservation, status, totalPrice) values(1 , 1, Reservation, 'WAIT', 20000)
-- insert into payment_tb(user_id, place_id, reservation, status, totalPrice) values(2 , 2, Reservation, 'WAIT', 30000)
--
--
-- insert into notice_tb(user_id, place_id, payment_id, content, status) values(1, 2 , 3, '내용1','WAIT')
-- insert into notice_tb(user_id, place_id, payment_id, content, status) values(1, 1 , 2, '내용2','WAIT')
-- insert into notice_tb(user_id, place_id, payment_id, content, status) values(1, 2 , 2, '내용3','WAIT')
--
-- insert into file_info_tb(type) values ('PLACE');
-- insert into file_info_tb(type) values ('FACILITY');
--
-- insert into file_tb(file_info_id, file_name, file_url, status) values (1, '8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'WAIT');
-- insert into file_tb(file_info_id, file_name, file_url, status) values (2, 'aa.jpg', 'https://news.dbhasjuhwuha.com/wp-content/uploads/2021/08/aa.jpg', 'WAIT');
-- insert into file_tb(file_info_id, file_name, file_url, status) values (2, 'bb8.jpg', 'https://news.ahjsbaghuwssplay.com/wp-content/uploads/2011/08/bb8.jpg', 'WAIT');
--
-- commit;
