<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<body>
	<c:set var="routeId" scope="session" value="${routeId}"></c:set>
	<c:if test="${not empty carriageList.types}">
		<div class="container" id="proceedContainer">
			<div class="row">
				<form role="form" action="controller">
					<input type="hidden" name="command" value="orderTicketView" /> <input
						type="hidden" name="carriageId" id="carriageId" /> <input
						type="hidden" name="seatNum" id="seatNum" />
						<div class="fine-button default">
							<button  type="submit"
								id="orderTicketButton">
								<fmt:message key="action.proceed" />
							</button>
					</div>
				</form>
			</div>
		</div>
	</c:if>
	<br>
	<div class="container">
			<c:forEach items="${carriageList.types}" var="t">
			<div class="form-group">
			<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">
								<fmt:message key="${t.name}" />
							</h3>
						</div>
						<div  class="form-inline">
										<div class="form-group">
										 <fmt:message key="label.carriage_tag" />
									</div>
										<div class="form-group">
										<fmt:message key="label.price" /> 
									</div>
									<div class="form-group">
										 <fmt:message
												key="label.available_seats" />
									</div>
								</div><div class="row">
								<c:forEach items="${carriageList.carriages}" var="c">
									<c:if test="${c.type==t}">
										<a href="#" class="list-group-item carriage-item row"
											id="${c.id}"><span class="col-md-4"><b>${c.tag}</b></span><span class="col-md-4">
											&#8372;${c.price}</span>
											<span class="badge">${c.type.seatNum - c.seatsTaken}</span></a>
										<div id="${c.id}" class="seat-container">
											<c:forEach begin="1" end="${c.type.seatNum}" step="1"
												var="seat">
												<c:choose>
													<c:when test="${empty c.seats[seat]}">
														<a href="javascript:void(0)" class="btn btn-warning seat"
															id="seatBtn">
													</c:when>
													<c:otherwise>
														<a href="#" class="btn btn-warning disabled"
															id="seatBtnDisabled">
													</c:otherwise>
												</c:choose>
												${seat }</a>
											</c:forEach>
										</div>
									</c:if>
									
								</c:forEach>
								</div>
							</div>
							</div>
		</c:forEach>
		</div>
	
</body>
</html>