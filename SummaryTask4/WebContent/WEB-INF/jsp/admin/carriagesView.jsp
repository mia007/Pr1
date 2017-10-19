<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ taglib prefix="st4" uri="/WEB-INF/myTags.tld"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<body lang="${currentLocaleLocale}">
	<div class="container">
		<c:if test="${not empty errors}">
			<div class="alert alert-dismissible alert-warning" id="errorDiv">
				<ul>
					<c:forEach items="${errors}" var="e">
						<li>${e}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		<h2>
			<fmt:message key="label.carriages" />
		</h2>
		<table class="table table-hover">
		<c:forEach items="${carriages}" var="c">
	<tr>
		<td>${c.tag}</td>
		<td><fmt:message key="${c.type.name}" />
		</td>
		<td>${c.price}</td>
	</tr>
</c:forEach>
		</table>
		<br>
		<form role="form" action="controller" class="input-not-empty"
			method="post">
			<input type="hidden" name="command" value="addCarriages" /> <input
				type="hidden" name="trainId" value="${trainId}" />
				<b><fmt:message key="label.add_carriages" /></b>
			<div id="carriageContainer">
				<div id="selectCarriage">
					<div  class="form-inline">
							<div class="form-group">
								<label class="control-label" for="carTag"><fmt:message
										key="label.carriage_tag" /></label> <input class="form-control"
									id="carTag" name="carTag">
								<p class="text-warning" id="carTagError"></p>
							</div>
							<div class="form-group">
								<label for="carTypeSelect"><fmt:message
										key="label.carriage_type" /></label> <select id="carTypeSelect"
									name="carTypeSelect" class="form-control">
									<c:forEach items="${carTypes}" var="s">
										<option value="${s.id}"><fmt:message key="${s.name}" /></option>
									</c:forEach>
								</select>
						</div>
							<div class="form-group">
								<label for="carPrice"><fmt:message key="label.price" /></label>
								<input id="carPrice" name="carPrice" class="form-control" />
								<p class="text-warning" id="carPriceError"></p>
							</div>
							
						</div>
					</div>
			<div class="fine-button default">
				<button  type="submit">
					<fmt:message key="action.submit" />
				</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>