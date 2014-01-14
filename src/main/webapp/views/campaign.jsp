<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Taverna Do Drag�o</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link href="/resources/css/taverna.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div class="page">
		<div class="header">
			<div class="title">
				<h1>Eu era um aventureiro como voc�... At� levar uma flechada no joelho...</h1>
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
						<li><a href="#">Upload ficha</a></li>
						<br>
						<li><a href="/campaign">Criar uma Campanha</a></li>
					</ul>
				</div>
			</div>
			<div class="middle">
				<form:form id="createCampaign" action="createCampaign" method="post" commandName="campaign">
					<div class="campaign">
						<div class="campaignHeader">
							<h3>Nome da Campanha:</h3>
							<div class="campaignField">
								<input style="width: 200px;" type="text" id="name" name="name">
								<input type="submit" value="Criar nova Campanha"/>
								<c:if test="${campaignError.errorType == 'CAMPAIGN_ALREADY_EXISTS'}" >
									<div class="error">
										${campaignError.message}
									</div>
								</c:if>
								<div>
									<form:errors path="name" cssStyle="color:red" id="name" />
								</div>
							</div>
						</div>
						<div class="campaignBody">
							<h3>Players:</h3>
							<div class="campaignFriends">
								<c:forEach var="friend" items="${friends}">
									<div class="campaignFriend">
										<input type="checkbox" name="${ friend.username }" />
										<img src="../resources/css/images/yourImageDefault.jpg" height="50" width="50" border="1">
										<h3><c:out value="${ friend.username }" /></h3>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>