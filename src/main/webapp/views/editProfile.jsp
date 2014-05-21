<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>Taverna Do Dragão</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<link href="/resources/css/taverna.css" type="text/css" rel="stylesheet" />
	<script src="http://code.jquery.com/jquery-1.9.0.js"></script>
</head>
<body>
	<div class="page">
		<div class="header">
			<div class="title">
				<h1>I used to be an adventurer like you, then I took an arrow to the knee...</h1>
				<a id="logout" href="logout">Logout</a>
			</div>
		</div>
		<div class="content">
			<div class="left">
				<div class="profile">
					<a href="/main">
						<img src="${currentUser.avatarImgPath}" height="100" width="100" border="1">
					</a>
					<h3>
						<a href="/main">
							${currentUser.username}
						</a>
					</h3>
				</div>
			</div>
			<div class="middle">
				<form id="updateProfile" action="updateProfile" method="post" enctype="multipart/form-data">
					<div class="editProfile">
						<div class="editProfileLabel">
							<h3>Upload New Photo:</h3>
							<h3>Username:</h3>
							<h3>Email:</h3>
							<h3>Old Password:</h3>
							<h3>New Password:</h3>
							<h3>Confirm New Password:</h3>
						</div>
						<div class="editProfileText">
							<input type="file" name="file" id="file">
							<input type="text" id="username" name="username" value="${currentUser.username}" >
							<input type="text" id="email" name="email" value="${currentUser.email}">
							<input type="password" id="oldPassword" name="oldPassword" value="" >
							<input type="password" id="newPassword" name="newPassword" value="" >
							<input type="password" id="confirmPassword" name="confirmPassword" value="" >
							<div>
								<input type="submit" value="Save" >
							</div>
						</div>
						<div class="editError">
							<div>
								<c:if test="${profileError.errorType == 'INVALID_USERNAME'}">
									<label class="error"><c:out value="${profileError.message}" /></label>
								</c:if>
							</div>
							<div>
								<c:if test="${profileError.errorType == 'INVALID_EMAIL'}">
									<label class="error"><c:out value="${profileError.message}" /></label>
								</c:if>
								<c:if test="${profileError.errorType == 'EMAIL_ALREADY_IN_USE'}">
									<label class="error"><c:out value="${profileError.message}" /></label>
								</c:if>
							</div>
							<div>
								<c:if test="${profileError.errorType == 'CURRENT_PASS_DOESNT_MATCH'}">
									<label class="error"><c:out value="${profileError.message}" /></label>
								</c:if>
								<c:if test="${profileError.errorType == 'CURRENT_PASS_DOESNT_PASSED'}">
									<label class="error"><c:out value="${profileError.message}" /></label>
								</c:if>
							</div>
							<div>
								<c:if test="${profileError.errorType == 'INVALID_PASSWORD'}">
									<label class="error"><c:out value="${profileError.message}" /></label>
								</c:if>
								<c:if test="${profileError.errorType == 'PASS_AND_CONFIRM_DOESNT_MATCH'}">
									<label class="error"><c:out value="${profileError.message}" /></label>
								</c:if>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>