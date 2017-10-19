<%@ include file="/WEB-INF/jspf/head.jspf"%>
<script src="js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-datepicker3.css" />
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<form class="form-inline" action="controller" method="get" role="form">
	<div class="form-group">
		<input type="hidden" name="command" value="findTrains" /> <label
			class="control-label" for="stationFrom"> </label> <input
			class="form-control" id="stationFrom" placeholder=<fmt:message key="label.from" /> name="stationFrom"
			onkeyup="getStations(this)" value="${requestScope.stationFrom}" />
		<p class="text-warning" id="stationFromError">
			<c:if test="${not empty stationFromError}">
				<fmt:message key="${stationFromError}" />
			</c:if>
		</p>
	</div>
	<div class="form-group">
		<input class="form-control" id="stationTo"
			placeholder=<fmt:message key="label.to" /> name="stationTo"
			onkeyup="getStations(this)" value="${requestScope.stationTo}">
		<i class="change-dir"></i>
		<p class="text-warning" id="stationToError">
			<c:if test="${not empty stationToError}">
				<fmt:message key="${stationToError}" />
			</c:if>
		</p>
	</div>
	<div class="opt">
		<label class="control-label" for="date"> <fmt:message
				key="label.date" />:
		</label> <input class="form-control date" id="date" name="date"
			value="${requestScope.date}" data-provide="datepicker-inline" />
		<p class="text-warning" id="dateError">
			<c:if test="${not empty dateError}">
				<fmt:message key="${dateError}" />
			</c:if>
		</p>
	</div>
	<div class="button">
		<div class="fine-button default">
			<button type="submit">
				<fmt:message key="action.search" />
			</button>
		</div>
	</div>
</form>
<br>
<br>
<div class="container">
	<c:choose>
		<c:when test="${not empty sessionScope.trainBeans}">
			<st4:displayTrainBeans trainBeans="${sessionScope.trainBeans}"
				display="full" />
		</c:when>
		<c:otherwise>
</div>
</c:otherwise>
</c:choose>
</div>
</body>
</html>