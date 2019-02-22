function removeFromCart(idBrano) {
	$.post("checkout", {"whatsend": "RemoveBrano", "idBrano": idBrano}, function(status, result, xhr) {
		console.log("Entered ", status);
		$("#song" + idBrano).remove();
		console.log($("#box").length);
		if($("box").length <= 1)
			location.reload();
	}, "json");
}