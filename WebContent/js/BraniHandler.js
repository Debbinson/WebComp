var downloads = [];
var brani_utente = [];

$(document).ready(function() {
	get_brani_utente();
	get_brani_acquistati();
});

function get_brani_utente() {
	$.ajax({
		type: "GET",
		url: "brano",
		contentType: "json",
		data: {"whatsend": "GetBraniUtente"},
		success: function(result, status, xhr) {
			console.log("Entered: ", status);
			$('#your-upload-table').find("tr:gt(0)").remove();
			brani_utente = JSON.parse((xhr.responseText.trim().split('\n'))[0]);
			for (var i = 0; i < brani_utente.length; i++) {
				var row = "<tr>"
					row += "<td><a href='brano?whatsend=GetDettaglioBrano&idBrano=" + brani_utente[i].idBrano + "'>" + brani_utente[i].titolo + "</td>"
					row += "<td>" + brani_utente[i].date_ins + "</td>"
					
					if (brani_utente[i].disponibile == true)
						row += "<td><a onclick='changeDisponibility(" + brani_utente[i].idBrano + ")' class='btn primary-btn fa fa-ban glyphicon'></td>"
					else
						row += "<td><a onclick='changeDisponibility(" + brani_utente[i].idBrano + ")' class='btn primary-btn fa fa-check glyphicon'></td>"
						
					row += "</tr>"
				$('#your-upload-table tr:last').after(row);
			}
		},
		error: function(error) {
			console.log("Error: ", error);
		}
	});
}

function get_all_brani() {
	$.ajax({
		type: "GET",
		url: "brano",
		contentType: "json",
		data: {"whatsend": "GetBrani"},
		success: function(result, status, xhr) {
			console.log("Entered: ", status);
			$('#songs-table').find("tr:gt(0)").remove();
			brani = JSON.parse((xhr.responseText.trim().split('\n'))[0]);
			for (var i = 0; i < brani.length; i++) {
				var row = "<tr>"
					row += "<td><a href='brano?whatsend=GetDettaglioBrano&idBrano=" + brani[i].idBrano + "'>" + brani[i].titolo + "</td>"
					row += "<td>" + brani[i].date_ins + "</td>"
					
					if (brani[i].disponibile == true)
						row += "<td><a onclick='changeDisponibilityAll(" + brani[i].idBrano + ")' class='btn primary-btn fa fa-ban glyphicon'></td>"
					else
						row += "<td><a onclick='changeDisponibilityAll(" + brani[i].idBrano + ")' class='btn primary-btn fa fa-check glyphicon'></td>"
						
					row += "</tr>"
				$('#songs-table tr:last').after(row);
			}
		},
		error: function(error) {
			console.log("Error: ", error);
		}
	});
}

function get_brani_acquistati() {
	$.get("brano", {"whatsend": "GetAcquisti"}, function(result, status, xhr) {
		downloads = JSON.parse(xhr.responseText.trim());
		$('#acquisto-table').find("tr:gt(0)").remove();
		for (var i = 0; i < downloads.length; i++) {
			var row = "<tr>";
			row += "<td><a href=brano?whatsend=GetDettaglioBrano&idBrano=" + downloads[i].idBrano + ">" + downloads[i].titolo + "</a></td>";
			row += "<td>" + downloads[i].date_ins + "</td>";
			row += "<td><a href='SONG/" + downloads[i].path + "' class='btn primary-btn fa fa-arrow-down glyphicon' download></td>";
			row += "</tr>";
			$('#acquisto-table tr:last').after(row);
		}
	}, "json");
}
function changeDisponibilityAll(idBrano) {
	$.post("brano", {"whatsend": "ChangeDisponibilityAll", "idBrano": idBrano}, function(result, status, xhr) {
		console.log("Entered ", status);
		get_all_brani();
	});
}

function changeDisponibility(idBrano) {
	$.post("brano", {"whatsend": "ChangeDisponibility", "idBrano": idBrano}, function(result, status, xhr) {
		console.log("Entered ", status);
		get_brani_utente();
	});
}