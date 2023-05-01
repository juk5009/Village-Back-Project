<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <%-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script> --%>
    <script src="https://kit.fontawesome.com/32aa2b8683.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <title>Document</title>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Village</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-between" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/hostForm">호스트신청</a>
                </li>
            </ul>
        </div>

    </div>
</nav>


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
                            <th>호스트신청</th>
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
                                <td><button class="btn btn-danger btn-sm" onclick="acceptHost(${user.id})">수락</button>
                                    <button class="btn btn-danger btn-sm" onclick="failHost(${user.id})">거절</button></td>
                                <td><button class="btn btn-danger btn-sm" onclick="deleteByUserId(${user.id})">삭제</button>
                                </td>
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
                            <th>비고</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${placeList}" var="place">
                            <tr id="list-${place.id}" class="text-center">
                                <td>${place.id}</td>
                                <td>${place.title}</td>
                                <td>${place.address.roadFullAddr}</td>
                                <td>${place.tel}</td>
                                <td>${place.pricePerHour}</td>
                                <td><button class="btn btn-danger btn-sm" onclick="deleteByBoardId(${place.id})">삭제</button></td> <!-- 삭제 버튼 추가 -->
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
                                <td>${reservation.place.pricePerHour}</td>
                                <td><button class="btn btn-danger btn-sm" onclick="deleteByReplyId(${reservation.id})">삭제</button></td> <!-- 삭제 버튼 추가 -->
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
                                <td><button class="btn btn-danger btn-sm" onclick="deleteByReplyId(${payment.id})">삭제</button></td> <!-- 삭제 버튼 추가 -->
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
            url: "/user/"+id ,
            dataType: "json"
        }).done((res) => {    // 20x 일때
            alert(res.msg);
            location.href = "/admin";
            $('#list-'+id).remove();
        }).fail((err) => {    // 40x , 50x 일때
            console.log(err);
            alert(err.responseJSON.msg);
        });
    }
    function deleteByBoardId(id) {
        $.ajax({
            type: "delete",
            url: "/board/"+id ,
            dataType: "json"
        }).done((res) => {    // 20x 일때
            alert(res.msg);
            location.href = "/admin";
            $('#list-'+id).remove();
        }).fail((err) => {    // 40x , 50x 일때
            // console.log(err);
            alert(err.responseJSON.msg);
        });
    }
    function deleteByReplyId(id) {
        $.ajax({
            type: "delete",
            url: "/reply/"+id ,
            dataType: "json"
        }).done((res) => {    // 20x 일때
            alert(res.msg);
            location.href = "/admin";
            $('#list-'+id).remove();
        }).fail((err) => {    // 40x , 50x 일때
            // console.log(err);
            alert(err.responseJSON.msg);
        });
    }
</script>
</body>
</html>


