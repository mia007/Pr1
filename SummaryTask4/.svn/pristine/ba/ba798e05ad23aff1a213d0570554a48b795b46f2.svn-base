<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ taglib prefix="st4" uri="/WEB-INF/myTags.tld"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<body lang="${currentLocaleLocale}">
	<div class="container">
		<form role="form" action="controller" method="post"
			class="input-not-empty">
			<input type="hidden" name="command" value="addTrain" />
			<div class="form-inline">
				<div class="form-group">
					<label class="control-label" for="trainTag"><fmt:message
							key="label.train_tag" /></label>
					<div class="form-group">
						<input class="form-control" id="trainTag" name="trainTag">
						<p class="text-warning" id="trainTagError"></p>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label" for="trainPrice"><fmt:message
							key="label.price" /></label>
					<div class="form-group">
						<input class="form-control" id="trainPrice" name="trainPrice">
						<p class="text-warning" id="trainPriceError"></p>
					</div>
				</div>
			</div>
			<legend>
				<fmt:message key="label.select_stations" />
			</legend>
			<div id="stationContainer">
				<c:forEach begin="1" end="2" step="1">
					<div id="selectStation">
						<div class="form-inline">
							<div class="form-group">
								<label for="stationSelect"><fmt:message
										key="label.station" /></label> <select id="stationSelect"
									name="stationSelect" class="form-control">
									<c:forEach items="${stations}" var="s">
										<option>${s.name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label class="control-label" for="arrTime"><fmt:message
										key="label.arr_time" /></label> <input
									 id="arrTime" name="arrTime" required>
								<p class="text-warning" id="arrTimeError"></p>
							</div>
							<div class="form-group">
								<label class="control-label" for="depTime"><fmt:message
										key="label.dep_time" /></label>
								<div class="form-group">
									<input class="form-control bootstrap-timepicker timepicker"
										 id="depTime" name="depTime" required 
										 pattern="(0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}">
									<p class="text-warning" id="depTimeError"></p>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="form-inline">
				<div class="form-group">
					<div class="fine-button default">
						<button class="btn btn-default" onclick="addStationSelect(event)">
							+
							<fmt:message key="action.add" />
						</button>
					</div>
				</div>
				<div class="form-group">
					<div class="fine-button default">
						<button class="btn btn-default" type="submit">
							<b><fmt:message key="action.submit" /></b>
						</button>
					</div>
				</div>
			</div>
		</form>
		<c:if test="${not empty trainBeans}">
			<st4:displayTrainBeans trainBeans="${trainBeans}" display="train" />
		</c:if>
	</div>

</body>
</html>