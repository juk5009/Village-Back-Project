<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="container my-5 justify-content-center pt-5" style="height: 650px;">
    <div class="row justify-content-center pt-5">
        <!-- col-md => col-lg 변환 : div width 증가 -->
        <div class="col-lg-4 col-lg-4">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h2 class="text-center mb-4">로그인</h2>
                    <div class="tab-content ">
                    <form action="/admin/login" method="post">
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
            location.href = "/admin/host";
        }).fail((err) =>{
            alert(err.responseJSON.msg);
        });
    }
</script>
<%@ include file="../layout/footer.jsp" %>

