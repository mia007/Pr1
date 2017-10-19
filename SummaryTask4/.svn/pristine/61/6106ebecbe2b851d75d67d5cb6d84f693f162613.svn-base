$(document)
		.ready(
				function() {
					var lang = $('body').attr('lang');

					// displayFreeSeats
					$('.seat-container').css('display', 'none');
					$('.carriage-item').click(function() {
						var id = $(this).attr('id');
						$('#carriageId').val(id);
						$('div[id=' + id + ']').slideToggle('slow');
					});
					$('.seat').click(function() {
						// $(this).blur();
						var idVal = $(this).html().trim();
						$('#seatNum').val(idVal);
					});

					$('#addStationDiv').hide();
					$('#addNewStation').click(function(event) {
						event.preventDefault();
						$('#addStationDiv').toggle();
						$('#stationName').focus();
					});

				});


function addStationSelect(event) {
	event.preventDefault();
	var html = $('#selectStation').html();
	$('#stationContainer').append(html);
};

function addCarriageSelect(event) {
	event.preventDefault();
	var html = $('#selectCarriage').html();
	$('#carriageContainer').append(html);
};

/* Set the width of the side navigation to 280px */
function openNav() {
	document.getElementById("mySidenav").style.width = "280px";
}

/* Set the width of the side navigation to 0 */
function closeNav() {
	document.getElementById("mySidenav").style.width = "0";
}

$(document).ready(function() {
	console.log("ready!!");
	var lang = $('body').attr('lang');
	// $('#errorDiv').hide();
//	
//	$('.my-popup').editable({
//		error : function(response, newValue) {
//			return errors[lang][response.responseText];
//		}
//	});

	$('#signUpForm').submit(function(event) {
		event.preventDefault();
		var map = {
			"email" : /\w+@\w+\.\w+/,
			"password" : /\w{4,50}/

		};

		var proceed = true;
		var $sel, $err, div;
		for (key in map) {
			$sel = $('#' + key);
			div = $sel.parent();
			var regex = new RegExp(map[key]);
			$err = $('#' + key + "Error");

			if (!regex.test($sel.val())) {
				proceed = false;
				$err.html(errors[lang][key]);
			} else {
				$err.html('');
			}
		}
		var key = 'repPassword';
		var $sel = $('#' + key);
		$err = $('#' + key + 'Error');
		if ($sel.val() != $('#password').val()) {
			$err.html(errors[lang][key]);
			proceed = false;
		}

		if (proceed) {
			$(this).off("submit");
			this.submit();
		}
	});

	$('.input-not-empty').submit(function(event) {
		event.preventDefault();
		var proceed = true;
		var items = $(this).find('.form-control');

		for (var i = 0; i < items.length; i++) {
			var id = $(items[i]).attr('id');
			if ($(items[i]).val() == '') {
				$('#' + id + 'Error').html(errors[lang]['notEmpty']);
				proceed = false;
			} else {
				$('#' + id + 'Error').html('');
			}
		}
		if (proceed) {
			$(this).off("submit");
			this.submit();
		}
	});
	$('#orderTicketButton').click(function(event) {
		event.preventDefault();
		var proceed = false;
		var seatNum = $('#seatNum').val();
		if (seatNum == '') {
			alert(errors[lang]['chooseSeat']);
		} else {
			$(this).off("click");
			this.click();
		}
	});

});

function getStations(input) {
	$.ajax({
		url : "serv?command=getStations&filter=" + $(input).val(),
		type : "get",
		async : true,
		success : function(data) {
			var stations = JSON.parse(data).stations;

			var names = new Array();
			for (var i = 0; i < stations.length; i++) {
				names.push(stations[i].name);
			}

			// console.log(names);
			$(input).autocomplete({
				source : names
			});
		}
	});
};

function addRoute() {
	$.ajax({
		url : "serv?command=addRoute&trainId=" + $('#trainSelect').val()
				+ "&date=" + $('#date').val(),
		type : "post",
		async : true,
		error : function(data) {
			$('#mesDiv').hide();
			$('#errorDiv').show();
			$('#errorDiv p').html(data.statusText);
		},
		success : function(data) {
			$('#errorDiv').hide();
			$('#mesDiv').show();
			$('#mesDiv p').html(data);
		}
	});
}
var errors = {
		'en' : {
			"notEmpty" : "Cannot be empty",
			"chooseSeat" : "Choose a seat, please",
			'message.name_not_valid' : "Name is not valid",
			'message.try_again' : "Try again later"
		},
		'ru' : {
			"notEmpty" : "Заполните поле",
			"chooseSeat" : "Пожалуйста, выберите место",
			'message.name_not_valid' : 'Имя некорректно',
			'message.try_again' : "Попробуйте позже"
		}
	}

	$(document).ready(function() {
		var lang = $('body').attr('lang');

		$('.my-popup').editable({
			error : function(response, newValue) {
				return errors[lang][response.responseText];
			}
		});
	});


