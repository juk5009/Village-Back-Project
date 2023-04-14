insert into user_tb(name, password, email, tel, role, profile) values ('cos', '1234', 'cos@nate.com', '010-1111-2222', 'USER', 'profile1')
insert into user_tb(name, password, email, tel, role, profile) values ('ssar1', '1234', 'ssar@nate.com', '010-1244-2232', 'USER', 'profile2')
insert into user_tb(name, password, email, tel, role, profile) values ('tan', '1234', 'tan@nate.com', '010-1411-2422', 'USER', 'profile3')

insert into address_tb(roadFullAddr, sggNm, zipNo, lat, lng) values ('부산 부산진구 중앙대로 688 한준빌딩 2층', '부산진구', '47296', '12', '15')
insert into address_tb(roadFullAddr, sggNm, zipNo, lat, lng) values ('부산 부산진구 중앙대로 688 한준빌딩 12층', '사상구', '43296', '111', '115')
insert into address_tb(roadFullAddr, sggNm, zipNo, lat, lng) values ('부산 부산진구 중앙대로 688 한준빌딩 22층', '사하구', '27296', '412', '125')


insert into chatRoom_tb(user, place) values (User, place)
insert into chatRoom_tb(user, place) values (User, place)
insert into chatRoom_tb(user, place) values (User, place)

insert into search_tb(user, keyword) values (User, 'keyword1')
insert into search_tb(user, keyword) values (User, 'keyword2')
insert into search_tb(user, keyword) values (User, 'keyword3')


insert into search_tb(user, place, count) values (User, Place, 1)
insert into search_tb(user, place, count) values (User, Place, 2)
insert into search_tb(user, place, count) values (User, Place, 3)

insert into chat_tb(user, send, chatRoom) values (User , '안녕', ChatRoom)
insert into chat_tb(user, send, chatRoom) values (User , '반가워',ChatRoom)
insert into chat_tb(user, send, chatRoom) values (User , '잘가', ChatRoom)

insert into category_tb(name) values ('스터디룸')
insert into category_tb(name) values ('연습실')
insert into category_tb(name) values ('공유 오피스')


insert into account_tb(user, accountNum) values (User,'123456789123')
insert into account_tb(user, accountNum) values (User,'1234545848721')
insert into account_tb(user, accountNum) values (User,'4556415615154')

insert into review_tb(user, starRating, content, image, likeCount) values(User, '5', '내용1', 'image1', 1)
insert into review_tb(user, starRating, content, image, likeCount) values(User, '5', '내용2', 'image2', 2)
insert into review_tb(user, starRating, content, image, likeCount) values(User, '5', '내용3', 'image3', 3)


insert into place_tb(user, title, address, tel, review, placeIntroductionInfo, guide, facilityInfo, hashtag, image, maxPeople, pricePerHour, startTime, endTime)
values(User, '제목1', Address, '전번1', Review, '공간정보1', '공간소개1', '시설정보1', '해쉬태그1', 'image1', 3, 30, now(), now())
insert into place_tb(user, title, address, tel, review, placeIntroductionInfo, guide, facilityInfo, hashtag, image, maxPeople, pricePerHour, startTime, endTime)
values(User, '제목2', Address, '전번2', Review, '공간정보2', '공간소개2', '시설정보2', '해쉬태그2', 'image2', 4, 30, now(), now())
insert into place_tb(user, title, address, tel, review, placeIntroductionInfo, guide, facilityInfo, hashtag, image, maxPeople, pricePerHour, startTime, endTime)
values(User, '제목3', Address, '전번3', Review, '공간정보3', '공간소개3', '시설정보3', '해쉬태그3', 'image3', 5, 30, now(), now())


insert into reservation_tb(user, place, date, startTime, endTime, peopleNum, status) values(User, Place, now(), now(), now(), 3, 'WAIT')
insert into reservation_tb(user, place, date, startTime, endTime, peopleNum, status) values(User, Place, now(), now(), now(), 4, 'WAIT')
insert into reservation_tb(user, place, date, startTime, endTime, peopleNum, status) values(User, Place, now(), now(), now(), 2, 'WAIT')

insert into payment_tb(user, place, reservation, status, totalPrice) values(User, Place, Reservation, 'WAIT', 10000)
insert into payment_tb(user, place, reservation, status, totalPrice) values(User, Place, Reservation, 'WAIT', 20000)
insert into payment_tb(user, place, reservation, status, totalPrice) values(User, Place, Reservation, 'WAIT', 30000)


insert into notice_tb(user, place, payment, content, status) values(User, Place, Payment, '내용1','WAIT')
insert into notice_tb(user, place, payment, content, status) values(User, Place, Payment, '내용2','WAIT')
insert into notice_tb(user, place, payment, content, status) values(User, Place, Payment, '내용3','WAIT')

