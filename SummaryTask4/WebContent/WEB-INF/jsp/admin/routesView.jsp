<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ taglib prefix="st4" uri="/WEB-INF/myTags.tld"%>
<script src="js/date.js"></script>
<script src="js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-datepicker3.css" />
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<body lang="ru">
	<div class="container">
		<form action="controller" method="post" >
			<input type="hidden" name="command" value="addRoute" />
				<h2><fmt:message key="label.add_route" /></h2>
			<c:if test="${not empty routeAddError}">
				<div class="alert alert-dismissible alert-warning">
					<button type="button" class="close" data-dismiss="alert">&#10060;</button>
					<p>
						<fmt:message key="${routeAddError}" />
					</p>
				</div>
			</c:if>
			<c:if test="${not empty routeAddMes}">
				<div class="alert alert-dismissible alert-success" id="mesDiv">
					<button type="button" class="close" data-dismiss="alert">&#10060;</button>
					<p>
						<fmt:message key="${routeAddMes}" />
					</p>
				</div>
			</c:if>
			<div  class="form-inline">
					<div class="form-group">
						<select id="trainSelect" name="trainSelect" class="form-control">
							<c:forEach items="${trainBeans}" var="t">
								<option value="${t.trainId}">${t.trainTag}
									&nbsp;&#124;&nbsp;&nbsp;${t.stationFrom} &mdash; ${t.stationTo}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<input class="form-control date" id="date" name="date"
							data-provide="datepicker-inline"  placeholder="<fmt:message key="label.date" />"/>
						<p class="text-warning" id="date">${dateError}</p>
					</div>
					</div>
			<div class="row">
				<div class="fine-button default">
					<button type="submit" class="btn btn-default btn-block">
						<fmt:message key="action.add" />
					</button>
				</div>
			</div>
		</form>
		<br>
		<form  class="form-inline" role="form" action="controller" method="get">
			<input type="hidden" value="routeView" name="command" />
					<fmt:message key="label.show_routes_by_date" />
				<c:if test="${not empty errors}">
					<div class="alert alert-dismissible alert-warning" id="errorDiv">
						<ul>
							<c:forEach items="${errors}" var="e">
								<li><fmt:message key="${e}" /></li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
				<st4:datePickers/>
				<div class="fine-button default">
				<button type="submit" >
					<fmt:message key="action.proceed" />
				</button>
				</div>
		</form>
		<c:if test="${not empty routes}">
			<st4:displayTrainBeans trainBeans="${routes}" display="route" />
		</c:if>
	</div>
</body>
</html>