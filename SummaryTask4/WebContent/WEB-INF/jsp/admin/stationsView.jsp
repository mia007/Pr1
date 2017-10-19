<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<body lang="${currentLocaleLocale.language}">
	<div class="container">
		<%@include file="/WEB-INF/jspf/errors.jspf"%>
		<h2>
			<fmt:message key="label.stations" />
		</h2>
		<div class="fine-button default">
		<a href="#" class="text-muted" id="addNewStation">
				<fmt:message key="action.add_new_station" />
		</a>
		</div>
		<br>
		<div id="addStationDiv">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="addStation" />
					<div class="input-group">
						<input class="form-control" name="stationName" id="stationName"
							type="text">
							<button class="btn btn-info" type="submit">
								<b>+</b>
							</button>
				</div>
			</form>
		</div>

		<table class="table table-hover">
			<c:forEach items="${stations}" var="s">
				<tr>
					<td>${s.name}</td>
					<td><form action="controller" method="post">
							<input type="hidden" name="command" value="deleteStation" /> <input
								type="hidden" name="stationId" value="${s.id}" />
							<button class="btn btn-danger btn-sm">
								<fmt:message key="action.delete" />
							</button>
						</form></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>