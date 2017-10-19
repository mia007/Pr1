<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<c:set var="title" value="${loginLoc}" scope="page" />
			<form class="form-inline" role="form" action="controller" method="post"  id="loginForm"
				class="input-not-empty">
			<input type="hidden" value="login" name="command" />
				<legend>${loginLoc}</legend>
					<c:if test="${not empty loginError}">
						<div class="alert alert-dismissible alert-warning" id="errorDiv">
							<fmt:message key="${loginError}" />
						</div>
					</c:if>
			<div class="form-group">
						 <input class="form-control" id="email" name="email" value="${email}" placeholder=<fmt:message key="label.email" />>
						<p class="text-warning" id="emailError"></p>
					</div>
					<div  class="form-group">
						<input type="password" class="form-control" id="password"
							name="password" placeholder=<fmt:message key="label.password" />>
						<p class="text-warning" id="passwordError"></p>
					</div>
					<div class="submit">
					<div class="fine-button default">
					<button type="submit">
						<fmt:message key="label.login" />
					</button>
					</div>
				</div>
			</form>
	</div>
</body>
</html>