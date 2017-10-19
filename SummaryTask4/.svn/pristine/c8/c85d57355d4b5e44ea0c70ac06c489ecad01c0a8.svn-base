<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>

<div class="jumbotron">
	<div class="container">
		<c:choose>
			<c:when test="${not empty tickets }">
				<p>
					<b><fmt:message key="message.ordered_tickets" /></b>
				</p>
		`
		<c:forEach items="${tickets}" var="t">
						<div class="row">
							<st4:ticket bean="${t}" display="short" />
						</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<b><fmt:message key="message.no_tickets" /></b>
			</c:otherwise>
		</c:choose>
	</div>
</div>

</body>
</html>