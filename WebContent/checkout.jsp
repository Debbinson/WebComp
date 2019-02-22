<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	

<!DOCTYPE html>
<html lang="it">

<head>
<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" href="img/favicon.png" type="image/png">
	<title>Rockstar-Checkout</title>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="vendors/linericon/style.css">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
	<link rel="stylesheet" href="vendors/lightbox/simpleLightbox.css">
	<link rel="stylesheet" href="vendors/nice-select/css/nice-select.css">
	<link rel="stylesheet" href="vendors/animate-css/animate.css">
	<link rel="stylesheet" href="vendors/jquery-ui/jquery-ui.css">
	<!-- main css -->
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/responsive.css">
<body>
<!--================Header Menu Area =================-->
	<header class="header_area">
		<div class="top_menu row m0">
			<div class="container-fluid">
				<div class="float-right">
					<ul class="right_side">
						<li>
							<a href="user">
								My Account
							</a>
						</li>
						<li>
							<a href="checkout">
								Go to cart
							</a>
						</li>
						<li>
							<a href="#">
								About us
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="main_menu">
			<nav class="navbar navbar-expand-lg navbar-light">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<a class="navbar-brand logo_h" href="index">
						<img src="img/logo.png" alt="">
					</a>
					<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
					 aria-expanded="false" aria-label="Toggle navigation">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse offset" id="navbarSupportedContent">
						<div class="row w-100">
							<div class="col-lg-7 pr-0">
								<input id="search-bar-brano" class="form-control search-field input" type="text" placeholder="Search song" >
							</div>

							<div class="col-lg-5">
					
							</div>
						</div>
					</div>
				</div>
			</nav>
		</div>
	</header>
	<!--================Header Menu Area =================-->
<section class="checkout_area section_gap">
		<div class="container">
			<div class="col-lg-4">
						<div class="order_box" >
							<h2>Your Order</h2>
							<ul class="list" id="box">
								<li>
									<a >Product
										<span>Total</span>
									</a>
								</li>
							<c:forEach items="${CARRELLO_UTENTE}" var="brano">	
								<li id="song${brano.idBrano}">
								<a onclick='removeFromCart(${brano.idBrano})'class="btn primary-btn fa fa-close"></a>
									<a href='brano?whatsend=GetDettaglioBrano&idBrano=${brano.idBrano}' ">${brano.titolo}
										
										<span class="last">${brano.prezzo} €</span>
									</a>
								</li>
								
							</c:forEach>
							</ul>
							<ul class="list list_2">
								<li>
									<c:set var="totale" value="${0}" />
									<c:forEach items="${CARRELLO_UTENTE}" var="brano">
										<c:set var="totale" value="${totale+brano.prezzo}" />
									</c:forEach>
									<a>Total
										<span>${totale} €</span>
									</a>
								</li>
							</ul>
							<div class="pull-right">
							<button id="place_order_btn" class="primary-btn">Place Order</button>
							<form id="pay_with_card" action='checkout?whatsend=StripePayment&total=${totale*100}' method="post" style="display:none">
								<script src="https://checkout.stripe.com/checkout.js"
									class="stripe-button"
									data-key="pk_test_DEjyvP1VC9EQDQwaPQX8HUHY"
									data-amount='${totale*100}'
									data-name='${LOGINED_USER.nome } ${LOGINED_USER.cognome }'
									data-description="Place Order!"
									data-image="https://stripe.com/img/documentation/checkout/marketplace.png"
									data-locale="auto" data-currency="eur">
  										</script>
							</form>
						</div>
						</div>
					</div>
				</div>
</section>

	<script src="js/jquery.min.js"></script>
	<script src="js/RemoveFromCart.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/slick.min.js"></script>
	<script src="js/nouislider.min.js"></script>
	<script src="js/main.js"></script>
	<script src="js/UpdateQty.js"></script>
	<script src="js/checkOut.js"></script>
</body>
</html>
