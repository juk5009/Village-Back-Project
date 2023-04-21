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
        <a class="navbar-brand" href="/z/admin/main">Village</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-between" id="collapsibleNavbar">
            <ul class="navbar-nav">

                <c:choose>
                    <c:when test="${principal == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="/z/admin/host">호스트신청</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="/z/admin/loginForm">로그인</a>
                        </li>
                    </c:when>

                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="/z/admin/host">호스트신청</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/logout">로그아웃</a>
                        </li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>

    </div>
</nav>
<div class="container my-5 justify-content-center pt-5" style="height: 650px;">
    <div class="row justify-content-center pt-5">
        <!-- col-md => col-lg 변환 : div width 증가 -->
        <div class="col-lg-4 col-lg-4">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h2 class="text-center mb-4">로그인</h2>
                    <div class="tab-content ">
                    <form action="/z/admin/login" method="post">
                        <table class="table table-bordered">
                            <tr class="text-center">
                                <!-- input의 크기는 class="form-control-lg" 로 늘린다. -->
                                <td><input type="text" class="form-control-lg w-100" name="email"
                                           placeholder="email"></td>
                            </tr>

                            <tr class="text-center">
                                <td><input type="password" class="form-control-lg w-100" name="password"
                                           placeholder="password"></td>
                            </tr>
                        </table>
                        <button class="btn btn-danger btn-sm" style="float:right;">로그인</button>
                        <input type="checkbox" value="">로그인 상태 유지<br><br><br>
                    </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function acceptHost(id) {
        $.ajax({
            type: "post",
            url: "/users/host/"+id,
            dataType: "json"
        }).done((res) => {
            alert(res.msg);
            location.href = "/z/admin/host";
        }).fail((err) =>{
            alert(err.responseJSON.msg);
        });
    }
</script>

</body>
</html>

