<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
				<h1>Taverna Do Dragão</h1>
				<div class="login">
					<form:form id="login" action="login" method="post">
						<label>Email:</label>
						<input type="text" id="emailLogin" name="email">
						<label>Password:</label>
						<input type="password" id="passwordLogin" name="password">
						<input type="submit" id="buttonLogin" value="Log In">
						<c:if test="${loginError.errorType == 'USER_OR_PASS_INVALID'}" >
							<label class="error">
								<c:out value="${loginError.message}" />
							</label>
						</c:if>
					</form:form>
				</div>
			</div>
		</div>
		<div class="content">
			<form:form id="signup" action="signup" method="post" commandName="user">	
				<div class="signup">
					<div class="left">
						<div>
							<label>Username:</label>
						</div>
						<div>
							<label>Email:</label>
						</div>
						<div>
							<label>Password:</label>
						</div>
						<div>
							<label>Confirm Password:</label>
						</div>
					</div>
					<div class="right">
						<div>
							<input type="text" id="usernameSignup" name="username">
						</div>
						<div>
							<input type="text" id="emailSignup" name="email">
						</div>
						<div>
							<input type="password" id="passwordSignup" name="password">
						</div>
						<div>
							<input type="password" id="confirmPasswordSignup" name="confirmPassword">
						</div>
						<div>
							<input type="submit" id="buttonSignup" value="Sign Up">
						</div>
					</div>
				</div>
				<div class="signupError">
					<div>
						<form:errors path="username" cssStyle="color:red" id="usernameSignup" />
					</div>
					<div>
						<form:errors path="email" cssStyle="color:red" id="emailSignup" />
						<c:if test="${loginError.errorType == 'EMAIL_ALREADY_IN_USE'}">
							<label class="error">
								<c:out value="${loginError.message}" />
							</label>
						</c:if>
					</div>
					<div>
						<form:errors path="password" cssStyle="color:red" id="passwordSignup" />
						<c:if test="${loginError.errorType == 'PASS_AND_CONFIRM_DOESNT_MATCH'}">
							<label class="error">
								<c:out value="${loginError.message}" />
							</label>
						</c:if>
					</div>
					<div>
						<form:errors path="confirmPassword" cssStyle="color:red" id="confirmPasswordSignup" />
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>