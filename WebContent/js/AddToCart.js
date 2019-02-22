function addToCart(idBrano) {
	$.post("checkout", {"whatsend": "AddBrano", "idBrano": idBrano}, function() {
		console.log("Added to cart");
		update_qnt_span();
	});
	
}