insert into user_tb (name, password, email, tel, role, profile, created_at)
values ('ssar', '$2a$10$AY7h/0uPoe5UtfMZxmxNq.d4KOTZiKbZFvbM3k7vlsfpjAJd0fTvW', 'ssar@naver.com', '01012345678', 'USER', '/images/dora.png', NOW());
insert into user_tb (name, password, email, tel, role, profile, created_at)
values ('Jane', '$2a$10$AY7h/0uPoe5UtfMZxmxNq.d4KOTZiKbZFvbM3k7vlsfpjAJd0fTvW', 'Jane@naver.com', '01023455678', 'HOST', '/images/dora.png', NOW());
insert into user_tb (name, password, email, tel, role, profile, created_at)
values ('Bob', '$2a$10$AY7h/0uPoe5UtfMZxmxNq.d4KOTZiKbZFvbM3k7vlsfpjAJd0fTvW', 'Bob@naver.com', '01067895678', 'ADMIN', '/images/dora.png', NOW());

insert into address_tb(address, sigungu, zonecode, detail_address, x, y)
values ('부산 부산진구 중앙대로 688 한준빌딩 2층', '부산 부산진구', '47296', '201호' ,'12', '15');
insert into address_tb(address, sigungu, zonecode, detail_address, x, y)
values ('부산 부산진구 중앙대로 688 한준빌딩 3층', '부산 부산진구', '47296', '301호' ,'112', '115');
insert into address_tb(address, sigungu, zonecode, detail_address, x, y)
values ('부산 부산진구 중앙대로 688 한준빌딩 4층', '부산 부산진구', '47296', '401호' ,'12', '215');



insert into account_tb(user_id, account_num)
values (1, '123456-01-123456');
insert into account_tb(user_id, account_num)
values (1, '123434-01-123354');
insert into account_tb(user_id, account_num)
values (1, '333456-01-111244');



insert into place_tb (user_id, title, address_id, tel, notice, place_introduction_info, max_people, max_parking,
                      price_per_hour,
                      start_time, end_time)
values (1, '스튜디오 르온드', 1, '01012345678', '좋은 공간입니다.', '좋은 공간입니다. 이용해보세요!', 10, 5, 4000, NOW(), NOW());
insert into place_tb (user_id, title, address_id, tel, notice, place_introduction_info, max_people, max_parking,
                      price_per_hour,
                      start_time, end_time)
values (2, '스튜디오 STUDIO', 2, '01012345679', '멋진 공간입니다.', '멋진 공간입니다. 이용해보세요!', 10, 10, 5000, NOW(), NOW());
insert into place_tb (user_id, title, address_id, tel, notice, place_introduction_info, max_people, max_parking,
                      price_per_hour,
                      start_time, end_time)
values (3, '업타운댄스뮤직 아카데미', 3, '01012345680', '편안한 공간입니다.', '편안한 공간입니다. 이용해보세요!', 10, 15, 3000, NOW(), NOW());


insert into review_tb (user_id, place_id, star_rating, content, image, like_count, created_at)
values (1, 1, 5, '좋은 상품이에요', NULL, 10, now());
insert into review_tb (user_id, place_id, star_rating, content, image, like_count, created_at)
values (2, 2, 4, '조금 아쉬운 부분도 있지만 전체적으로 만족스러웠어요', NULL, 5, now());
insert into review_tb (user_id, place_id, star_rating, content, image, like_count, created_at)
values (3, 3, 3, '그저 그랬어요', NULL, 2, now());




insert into facility_info_tb (facility_name, place_id)
values ('카페', 1);
insert into facility_info_tb (facility_name, place_id)
values ('화장실', 2);
insert into facility_info_tb (facility_name, place_id)
values ('주차장', 3);


insert into category_tb (category_name, place_id)
values ('연습실', 1);
insert into category_tb (category_name, place_id)
values ('스터디룸', 2);
insert into category_tb (category_name, place_id)
values ('공유오피스', 3);


insert into reservation_tb (user_id, place_id, date, start_time, end_time, people_num, status)
values (1, 1, '2023-04-20', '2021-01-01T00:01', '2021-01-01T04:01', 2, 'WAIT');
insert into reservation_tb (user_id, place_id, date, start_time, end_time, people_num, status)
values (2, 2, '2023-04-20', '2021-01-02T00:01', '2021-01-03T00:01', 3, 'COMPLETE');
insert into reservation_tb (user_id, place_id, date, start_time, end_time, people_num, status)
values (1, 1, '2023-04-20', '2021-01-01T10:01', '2021-01-01T20:01', 4, 'FAIL');

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
values (1, 1, 1, 'WAIT', 30000);
insert into payment_tb (user_id, place_id, reservation_id, status, total_price)
values (1, 2, 1, 'COMPLETE', 20000);
insert into payment_tb (user_id, place_id, reservation_id, status, total_price)
values (2, 3, 2, 'FAIL', 10000);

insert into dates_tb (day_of_week_name, place_id)
values ('월요일', 1);
insert into dates_tb (day_of_week_name, place_id)
values ('화요일', 1);
insert into dates_tb (day_of_week_name, place_id)
values ('수요일',  2);

insert into notice_tb (user_id, place_id, payment_id, content, status)
values (1, 1, 1, '내용1', 'WAIT');
insert into notice_tb (user_id, place_id, payment_id, content, status)
values (2, 2, 2, '내용2', 'WAIT');
insert into notice_tb (user_id, place_id, payment_id, content, status)
values (3, 3, 3, '내용3', 'WAIT');

insert into hashtag_tb (hashtag_name, place_id)
values ('파티룸', 1);
insert into hashtag_tb (hashtag_name, place_id)
values ('파티', 2);
insert into hashtag_tb (hashtag_name, place_id)
values ('부산연습실', 3);

insert into file_info_tb(type) values ('PLACE');
insert into file_info_tb(type) values ('FACILITY');

insert into file_tb(file_info_id, file_name, file_url, status) values (1, '8.jpg', 'https://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg', 'WAIT');
insert into file_tb(file_info_id, file_name, file_url, status) values (2, 'aa.jpg', 'https://news.dbhasjuhwuha.com/wp-content/uploads/2021/08/aa.jpg', 'WAIT');
insert into file_tb(file_info_id, file_name, file_url, status) values (2, 'bb8.jpg', 'https://news.ahjsbaghuwssplay.com/wp-content/uploads/2011/08/bb8.jpg', 'WAIT');

insert into fcm_tb(user_id, target_token) values (null,'dVimDFTAQJCHMrFDJD2W18:APA91bFef_eC8HUP_PPjtGnt3_1hJR4m-BJMDr2PSfFqA9eNtnYh4XTOqCStmPKnWgv6XDCkzur7kCrxlvghvtTPttD58zYKrz8OhkZn8Pc40vO9YCRIpJhHPaMT3wEMEkF7l7TCZkDx');

commit;