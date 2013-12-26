<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link type="text/css" rel="stylesheet" href="/resources/css/default.css">
</head>
<body>
	<h1>Taverna do Dragão</h1>

	<table>
		<form:form id="login" action="login" method="post">
			<tr>
				<td><label>Email:</label></td>
				<td><input type="text" id="emailLogin" name="email"></td>
				<td><label>Password:</label></td>
				<td><input type="password" id="passwordLogin" name="password"></td>
				<td><input type="submit" id="buttonLogin" value="Log In"></td>
				<c:if test="${loginError.errorType == 'USER_OR_PASS_INVALID'}" >
					<td style="color: red">${loginError.message}</td>
				</c:if>
			</tr>
		</form:form>
	</table>
	<br>
	<table align="center">
		<form:form id="signup" action="signup" method="post"
			commandName="user">
			<tr>
				<td><label>Username:</label></td>
				<td><input type="text" id="usernameSignup" name="username"></td>
				<td><form:errors path="username" cssStyle="color:red"
						id="usernameSignup" /></td>
			</tr>
			<tr>
				<td><label>Email:</label></td>
				<td><input type="text" id="emailSignup" name="email"></td>
				<td><form:errors path="email" cssStyle="color:red"
						id="emailSignup" />
					<c:if test="${loginError.errorType == 'EMAIL_ALREADY_IN_USE'}">
						<td style="color: red">${loginError.message}</td>
					</c:if>
				</td>
			</tr>
			<tr>
				<td><label>Password:</label></td>
				<td><input type="password" id="passwordSignup" name="password"></td>
				<td><form:errors path="password" cssStyle="color:red"
						id="passwordSignup" /></td>
				<c:if test="${loginError.errorType == 'PASS_AND_CONFIRM_DOESNT_MATCH'}">
					<td style="color: red">${loginError.message}</td>
				</c:if>
			</tr>
			<tr>
				<td><label>Confirm Password:</label></td>
				<td><input type="password" id="confirmPasswordSignup" name="confirmPassword"></td>
				<td><form:errors path="confirmPassword" cssStyle="color:red"
						id="confirmPasswordSignup" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" id="buttonSignup" value="Sign Up"></td>
				<td></td>
			</tr>
		</form:form>
	</table>
</body>
</html>