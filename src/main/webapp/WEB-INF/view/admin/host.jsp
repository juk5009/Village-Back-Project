<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="container container-fluid mt-5">
    <div class="row">
        <div class="col">
            <h1>호스트 신청 페이지</h1>
            <hr/>
            <table class="table table-bordered">
                <thead>
                <tr class="text-center">
                    <th>번호</th>
                    <th>아이디</th>
                    <th>이메일</th>
                    <th>권한</th>
                    <th>가입일</th>
                    <th>호스트신청</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${hosts}" var="host">
                    <tr id="list-${host.user.id}" class="text-center">
                        <td>${host.user.id}</td>
                        <td>${host.user.name}</td>
                        <td>${host.user.email}</td>
                        <td>${host.user.role}</td>
                        <td>${host.user.createdAt}</td>
                        <td>
                            <button class="btn btn-danger btn-sm" onclick="acceptHost(${host.user.id})">수락</button>
                            <button class="btn btn-danger btn-sm" onclick="failHost(${host.user.id})">거절</button></td>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

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
            location.href = "/admin/main";
        }).fail((err) =>{
            alert(err.responseJSON.msg);
        });
    }
</script>

<%@ include file="../layout/footer.jsp" %>