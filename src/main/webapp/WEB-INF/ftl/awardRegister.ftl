<!doctype html>
<html>
<head>
    <title>2017全国高校云计算应用创新大赛 - 登录</title>
    <meta charset="utf-8">
    <meta name="keywords" content="云计算,云计算大赛,全国高校云计算大赛,教育部,技能赛,创意赛,命题赛,创业赛,东南大学,ACM南京分会">
    <meta name="description" content="2017年-2018年第四届全国高校云计算应用创新大赛">
    <meta name="viewport" content="width=1200px, initial-scale=device-width/1200">
    <link rel="icon" href="assets/img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="assets/css/cc.common.css">
    <link rel="stylesheet" type="text/css" href="assets/css/cc.header.css">
    <link rel="stylesheet" type="text/css" href="assets/css/cc.footer.css">
    <link rel="stylesheet" type="text/css" href="assets/css/cc.center.css">
    <link rel="stylesheet" type="text/css" href="assets/css/cc.uploadfile.css">
    <link rel="stylesheet" type="text/css" href="assets/css/buttons.css">
    <link rel="stylesheet" type="text/css" href="assets/css/cc.bootstrap-table.css">
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
<div id="center-container" class="nano-top-gap nano-bottom-gap">
    <div class="wrapper">
        <div id="center-inner-container">
            <div id="center-menu">
                <ul>
                    <li class="even"><a href="award">获奖信息</a></li>
                    <li class="odd"><a href="addressRegister">奖状邮寄</a></li>
                <#if Session.money??>
                    <li class="even"><a href="awardRegister">奖金颁发</a></li>
                    <li class="odd"><a href="logout">注销</a></li>
                <#else>
                    <li class="even"><a href="logout">注销</a></li>
                </#if>
                </ul>
            </div>
            <div id="center-context-container">
                <h1 class="nano-left-gap">奖状邮寄</h1>
                <hr class="nano-left-gap nano-right-gap">
                <div class="mess-container nano-left-gap nano-right-gap nano-top-gap">
                    <div class="table-container nano-left-gap nano-right-gap nano-top-gap">
                        <div class="center">
                            <table style="margin: auto; border-spacing: 10px; border-collapse: separate"
                                   cellspacing="10">
                            <#if awd??>
                                <tr>
                                    <td>账户姓名：</td>
                                    <td><input type="text" name="username" value="${awd.username}"></td>
                                </tr>
                                <tr>
                                    <td>身份证号：</td>
                                    <td><input type="text" name="idnum" value="${awd.idnum}"></td>
                                </tr>
                                <tr>
                                    <td>账户名称：</td>
                                    <td><input type="text" name="accountName" value="${awd.accountName}"></td>
                                </tr>
                                <tr>
                                    <td>银行账号：</td>
                                    <td><input type="text" name="accountNumber" value="${awd.accountNumber}"></td>
                                </tr>
                                <tr>
                                    <td>联行号：</td>
                                    <td><input type="text" name="coupletNumber" value="${awd.coupletNumber}"></td>
                                </tr>
                                <tr>
                                    <td>联系电话：</td>
                                    <td><input type="text" name="telephone" value="${awd.telephone}"></td>
                                </tr>
                            <#else>
                                <tr>
                                    <td>账户姓名：</td>
                                    <td><input type="text" name="username" value=""></td>
                                </tr>
                                <tr>
                                    <td>身份证号：</td>
                                    <td><input type="text" name="idnum" value=""></td>
                                </tr>
                                <tr>
                                    <td>账户名称：</td>
                                    <td><input type="text" name="accountName" value=""></td>
                                </tr>
                                <tr>
                                    <td>银行账号：</td>
                                    <td><input type="text" name="accountNumber" value=""></td>
                                </tr>
                                <tr>
                                    <td>联行号：</td>
                                    <td><input type="text" name="coupletNumber" value=""></td>
                                </tr>
                                <tr>
                                    <td>联系电话：</td>
                                    <td><input type="text" name="telephone" value=""></td>
                                </tr>
                            </#if>

                                <tr>
                                    <td></td>
                                    <td>
                                        <div class="button button-primary" style="cursor: pointer" id="register">提交
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<div id="footer">
    <p>版权所有 @ 2017全国高校云计算应用创新大赛组委会
    <p>
</div>
<script type="text/javascript" src="assets/js/cc.header.js"></script>
<script type="text/javascript">
    $("#register").click(function () {
        var username = $("input[name='username']").val();
        var idnum = $("input[name='idnum']").val();
        var accountName = $("input[name='accountName']").val();
        var accountNumber = $("input[name='accountNumber']").val();
        var coupletNumber = $("input[name='coupletNumber']").val();
        var telephone = $("input[name='telephone']").val();

        if (username == "" || idnum == "" || accountName == "" || accountNumber == "" || coupletNumber == "" || telephone == "")
            alert("请完整填写信息");
        else {
            $.post("awardRegister", {
                username: username, idnum: idnum,
                accountName: accountName, accountNumber: accountNumber, coupletNumber: coupletNumber,
                telephone: telephone
            }, function (data) {
                if (data == 1) {
                    alert("奖金账号信息登记成功");
                }
                else {
                    alert("奖金账号信息登记失败，请重新尝试，或联系管理员");
                }
            })
        }

    })
</script>
</body>
</html>