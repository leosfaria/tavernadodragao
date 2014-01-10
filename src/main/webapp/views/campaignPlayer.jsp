<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="/resources/css/default.css">
</head>
<body>
	<h1>Eu era um aventureiro como você... Até levar uma flechada no
		joelho...</h1>
	<a style="margin-left: 850px" href="logout">Logout</a>
	<br>
	<br>
	<br>
	<table>
		<tr>
			<td style="padding: 0px 0px 0px 20px;"><img src="../resources/css/images/yourImageDefault.jpg"
				height="100" width="100" border="1"></td>
			<td style="width: 100px"></td>
			<td style="width: 400px">
				<img src="../resources/css/images/charactersheet.jpg" height="500" width="500" border="1">
			</td>
			<td>
			</td>
			<td style="width: 500px">
				<h2 style="padding: 0px 0px 0px 200px">
					<u>Party</u>  
				</h2>
			</td>
		</tr>
		<tr>
			<td>
				<a href="/main">
					<h3  align="center">${logged.username}</h3>
				</a>
				<a href="#">Upload ficha</a>
			</td>
			<td></td>
			<td> 
			</td>
			<td></td>
			<td align="center" valign="top">
			<c:forEach var="companion" items="${party}">
				<img src="../resources/css/images/yourImageDefault.jpg" height="50" width="50" border="1">
				<h3><c:out value="${ companion.username }" /></h3>
			</c:forEach>
			</td>
		</tr>
		<tr>
			<td>
				<br>
				<h2>
					<u>Dices</u>
				</h2>
			</td>
			<td></td>
			<td></td>
			<td></td>
			<td>
				<h2 style="padding: 0px 0px 0px 200px">
					<u>Master</u>
				</h2>
			</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td>
				<form id="campaignChat" action="campaignChat" method="post">
					<input type="text" name="chat">
					<input hidden="hidden" name="campaignName" value="${ campaign.name }">
				</form>
			</td>
			<td></td>
			<td align="center" valign="top">
				<img src="../resources/css/images/yourImageDefault.jpg" height="50" width="50" border="1">
				<h3><c:out value="${ master.username }" /></h3>				
			</td>
		</tr>
	</table>
</body>
</html>