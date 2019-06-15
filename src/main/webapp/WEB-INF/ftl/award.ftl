<!doctype html>
<html>
<head>
    <title>2017全国高校云计算应用创新大赛 - 登录</title>
    <meta charset="utf-8">
    <meta name="keywords" content="云计算,云计算大赛,全国高校云计算大赛,教育部,技能赛,创意赛,命题赛,创业赛,东南大学,ACM南京分会">
    <meta name="description" content="2017年-2018年第四届全国高校云计算应用创新大赛">
    <meta name="viewport" content="width=1200px, initial-scale=device-width/1200">
    <link rel="icon" href="favicon.ico" type="image/x-icon" />
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
                    <#if result??>
                        <li class="odd"><a href="addressRegister">奖状邮寄</a></li>
                    </#if>
                    <#if Session.money??>
                        <li class="even"><a href="awardRegister">奖金颁发</a></li>
                        <li class="odd"><a href="logout">注销</a></li>
                    <#else>
                        <li class="even"><a href="logout">注销</a></li>
                    </#if>
                </ul>
            </div>
            <div id="center-context-container">
                <h1 class="nano-left-gap">获奖信息</h1>
                <hr class="nano-left-gap nano-right-gap">
                <div class="mess-container nano-left-gap nano-right-gap nano-top-gap">
                    <div class="table-container nano-left-gap nano-right-gap nano-top-gap">
                        <#if result??>
                            <#list results as r>
                                <p>恭喜${r.getWorkname()}作品获得${r.getAward()}</p>
                            </#list>
                            <p>奖状以电子版形式发往参赛选手邮箱中，请输入电子邮箱地址，同时请各位参赛选手确认选手信息
                                <#if Session.money??>
                                    及银行相关信息</p>
                                </#if>
                            <table>
                                <tr>
                                    <td>学校</td>
                                    <td>${result.getSchool()}</td>
                                </tr>
                                <tr>
                                    <td>指导教师:</td>
                                    <td>${result.getTeacher()}</td>
                                </tr>
                                <tr>
                                    <td>队长:</td>
                                    <td>${result.leader}</td>
                                </tr>
                                <tr>
                                    <td>队员:</td>
                                    <#if result.labman1??>
                                        <td>${result.labman1}</td>
                                    </#if>
                                </tr>
                                <tr>
                                    <td>队员:</td>
                                    <#if result.labman2??>
                                        <td>${result.labman2}</td>
                                    </#if>
                                </tr>
                                <tr>
                                    <td>队员:</td>
                                    <#if result.labman3??>
                                        <td>${result.labman3}</td>
                                    </#if>
                                </tr>
                                <tr>
                                    <td>参赛组别:</td>
                                    <td>${result.category}</td>
                                </tr>
                            </table>
                        <#else>
                            <p>您的成绩不存在或未录入，如有问题，请联系管理员</p>
                        </#if>
                    </div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<div id="footer">
    <p>版权所有 @ 2017全国高校云计算应用创新大赛组委会<p>
</div>
</body>
</html>