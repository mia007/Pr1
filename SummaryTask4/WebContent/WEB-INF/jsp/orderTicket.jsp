<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<body>
	<c:set var="title" value="${signUpLoc}" scope="page" />
	<div class="container">
				<form role="form" action="controller" method="post"
					id="ticketOrderForm" class="input-not-empty">
					<input type="hidden" value="orderTicket" name="command" />

							<fmt:message key="label.ticket_order" />
							<p>
								<fmt:message key="message.enter_name" />
							</p>
						<div class="form-inline">
						<div class="form-group">
							<label class="control-label" for="firstName"><fmt:message
									key="label.first_name" />:</label> <input class="form-control"
								id="firstName" name="firstName" value="${currentUser.firstName}">
							<p class="text-warning" id="firstNameError"></p>
						</div>
						<div class="form-group">
							<label class="control-label" for="lastName"><fmt:message
									key="label.last_name" />:</label> <input class="form-control"
								id="lastName" name="lastName" value="${currentUser.lastName}">
							<p class="text-warning" id="lastNameError"></p>
						</div>
</div>
<div class="fine-button default">
						<button type="submit"  
							id="signUpSubmit">
							<fmt:message key="action.proceed" />
						</button>
						</div>
				</form>
			</div>
			<p>
						<fmt:message key="label.train_info" /></p>
					<div class="row">
						<st4:ticket bean="${ticketOrderBean}" display="full" />
					</div>
</body>
</html>