<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>显示商品信息</title>


</head>
<body>
	<h1>添加商品页面</h1>
	
	<form:form action="addItems" method="post" modelAttribute="items">
	商品名称<form:input path="itemsname" />
	<form:errors path="itemsname" cssStyle="color:red"></form:errors><br/>
	价格        <form:input path="price"/>
	<form:errors path="price" cssStyle="color:red"></form:errors><br/>
	生产日期<form:input path="createtime"/>
	<form:errors path="createtime"></form:errors><br/>
	商品描述<form:input path="detail"/>
	<form:errors path="detail"></form:errors><br/>
	<form:button name="submit">保存</form:button>
	</form:form>
	

	<%-- <form action="addItems" method="post">
		<table>
			<tr>
				<td>商品名称：<input type="text" name="itemsname"></td>
			</tr>
			<tr>
				<td>商品价格：<input type="text" name="price"></td>
			</tr>
			<tr>
				<td>生产日期：<input type="text" name="createtime"></td>
			</tr>
			<tr>
				<td>商品描述：<input type="text" name="detail"></td>
			</tr>
			<tr>
				<td><input type="submit" value="保存"></td>
			</tr>

		</table>

	</form> --%>

</body>
</html>