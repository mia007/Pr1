<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<body>
	<div class="container">
		<p>
		<h2>
			<fmt:message key="label.admin_welcome" />
			!
		</h2>
		</p>
		<br />
		<div class="fine-button default">
			<a href="controller?command=stationView"><fmt:message
					key="action.manage_stations" /></a>
		</div>
		<div class="fine-button default">
			<a href="controller?command=routeView"><fmt:message
					key="action.manage_routes" /></a>
		</div>
		<div class="fine-button default">
			<a href="controller?command=trainView"><fmt:message
					key="action.manage_trains" /></a>
		</div>
		<div class="fine-button default">
			<a href="controller?command=ticketNumView">Tickets</a>
		</div>
	</div>
</body>
</html>