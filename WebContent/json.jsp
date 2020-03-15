<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/jquery-3.0.0.min.js"></script>
<script type="text/javascript">
//请求的json  返回json
//请求是json返回json
function find(){
	 //查询的参数
	var param={itemsname:'笔记本',detail:'电脑'}
	$.ajax({
		url:"${pageContext.request.contextPath}/items2/findItems",
		type:"post",
		contentType:"application/json",//请求的为json
		data:JSON.stringify(param),// 把字符串转为json对象
		success:function(result){
			//显示信息
			for(var i=0;i<result.length;i++){
				alert(result[i].itemsname)
			}
		},
		dataType:"json",//要求返回的是json
		
		
	})
	
}
</script>
</head>
<body>

	<input type="button" onclick="find()" value="查询">
      
</body>
</html>