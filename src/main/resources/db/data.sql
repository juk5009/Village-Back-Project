insert into user_tb (name, password, email, tel, role, profile, created_at) values ('ssar', '1234', 'ssar@naver.com', '01012345678', 'USER', '/images/dora.png', NOW());
insert into user_tb (name, password, email, tel, role, profile, created_at) values ('Jane', '1234', 'Jane@naver.com', '01023455678', 'MANAGER', '/images/dora.png', NOW());
insert into user_tb (name, password, email, tel, role, profile, created_at) values ('Bob', '1234', 'Bob@naver.com', '01067895678', 'ADMIN', '/images/dora.png', NOW());

insert into address_tb(road_full_addr, sgg_nm, zip_no, lat, lng) values ('부산 부산진구 중앙대로 688 한준빌딩 2층', '부산진구', '47296', '12', '15');
insert into address_tb(road_full_addr, sgg_nm, zip_no, lat, lng) values ('부산 부산진구 중앙대로 688 한준빌딩 12층', '사상구', '43296', '111', '115');
insert into address_tb(road_full_addr, sgg_nm, zip_no, lat, lng) values ('부산 부산진구 중앙대로 688 한준빌딩 22층', '사하구', '27296', '412', '125');

insert into facility_info_tb (facility_name) values ('카페');
insert into facility_info_tb (facility_name) values ('화장실');
insert into facility_info_tb (facility_name) values ('수유실');

insert into hashtag_tb (hashtag_name) values ('가까운곳');
insert into hashtag_tb (hashtag_name) values ('저렴한곳');
insert into hashtag_tb (hashtag_name) values ('내주변인곳');

insert into account_tb(user_id, account_num) values (1, '123456-01-123456');
insert into account_tb(user_id, account_num) values (1, '123434-01-123354');
insert into account_tb(user_id, account_num) values (1, '333456-01-111244');


insert into review_tb (user_id, star_rating, content, image, like_count, created_at) values (1, 5, '좋은 상품이에요', NULL, 10, now());
insert into review_tb (user_id, star_rating, content, image, like_count, created_at) values (2, 4, '조금 아쉬운 부분도 있지만 전체적으로 만족스러웠어요', NULL, 5, now());
insert into review_tb (user_id, star_rating, content, image, like_count, created_at) values (3, 3, '그저 그랬어요', NULL, 2, now());

-- insert into place_tb (user_id, title, address_id, tel, review_id, place_introduction_info, guide, max_people, price_per_hour, start_time, end_time, category_id) values (1, '좋은 공간', 1, '01012345678', 1, '좋은 공간입니다.', '좋은 공간입니다. 이용해보세요!', 10, 5, NOW(), NOW(), 1);
-- insert into place_tb (user_id, title, address_id, tel, review_id, place_introduction_info, guide, max_people, price_per_hour, start_time, end_time, category_id) values (2, '멋진 공간', 2, '01012345679', 2, '멋진 공간입니다.', '멋진 공간입니다. 이용해보세요!', 10, 4, NOW(), NOW(), 2);
-- insert into place_tb (user_id, title, address_id, tel, review_id, place_introduction_info, guide, max_people, price_per_hour, start_time, end_time, category_id) values (3, '편안한 공간', 3, '01012345680', 3, '편안한 공간입니다.', '편안한 공간입니다. 이용해보세요!', 10, 3, NOW(), NOW(), 3);
--

-- insert into chatroom_tb (user_id, place_id, created_at) values (1, 1, NOW());
-- insert into chatroom_tb (user_id, place_id, created_at) values (2, 2, NOW());
-- insert into chatroom_tb (user_id, place_id, created_at) values (3, 3, NOW());

--
insert into search_tb (user_id, keyword) values (1, '연습실');
insert into search_tb (user_id, keyword) values (1, '스터디룸');
insert into search_tb (user_id, keyword) values (1, '커피숍');
--
-- insert into chat_tb (user_id, send, chatroom_id, created_at) values (1, '안녕하세요!', 1, NOW());
-- insert into chat_tb (user_id, send, chatroom_id, created_at) values (2, '반갑습니다!', 1, NOW());
-- insert into chat_tb (user_id, send, chatroom_id, created_at) values (1, '어디에 계시나요?', 1, NOW());
-- insert into chat_tb (user_id, send, chatroom_id, created_at) values (2, '서울에 있습니다.', 1, NOW());
--
--
-- insert into payment_tb (user_id, place_id, reservation_id, status, total_price) values (1, 1, 1, '결제완료', 30000);
-- insert into payment_tb (user_id, place_id, reservation_id, status, total_price) values (1, 1, 1, '결제대기', 20000);
-- insert into payment_tb (user_id, place_id, reservation_id, status, total_price) values (1, 1, 1, '환불완료', 10000);
--
insert into date_tb (day_of_week) values ('월요일');
insert into date_tb (day_of_week) values ('화요일');
insert into date_tb (day_of_week) values ('수요일');

insert into category_tb (category_name) values ('연습실');
insert into category_tb (category_name) values ('스터디룸');
insert into category_tb (category_name) values ('공유오피스');



--
-- insert into notice_tb (user_id, place_id, payment_id, content, status) values (1, 1, 1, '내용1', 'WAIT');
-- insert into notice_tb (user_id, place_id, payment_id, content, status) values (2, 2, 2, '내용2', 'WAIT');
-- insert into notice_tb (user_id, place_id, payment_id, content, status) values (3, 3, 3, '내용3', 'WAIT');


commit;