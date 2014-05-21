<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<title>Taverna Do Dragão</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<link href="/resources/css/taverna.css" type="text/css" rel="stylesheet" />
	<script src="http://code.jquery.com/jquery-1.9.0.js"></script>
	<script src="../resources/js/character.js"></script>
</head>
<body>
	<div class="page">
		<div class="header">
			<div class="title">
				<h1>I used to be an adventurer like you but then i took an arrow to the knee...</h1>
				<a id="logout" href="logout">Logout</a>
			</div>
		</div>
		<div class="content">
			<div class="left">
				<div class="profile">
					<a href="/main">
						<img src="${userLogged.avatarImgPath}" height="100" width="100" border="1">
					</a>
					<h3>
						<a href="/main">
							${userLogged.username}
						</a>
					</h3>
				</div>
			</div>
			<div class="middle">
				<div class="character">
					<form id="uploadFile" action="uploadCharacterSheet" method="post" enctype="multipart/form-data">
						<div class="characterHeader">
							<h3>Upload Character:</h3>
								<input type="file" name="file" id="file">
							<div>
								${charactersheet.name}
							</div>
						</div>
						<div class="characterBody">
							<h3>Campaigns:</h3>
							<div class="characterCampaigns">
								<c:forEach var="campaign" items="${existingCampaigns}">
									<div class="characterCampaign">
										<input type="checkbox" name="${ campaign.name }" />
										<img src="../resources/css/images/rpgTable.jpg" height="50" width="50" border="1">
										<h3><c:out value="${ campaign.name }" /></h3>
									</div>
								</c:forEach>
							</div>
							
						</div>
						<div>
							<input type="submit" value="Send">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>