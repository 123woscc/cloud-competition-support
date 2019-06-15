<!doctype html>
<html>
<head>
</head>
<body>
<div>
    <table>
        <tr>
            <td>选择文件</td>
            <td><input name="uploadFile" id="file" type="file"></td>
        </tr>
        <tr>
            <td><input type="button" id="upload">上传</td>
        </tr>
    </table>
</div>
</body>
<script type="text/javascript" src="assets/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="assets/js/ajaxfileupload.js"></script>
<script type="text/javascript">
    $("#upload").click(function() {
        $.ajaxFileUpload({
            url: "save",
            secureuri: true,
            fileElementId: "file",
            success: function (data, status) {
                if (data == 0)
                    alert("上传失败，请重新尝试或联系管理员");
                else
                    alert("上传成功");
            }
        })
    });
</script>
</html>