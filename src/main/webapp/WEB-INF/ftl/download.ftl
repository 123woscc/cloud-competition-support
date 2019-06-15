<!doctype html>
<html>
<head>
</head>
<body>
<div>
    <div style="float: right"><a href="logout">退出登录</a></div>
    <div><a href="upload">上传评审表格（EXCEL）</a> </div>
    <table>
    <tr>
        <td>收件人姓名</td>
        <td>收件人联系电话</td>
        <td>省</td>
        <td>市</td>
        <td>详细地址</td>
    </tr>
    <#if mails??>
        <#list mails as mail>
            <tr>
                <td>${mail.name}</td>
                <td>${mail.telephone}</td>
                <td>${mail.province}</td>
                <td>${mail.city}</td>
                <td>${mail.address}</td>
            </tr>
        </#list>
    </#if>
        <tr>
            <td><a href="downloadMail/mail.xls">下载邮寄信息表格</a> </td>
        </tr>
    </table>
    <br/><br/>
    <table>
        <tr>
            <td>ID</td>
            <td>姓名</td>
            <td>证件号码</td>
            <td>账户名称</td>
            <td>银行账号</td>
            <td>联行号</td>
            <td>联系方式</td>
        </tr>
            <#if awdList??>
                <#list awdList as award>
                <tr>
                    <td>${award.id}</td>
                    <td>${award.username}</td>
                    <td>${award.getIdnum()}</td>
                    <td>${award.accountName}</td>
                    <td>${award.accountNumber}</td>
                    <td>${award.coupletNumber}</td>
                    <td>${award.telephone}</td>
                </tr>
                </#list>
            </#if>

        <tr>
            <td><a href="downloadAward/award.xls">下载奖金信息表格</a> </td>
        </tr>
    </table>
</div>
</body>
</html>