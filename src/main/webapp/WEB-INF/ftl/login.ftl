<!doctype html>
<html>
<head>
    <title>2017全国高校云计算应用创新大赛 - 登录</title>
    <meta charset="utf-8">
    <meta name="keywords" content="云计算,云计算大赛,全国高校云计算大赛,教育部,技能赛,创意赛,命题赛,创业赛,东南大学,ACM南京分会">
    <meta name="description" content="2017年-2018年第四届全国高校云计算应用创新大赛">
    <meta name="viewport" content="width=1200px, initial-scale=device-width/1200">
    <link rel="icon" href="favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="assets/css/cc.common.css">
    <link rel="stylesheet" type="text/css" href="assets/css/cc.header.css">
    <link rel="stylesheet" type="text/css" href="assets/css/cc.footer.css">
    <link rel="stylesheet" type="text/css" href="assets/css/cc.login.css">
    <link rel="stylesheet" type="text/css" href="assets/css/cc.textinput.css">
    <link rel="stylesheet" type="text/css" href="assets/css/buttons.css">
    <script type="text/javascript" language="javascript" src="assets/js/jquery-1.11.1.min.js"></script>
</head>
<body>
<div>
    <div id="header-top"></div>
    <div class="wrapper top-gap bottom-gap">
        <div id="header-logo" class="float-left"></div>
        <div id="header-code" class="float-right"></div>
        <div class="clear"></div>
    </div>
</div>
<div id="menu">
    <div class="wrapper">
        <div id="menu-container">
        </div>
    </div>
</div>
<div id="login-container" class="nano-top-gap nano-bottom-gap">
    <div class="wrapper">
        <div id="login-box">
            <div>
                <div class="login-box-top left selected"><a href="#">用户登录</a></div>
                <div class="login-box-top right"><a href="#">用户注册</a></div>
                <div class="clear"></div>
            </div>
            <div class="login-text-container top-gap">
                <input class="cc-textinput fake_text" id="fake_username" type="text">
                <input class="cc-textinput" id="real_username" type="text" name="username">
            </div>
            <div class="login-text-container nano-top-gap">
                <input class="cc-textinput fake_text" id="fake_password" type="text">
                <input class="cc-textinput" id="real_password" type="password" name="password">
            </div>
            <div class="login-button-container">
                <a id="btnLogin" class="button button-block button-rounded button-flat-primary button-large">安全登陆</a>
            </div>
        </div>
        <div id="login-pattern"></div>
        <div class="clear"></div>
    </div>
</div>
<div id="footer">
    <p>版权所有 @ 2017全国高校云计算应用创新大赛组委会
    <p>
</div>
<script type="text/javascript" src="assets/js/cc.header.js"></script>
<script type="text/javascript" language="javascript">
    function initMessage() {
        $('#fake_username').val("请输入用户名...");
        $('#fake_password').val("请输入密码...");
        $('#fake_username').removeClass("warning_text");
        $('#fake_password').removeClass("warning_text");
    }
    function showWrongUsername(message) {
        $('#real_username').hide();
        $('#fake_username').show();
        $('#fake_username').val(message);
        $('#fake_username').addClass("warning_text");
        showErrorAnimation();
    }
    function showWrongPassword(message) {
        $('#real_password').hide();
        $('#fake_password').show();
        $('#fake_password').val(message);
        $('#fake_password').addClass("warning_text");
        showErrorAnimation();
    }
    function showErrorAnimation() {
        $('#login-box').shake();
    }
    $(document).ready(function () {
        initMessage();
        $('#real_username').hide();
        $('#real_password').hide();
        $('#fake_username').show();
        $('#fake_password').show();
    });
    $('#fake_username').focus(function () {
        $(this).hide();
        $('#real_username').show().focus();
    })
    $('#real_username').blur(function () {
        if ($(this).val() == "") {
            $(this).hide();
            $('#fake_username').show();
        }
        ;
    });
    $('#fake_password').focus(function () {
        $(this).hide();
        $('#real_password').show().focus();
    })
    $('#real_password').blur(function () {
        if ($(this).val() == "") {
            $(this).hide();
            $('#fake_password').show();
        }
        ;
    });
    $('#btnLogin').click(function () {

        var username = $('#real_username').val();
        var password = $('#real_password').val();
        $('#real_username').val("");
        $('#real_password').val("");
        if (username == "") {
            showWrongUsername("用户名不能为空...");
            return;
        }
        if (password == "") {
            showWrongPassword("密码不能为空...");
            return;
        }
        initMessage();
        $.post("login", {username: username, password: password}, function (data) {
            if (data == 0) {
                alert("登录名不存在。");
            } else if (data == 2) {
                alert("密码错误！请重试。");
            } else if (data == 3) {
                location.href = "download";
            } else
                location.href = "award";
        });
    });
</script>
</body>
</html>