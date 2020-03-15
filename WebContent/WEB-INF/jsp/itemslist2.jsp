<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>显示商品信息</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/jquery-3.0.0.min.js"></script>
<script type="text/javascript">
	//添加
	function add(){
		
		location.href="${pageContext.request.contextPath}/items2/toAddItems";
	}
	
	
	//批量删除
	function deleteBatch(){
	//document.form1.action="${pageContext.request.contextPath}/items/deleteBatch"
	document.form1.submit();
		
	}

	//分页
	function page(pageNum) {
		
		var pageSize = document.getElementById("pageSize").value;
		//一页显示的行数	
		location.href="findItemsByCondition?pageNum="+pageNum+"&pageSize="+pageSize;
	
	}
	

</script>
</head>
<body>
	<h1>商品展示页面</h1>
	<form action="findItemsByCondition" method="post">
		<table>
			<tr>
				<td>商品名称<input name="itemsname" type="text"value="${itemsVO.itemsname }">
					 最低价格<input name="pricelow"type="text" value="${itemsVO.pricelow }">
					  最高价格<inputname="pricehight" type="text" value="${itemsVO.pricehight }">
					 <input type="submit" value="查询">
					 <input type="button"onclick="add()" value="增加">
					 <input type="button"onclick="deleteBatch()" value="批量删除">
				</td>
			</tr>
		</table>

	</form>

	<table border="1px">
		<tr>
			<td>选择</td>
			<td>商品编号</td>
			<td>商品名称</td>
			<td>价格</td>
			<td>生产日期</td>
			<td>商品图片</td>
			<td>商品描述</td>
			<td>操作</td>
		</tr>
		<form action="deleteBatch" name="form1">
			<c:forEach items="${items}" var="item" varStatus="index">
				<tr>
				<td><input type="checkbox" name="itemsids" <%-- value="${item.id }" --%>/></td>
					<td>${index.index+pageInfo.getStartRow()}</td>
					<td>${item.itemsname}</td>
					<td>${item.price}</td>
					<td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><img alt="图片已损坏" width="300px" height="100px" src="/pic/${item.pic}"></td>
					<td>${item.detail}</td>
					<td><a href="toUpdateItems?id=${item.id }">修改</a>--<a href="deleteItemsById?id=${item.id}">删除</a></td>
				</tr>
			</c:forEach>
		</form>
		<tr>
			<td colspan="7">
			<select name="pageSize" id="pageSize" onchange="page('1')">
				<option value="5">5</option>
				<option value="10">10</option>
				<option value="20">20</option>
				
			</select>
				<input type="button" value="首页" onclick="page('1')">
				<input type="button" value="上一页" onclick="page('${pageInfo.getPrePage()}')">
				<input type="button" value="下一页" onclick="page('${pageInfo.getNextPage()}')">
				<input type="button" value="末页" onclick="page('${pageInfo.getPages()}')">
				共${pageInfo.getPages()}页	
			</td>
	
		</tr>
	
	</table>


<c:forEach items="${hb}" var="hobby" varStatus="vs">  
        <c:choose>  
            <c:when test="${hobby == 'basketball'}">  
            篮球<input type="checkbox" name="hobbies"   value="basketball">  
            </c:when>  
            <c:when test="${hobby == 'football'}">  
                足球<input type="checkbox" name="hobbies" value="football">  
            </c:when>  
            <c:when test="${hobby == 'tennis'}">  
                网球<input type="checkbox" name="hobbies" value="tennis">  
            </c:when>  
        </c:choose>  
    </c:forEach> 


</body>
</html>