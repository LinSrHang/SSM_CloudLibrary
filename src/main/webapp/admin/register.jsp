<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>

    <head>
        <title>用户注册</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    </head>

    <body>

        <form action="${pageContext.request.contextPath}/register" method="post" class="form-horizontal" role="form"
            id="registerForm">
            <h1 class="text-center">注册账号</h1>

            <div class="form-group">
                <label class="col-sm-4 control-label">用户名</label>
                <div class="col-sm-4">
                    <input type="text" name="user_name" class="form-control" id="user_name" placeholder="请输入用户名">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">用户邮箱</label>
                <div class="col-sm-4">
                    <input type="text" name="user_email" class="form-control" id="user_email" placeholder="请输入用户邮箱">
                    <input type="button" value="获取验证码" name="yzm" class="yzm" id="yzm">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">验证码</label>
                <div class="col-sm-4">
                    <input type="text" name="vc" class="form-control" id="vc" placeholder="请输入验证码">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">密码</label>
                <div class="col-sm-4">
                    <input type="password" name="user_password" class="form-control" id="user_password"
                        placeholder="请输入密码">
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-4 control-label">注册类型</label>
                <div class="col-sm-4">
                    <label class="radio-inline">
                        <input type="radio" name="user_role" value="USER"> 普通用户
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="user_role" value="ADMIN"> 管理员
                    </label>
                </div>
            </div>

            <div class="form-group btn-group-justified">
                <div class="col-sm-offset-5 col-sm-8">
                    <button type="submit" class="btn btn-default" id="zhuce">注&nbsp;&nbsp;册</button>
                    <button type="reset" class="btn btn-default" id="reset">重&nbsp;&nbsp;置</button>
                </div>
            </div>
        </form>
        <script>
            $(function () {
                $("#zhuce").click(function () {
                    var username = document.getElementById("username").value;
                    var password = document.getElementById("password").value;
                    var user_email = document.getElementById("user_email").value;
                    var role = $('input:radio:checked').val();
                    var vc = document.getElementById("vc").value;
                    if (username.length === 0) {
                        alert("请输入用户名！");
                        return;
                    }
                    if (user_email.length === 0) {
                        alert("请输入用户邮箱！");
                        return;
                    }
                    if (password.length === 0) {
                        alert("请输入密码！");
                        return;
                    }
                    if (role == null) {
                        alert("请填写注册类型！");
                        return;
                    }
                    if (vc.length === 0) {
                        alert("请输入验证码！");
                        return;
                    }
                });
                $(document).ready(function () {
                    var btn = document.getElementById("yzm");
                    btn.onclick = function () {
                        $.ajax({
                            type: 'POST',
                            url: '${pageContext.request.contextPath}/email/send',
                            data: {
                                user_email: document.getElementById("user_email").value
                            },
                            success: function (s) {
                                alert(s);
                            }
                        })
                    }
                })
            });
        </script>
    </body>

    </html>