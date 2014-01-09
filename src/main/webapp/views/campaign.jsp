<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
		<form:form id="createCampaign" action="createCampaign" method="post" commandName="campaign">
			<tr>
				<td style="padding: 0px 0px 0px 20px;"><img src="../resources/css/images/yourImageDefault.jpg"
					height="100" width="100" border="1"></td>
				<td style="width: 100px"></td>
				<td>
					<h3>Nome da Campanha:</h3>
					<input style="width: 200px;" type="text" id="name" name="name">
					<h3 style="padding: 20px 0px 0px 0px;">Players:</h3>
				</td>
				<td style="padding: 0px 0px 20px 0px;">
					<input type="submit" value="Criar nova Campanha"/>
				</td>
				<td>
					<c:if test="${campaignError.errorType == 'CAMPAIGN_ALREADY_EXISTS'}" >
						<td style="color: red">${campaignError.message}</td>
					</c:if>
					<form:errors path="name" cssStyle="color:red" id="name" />
				</td>
			</tr>
			<tr>
				<td>
					<a href="/main">
						<h3  align="center">${logged.username}</h3>
					</a>
					<ul>
						<li><a href="#">Gurps ficha</a></li>
						<li><a href="#">D&D ficha</a></li>
						<li><a href="#">Vampire ficha</a></li>
						<li><a href="#">Upload ficha</a></li>
						<br>
						<li><a href="/campaign">Criar uma Campanha</a></li>
					</ul>
				</td>
				<td>
					
				</td>
				<td>
					<table>
						<c:forEach var="friend" items="${friends}">
							<tr>
								<td>
									<input type="checkbox" name="${ friend.username }" />
								</td>
								<td>
									<img src="../resources/css/images/yourImageDefault.jpg" height="50" width="50" border="1">
								</td>
								<td>
									<h3><c:out value="${ friend.username }" /></h3>
								</td>
							</tr>
						</c:forEach>
					</table>
				</td>
				<td>
				</td>
			</tr>
		</form:form>
	</table>
</body>
</html>