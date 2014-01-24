<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
	<title>Taverna Do Dragão</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<link href="/resources/css/taverna.css" type="text/css" rel="stylesheet" />
	<script src="http://code.jquery.com/jquery-1.9.0.js"></script>
	<script src="../resources/js/campaignMaster.js"></script>
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
							${userLogged.username}
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
						<input type="text" id="d20" name="d20" value="1" />
						<input type="text" id="d12" name="d12" value="1" />
						<input type="text" id="d10" name="d10" value="1" />
						<input type="text" id="d8" name="d8" value="1" />
						<input type="text" id="d6" name="d6" value="3" />
						<input type="text" id="d4" name="d4" value="1" />
					</div>
					<div class="roll">
						<img id="d20Button" src="../resources/css/images/d20.jpg" height="20" width="24" border="1">
						<img id="d12Button" src="../resources/css/images/d12.jpg" height="20" width="24" border="1">
						<img id="d10Button" src="../resources/css/images/d10.jpg" height="20" width="24" border="1">
						<img id="d8Button" src="../resources/css/images/d8.jpg" height="20" width="24" border="1">
						<img id="d6Button" src="../resources/css/images/d6.jpg" height="20" width="24" border="1">
						<img id="d4Button" src="../resources/css/images/d4.jpg" height="20" width="24" border="1">
					</div>
				</div>
			</div>
			<div class="middle">
				<div class="charactersheet">
					<img src="../resources/css/images/charactersheet.jpg" height="500" width="500" border="1">
				</div>
				<div class="chat">
					<div id="chatView" class="chatView">
					</div>					
					<input type="text" id="message" name="message" />
					<input type="hidden" id="campaignName" name="campaignName" value="${campaign.name}">
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
				<div class="masterNotes">
					<h2>Notes</h2>
					<textarea rows="10" cols="10"></textarea>
				</div>
			</div>
		</div>
	</div>
</body>
</html>