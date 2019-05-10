<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function ok(){
//这里的HTMLFormElement是强制转换的写法，因为是typescript所以不强制转换的话编译不能通过
var myForm = <HTMLFormElement>$('#你的要提交的form的ID')[0];
var formData = new FormData(myForm);//和后台联通让后台接受form的系统类
//获取文件名
var filePath = $('#txt_upload').val();
var fileName = filePath.substring(filePath.lastIndexOf('\\') + 1);
formData.append('fileName', fileName);
$('#waring').text("操作中，请等待");
$.ajax({
    url: '/Home/后台的导入方法',
    type: 'POST',
    cache: false,
    data: formData,
    processData: false,
    contentType: false
}).done(function (res) {
    //批量导入后刷新页面
    $('#waring').text(res.message);
}).fail(function (res) {
    $('#waring').text("导入数据失败");
});
}
</script>
</head>

<body>
<form id="form" class="form-horizontal" enctype="multipart/form-data">
    <div class="form-group">
        <label for="txt_signInTitleOfDetail">选择文件</label>
        <input type="file" name="file" class="form-control" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" />
    </div>
    <input type="button" class="btn btn-primary" value="保存" id="btn_Save">
</form>
</body>
</html>