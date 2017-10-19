<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<fmt:message key="label.sign_up" var="signUpLoc" />
<c:set var="title" value="${signUpLoc}" scope="page" />
<div class="container">
	<form class="form-inline" role="form" action="controller" method="post" id="signUpForm">
	<input type="hidden" value="signUp" name="command" />
			 <input class="form-control" id="email"
				name="email" value="${sessionScope.email}"  placeholder="<fmt:message
					key="label.email" />">
			<p class="text-warning" id="emailError"></p>
		<div class="form-group">
			 <input type="password"
				class="form-control" id="password" name="password" placeholder=<fmt:message
					key="label.password" />>
			<p class="text-warning" id="passwordError"></p>
		</div>
		<div class="form-group">
			<input type="password" class="form-control" id="repPassword"
				name="repPassword" placeholder="<fmt:message
					key="label.repeat_password" />">
			<p class="text-warning" id="repPasswordError"></p>
		</div>
		<br>
		<div class="form-group">
			 <input class="form-control"
				id="firstName" name="firstName" value="${sessionScope.firstName}" placeholder="<fmt:message
					key="label.first_name" />">
			<p class="text-warning" id="firstNameError"></p>
		</div>
		<div class="form-group">
			<input class="form-control"
				id="lastName" name="lastName" value="${sessionScope.lastName}" placeholder="<fmt:message
					key="label.last_name" />" />
			<p class="text-warning" id="lastNameError"></p>
		</div>

		<div class="button">
			<div class="fine-button default">
				<button type="submit">
					<fmt:message
						key="label.sign_up" />
				</button>
			</div>
		</div>
	</form>
</div>
</body>
</html>