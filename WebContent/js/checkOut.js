$(document).ready(function() {
	$('#place_order_btn').click(function() {
		$.ajax({
			type: "GET",
			url: "checkout",
			contentType: "json",
			data: {"whatsend": "VerifyDisponibility"},
			success: function(result, status, xhr) {
				var brani = JSON.parse(xhr.responseText);
				$('#brani_table').empty();
				var totale = 0.0;
				for (var brano of brani) {
					var row = "<tr id='product-tr-'" + brano.idBrano + ">";
					row += "<td class='details'><a href='brano?whatsend=GetDettaglioBrano&idBrano=" + brano.idBrano +"'>" + brano.titolo + "</a></td>";
					row += "<td class='details'><strong>" + brano.descrizione + "</strong></td>";
					row += "<td></td>";
					row += "<td></td>";
					row += "<td class='total text-center'><strong class='primary-color'>" + brano.prezzo + "€</strong></td>";
					row += "<td class='text-right'><a onclick='removeFromCart(" + brano.idBrano + ")' class='btn primary-btn fa fa-close'></a></td></tr>";
					$('#brani_table').append(row);
					totale += brano.prezzo;
				}
				$('#place_order_btn').hide();
				console.log(totale);
				$('#totale_th').html(totale + " €");
				$('#pay_with_card').show();
			},
			error: function(error) {
				console.log("Error ", error);
				var re = new RegExp(/^.*\//);
				document.location.href = re.exec(window.location.href);
			}
		});
	});
});