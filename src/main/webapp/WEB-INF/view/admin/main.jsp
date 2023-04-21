<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="container container-fluid mt-5">
    <div class="row">
        <div class="col">
            <h1>관리자 페이지</h1>
            <hr/>
            <div class="col-md-4">
                <div class="list-group">
                    <ul class="nav nav-tabs">
                        <li class="nav-item">
                            <a class="nav-link list-group-item list-group-item-action active" data-toggle="tab" href="#userCare">회원관리</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link list-group-item list-group-item-action" data-toggle="tab" href="#placeCare">공간관리</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link list-group-item list-group-item-action" data-toggle="tab" href="#reservationCare">예약관리</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link list-group-item list-group-item-action" data-toggle="tab" href="#paymentCare">결제관리</a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="tab-content">
                <div class="tab-pane fade show active" id="userCare">
                    <table class="table table-bordered">
                        <thead>
                        <tr class="text-center">
                            <th>번호</th>
                            <th>아이디</th>
                            <th>이메일</th>
                            <th>권한</th>
                            <th>가입일</th>
                            <th>활성화상태</th>
                            <th>비고</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${userList}" var="user">
                            <tr id="list-${user.id}" class="text-center">
                                <td>${user.id}</td>
                                <td>${user.name}</td>
                                <td>${user.email}</td>
                                <td>${user.role}</td>
                                <td>${user.createdAt}</td>
                                <td>${user.status}</td>
                                <c:choose>
                                    <c:when test="${user.status == 'ACTIVE'}">
                                        <td><button class="btn btn-danger btn-sm" onclick="deleteByUserId(${user.id})">비활성화</button></td>
                                    </c:when>

                                    <c:otherwise>
                                        <td><button class="btn btn-danger btn-sm" onclick="activeByUserId(${user.id})">활성화</button></td>
                                    </c:otherwise>
                                </c:choose>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="placeCare">
                    <table class="table table-bordered">
                        <thead>
                        <tr class="text-center">
                            <th>번호</th>
                            <th>제목</th>
                            <th>주소</th>
                            <th>전화번호</th>
                            <th>시간당금액</th>
                            <th>활성화상태</th>
                            <th>비고</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${placeList}" var="place">
                            <tr id="list-${place.id}" class="text-center">
                                <td>${place.id}</td>
                                <td>${place.title}</td>
                                <td>${place.address.address}</td>
                                <td>${place.tel}</td>
                                <td>${place.pricePerHour}</td>
                                <td>${place.status}</td>
                                <c:choose>
                                    <c:when test="${place.status == 'ACTIVE'}">
                                        <td><button class="btn btn-danger btn-sm" onclick="deleteByPlaceId(${place.id})">비활성화</button></td> <!-- 삭제 버튼 추가 -->
                                    </c:when>
                                        <c:otherwise>
                                        <td><button class="btn btn-danger btn-sm" onclick="activeByPlaceId(${place.id})">활성화</button></td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="reservationCare">
                    <table class="table table-bordered">
                        <thead>
                        <tr class="text-center">
                            <th>번호</th>
                            <th>예약자</th>
                            <th>예약장소</th>
                            <th>예약인원</th>
                            <th>예약시간</th>
                            <th>예약금액</th>
                            <th>비고</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${reservationList}" var="reservation">
                            <tr id="list-${reservation.id}" class="text-center">
                                <td>${reservation.id}</td>
                                <td>${reservation.user.name}</td>
                                <td>${reservation.place.title}</td>
                                <td>${reservation.peopleNum}</td>
                                <td>${reservation.startTime} ~ ${reservation.endTime}</td>
                                <td>${reservation.place.pricePerHour}</td>
                                <td><button class="btn btn-danger btn-sm" onclick="deleteByReservationId(${reservation.id})">삭제</button></td> <!-- 삭제 버튼 추가 -->
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="paymentCare">
                    <table class="table table-bordered">
                        <thead>
                        <tr class="text-center">
                            <th>번호</th>
                            <th>결제자</th>
                            <th>결제장소</th>
                            <th>결제금액</th>
                            <th>비고</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${paymentList}" var="payment">
                            <tr id="list-${payment.id}" class="text-center">
                                <td>${payment.id}</td>
                                <td>${payment.user.name}</td>
                                <td>${payment.place.title}</td>
                                <td>${payment.totalPrice}</td>
                                <td><button class="btn btn-danger btn-sm" onclick="deleteByPaymentId(${payment.id})">삭제</button></td> <!-- 삭제 버튼 추가 -->
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<script>
    function deleteByUserId(id) {
        $.ajax({
            type: "delete",
            url: "/users/"+id ,
            dataType: "json"
        }).done((res) => {    // 20x 일때
            alert(res.msg);
            location.href = "/z/admin/main";
            $('#list-'+id).remove();
        }).fail((err) => {    // 40x , 50x 일때
            console.log(err);
            alert(err.responseJSON.msg);
        });
    }
    function deleteByPlaceId(id) {
        $.ajax({
            type: "delete",
            url: "/places/"+id ,
            dataType: "json"
        }).done((res) => {    // 20x 일때
            alert(res.msg);
            location.href = "/z/admin/main";
            $('#list-'+id).remove();
        }).fail((err) => {    // 40x , 50x 일때
            // console.log(err);
            alert(err.responseJSON.msg);
        });
    }
    function deleteByReservationId(id) {
        $.ajax({
            type: "delete",
            url: "/reservation/"+id ,
            dataType: "json"
        }).done((res) => {    // 20x 일때
            alert(res.msg);
            location.href = "/z/admin/main";
            $('#list-'+id).remove();
        }).fail((err) => {    // 40x , 50x 일때
            // console.log(err);
            alert(err.responseJSON.msg);
        });
    }
    function deleteByPaymentId(id) {
        $.ajax({
            type: "delete",
            url: "/payment/"+id ,
            dataType: "json"
        }).done((res) => {    // 20x 일때
            alert(res.msg);
            location.href = "/z/admin/main";
            $('#list-'+id).remove();
        }).fail((err) => {    // 40x , 50x 일때
            // console.log(err);
            alert(err.responseJSON.msg);
        });
    }

    function activeByUserId(id) {
        $.ajax({
            type: "post",
            url: "/users/"+id ,
            dataType: "json"
        }).done((res) => {    // 20x 일때
            alert(res.msg);
            // console.log(res);
            location.href = "/z/admin/main";
            $('#list-'+id).reload();
        }).fail((err) => {    // 40x , 50x 일때
            // console.log(err);
            alert(err.responseJSON.msg);
        });
    }

    function activeByPlaceId(id) {
        $.ajax({
            type: "post",
            url: "/places/"+id ,
            dataType: "json"
        }).done((res) => {    // 20x 일때
            alert(res.msg);
            // console.log(res);
            location.href = "/z/admin/main";
            $('#list-'+id).reload();
        }).fail((err) => {    // 40x , 50x 일때
            // console.log(err);
            alert(err.responseJSON.msg);
        });
    }

</script>
</body>
</html>


