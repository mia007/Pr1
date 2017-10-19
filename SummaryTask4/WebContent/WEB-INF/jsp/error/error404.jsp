<%@page isErrorPage="true"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<c:set var="title" value="Oops" scope="page" />
<div class="jumbotron">
	<div class="container">
		<h2>404 Not Found</h2>
		<p>
			<fmt:message key="message.not_available" />
		</p>
		<st4:goBack />
	</div>
</div>
</body>
</html>