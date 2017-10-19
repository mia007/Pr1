<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<body lang="${currentLocaleLocale.language}">
	<div class="container">
		<%@include file="/WEB-INF/jspf/errors.jspf"%>
		<h2>
			Tickets
		</h2>

		<table class="table-hover">
		<tr>
		<th>Email</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Tickets&nbsp;number</th>
					</tr>
			<c:forEach items="${ticketBean}" var="t">
				
				<tr>
					<td>${t.email}</td>
					<td>${t.firstName}</td>
					<td>${t.lastName}</td>
					<td>${t.ticketNum}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>