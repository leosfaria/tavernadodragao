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
				<div class="menuCampaign">
					<ul>
						<li><a href="#">Upload Char</a></li>
					</ul>
				</div>
				<div class="dices">
					<h3>Dices</h3>
					<div class="number">
						<input type="text" name="d20" />
						<input type="text" name="d12" />
						<input type="text" name="d10" />
						<input type="text" name="d8" />
						<input type="text" name="d6" />
						<input type="text" name="d4" />
					</div>
					<div class="roll">
						<img src="../resources/css/images/d20.jpg" height="20" width="24" border="1">
						<img src="../resources/css/images/d12.jpg" height="20" width="24" border="1">
						<img src="../resources/css/images/d10.jpg" height="20" width="24" border="1">
						<img src="../resources/css/images/d8.jpg" height="20" width="24" border="1">
						<img src="../resources/css/images/d6.jpg" height="20" width="24" border="1">
						<img src="../resources/css/images/d4.jpg" height="20" width="24" border="1">
					</div>
				</div>
			</div>
			<div class="middle">
				<div class="charactersheet">
					<img src="../resources/css/images/charactersheet.jpg" height="500" width="500" border="1">
				</div>
				<div class="chat">
					<div class="chatView">
					</div>
					<form id="campaignChat" action="campaignChat" method="post">
						<input type="text" name="message" />
					</form>
				</div>
			</div>
			<div class="right">
				<div class="friends">
					<h2>Party</h2>
					<div class="friendsList">
						<c:forEach var="friend" items="${party}">
							<img src="../resources/css/images/yourImageDefault.jpg" height="50" width="50" border="1">
							<h3><c:out value="${ friend.username }" /></h3>
						</c:forEach>
					</div>
				</div>
				<div class="master">
					<h2>Master</h2>
					<img src="../resources/css/images/yourImageDefault.jpg" height="50" width="50" border="1">
					<h3><c:out value="${ master.username }" /></h3>
				</div>
			</div>
		</div>
	</div>
</body>
</html>