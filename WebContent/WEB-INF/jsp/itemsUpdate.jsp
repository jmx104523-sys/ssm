<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改商品信息</title>


</head>
<body>
	<form action="updateItems" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>商品名称：<input type="text" name="itemsname" value="${items.itemsname }">
				  <input type="hidden" name="id" value="${items.id }">
				</td>
			</tr>
			<tr>
				<td>商品价格：<input type="text" name="price" value="${items.price }"></td>
	
			</tr>
          <tr>
				<td>生产日期：<input type="text" name="createtime"
				 value='<fmt:formatDate value="${items.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>'></td>
			</tr>
		<tr>
				<td>商品图片：<input type="file" name="items_pic"></td>
				<td><input type="file" name="items_pic"></td>
				<td><input type="file" name="items_pic"></td>
			</tr>
		
			<tr>
				<td>商品描述：<input type="text" name="detail" value="${items.detail }"></td>
			</tr>
        <tr><td><input type="submit" value="保存"></td></tr>
		</table>

	</form>


</body>
</html>