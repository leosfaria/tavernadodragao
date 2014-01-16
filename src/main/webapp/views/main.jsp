<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Taverna Do Dragão</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link href="/resources/css/taverna.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div class="page">
		<div class="header">
			<div class="title">
				<h1>I used to be an adventurer like you but then i took an arrow to the knee...</h1>
				<a href="logout">Logout</a>
			</div>
		</div>
		<div class="content">
			<div class="left">
				<div class="profile">
					<img src="../resources/css/images/yourImageDefault.jpg" height="100" width="100" border="1">
					<h3>
						<a href="/main">
							${logged.username}
						</a>
					</h3>
				</div>
				<div class="menu">
					<ul>
						<li><a href="#">Upload Char</a></li>
						<br>
						<li><a href="/campaign">Create Campaign</a></li>
					</ul>
				</div>
			</div>
			<div class="middle">
				<div class="search">
					<form id="search" action="search" method="post">
						<input type="text" id="searchInput" name="searchInput">
						<a href="#" onclick="document.forms['search'].submit(); return false;">
						<img src="../resources/css/images/lupinha.jpg"></a>
					</form>
				</div>
				<div class="activity">
					<table>
						<c:forEach var="user" items="${userList}">
							<tr>
								<td class="img">
									<img src="../resources/css/images/yourImageDefault.jpg" height="50" width="50" border="1">
								</td>
								<td class="friendData">
									<h3>${user.username}</h3>
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
											<c:set var="requestPending" value="false" />
											<c:forEach var="friendRequest" items="${user.friendsRequests}">
												<c:if test="${friendRequest.id eq logged.id }">
													<c:set var="requestPending" value="true" />
												</c:if>
											</c:forEach>
											<c:choose>
												<c:when test="${ requestPending }">
													<input type="submit" value="Request Pending" disabled="disabled"/>
												</c:when>
												<c:otherwise>
													<form action="addFriend" method="post">
														<input type="submit" value="Add Friend"/>
														<input type="hidden" name="friend_id" value="${user.id}" />
													</form>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</table>
					<div class="clear"></div>
					<div class="recentActivity">
						<b>Recent Activity</b> 
						<br>
						<c:forEach var="activity" items="${activities}">
							<br>
							<p><fmt:formatDate pattern="dd/MM/yyyy - HH:mm" value="${activity.date}" /></p>
							<p>-   <c:out value="${ activity.message }" /></p>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="right">
				<div class="friends">
					<h2>Party</h2>
					<div class="friendsList">
						<c:out value="${ friendsRequestsCount }" />
						<c:forEach var="friend" items="${friends}">
							<img src="../resources/css/images/yourImageDefault.jpg" height="50" width="50" border="1">
							<h3><c:out value="${ friend.username }" /></h3>
						</c:forEach>
						<form id="acceptFriend" action="acceptFriend" method="post">
							<c:forEach var="friendRequest" items="${friendsRequests}">
								<img src="../resources/css/images/yourImageDefault.jpg" height="50" width="50" border="1">
								<h3><c:out value="${ friendRequest.username }" /></h3>
								<input type="hidden" name="accept_friend_id" value="${friendRequest.id}" />
								<input type="submit" value="Add" name="add" />
								<input type="submit" value="Decline" name="decline" />	
							</c:forEach>
						</form>
					</div>
				</div>
				<div class="campaign">
					<h2>Campaigns</h2>
					<div class="campaignList">
						<c:forEach var="campaign" items="${campaigns}">
							<img src="../resources/css/images/rpgTable.jpg" height="50" width="50" border="1">
							<h3>
								<a href="/enterCampaign?campaignName=${ campaign.name }"  >
									${ campaign.name }
								</a>
							</h3>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>