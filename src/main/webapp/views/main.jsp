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
		<tr>
			<td style="padding: 0px 0px 0px 20px;"><img src="../resources/css/images/yourImageDefault.jpg"
				height="100" width="100" border="1"></td>
			<td style="width: 100px"></td>
			<form id="search" action="search" method="post">
				<td style="width: 400px">
					<input style="width: 400px;" type="text" id="searchInput" name="searchInput">
					<p style="padding: 50px 0px 0px 0px;">
				</td>
				<td>
					<a href="#" onclick="document.forms['search'].submit(); return false;"><img
						src="../resources/css/images/lupinha.jpg"></a>
					<p style="padding: 50px 0px 0px 0px;">
				</td>
			</form>
			<td style="width: 500px">
				<h2 style="padding: 0px 0px 0px 200px">
					<u>Party</u>  
					<c:out value="${ friendsRequestsCount }" />
				</h2>
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
			<td></td>
			<td valign="top">Recent Activity</td>
			<td></td>
			<td align="center" valign="top">
			<c:forEach var="friend" items="${friends}">
				<img src="../resources/css/images/yourImageDefault.jpg" height="50" width="50" border="1">
				<h3><c:out value="${ friend.username }" /></h3>
			</c:forEach>
			<form id="acceptFriend" action="acceptFriend" method="post">
				<c:forEach var="friendRequest" items="${friendsRequests}">
					<img src="../resources/css/images/yourImageDefault.jpg" height="50" width="50" border="1">
					<h3><c:out value="${ friendRequest.username }" /></h3>
					<input type="hidden" name="accept_friend_id" value="${friendRequest.id}" />
					<input type="submit" value="Add" />	
				</c:forEach>
			</form>
			</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>
				<h2 style="padding: 0px 0px 0px 200px">
					<u>Campaigns</u>
				</h2>
			</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td align="center" valign="top">
			<c:forEach var="campaign" items="${campaigns}">
				<img src="../resources/css/images/rpgTable.jpg" height="50" width="50" border="1">
				<h3><c:out value="${ campaign.name }" /></h3>
			</c:forEach>
			</td>
		</tr>
		<c:forEach var="user" items="${userList}">
			<tr>
				<td></td>
				<td></td>
				<td>
					<img src="../resources/css/images/yourImageDefault.jpg"
				height="50" width="50" border="1">
					<h3>${user.username}</h3>
				</td>
				<td>
					<c:set var="contains" value="false" />
					<c:forEach var="friend" items="${friends}">
						<c:if test="${friend.id eq user.id }">
							<c:set var="contains" value="true" />
						</c:if>
					</c:forEach>
					<c:choose>
						<c:when test="${ contains }">
							<form action="unFriend" method="post">
								<input type="submit" value="Unfriend"/>
								<input type="hidden" name="friend_id" value="${user.id}" />
							</form>
						</c:when>
						<c:otherwise>
							<form action="addFriend" method="post">
								<input type="submit" value="Add Friend"/>
								<input type="hidden" name="friend_id" value="${user.id}" />
							</form>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>