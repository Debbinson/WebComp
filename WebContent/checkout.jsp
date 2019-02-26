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
		<div class="main_menu">
			<nav class="navbar navbar-expand-lg navbar-light">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<a class="navbar-brand logo_h" href="index.html">
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
								<ul class="nav navbar-nav center_nav pull-right">
									<li class="nav-item">
										<a class="nav-link" href="index.html">Home</a>
									</li>

										<li class="nav-item submenu dropdown">
											<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Social</a>
											<ul class="dropdown-menu">
												<li class="nav-item">
													<a class="nav-link" href="#">Facebook</a>
													<li class="nav-item">
														<a class="nav-link" href="#">Instagram</a>
														<li class="nav-item">
															<a class="nav-link" href="#">Twitter</a>
														</li>
											</ul>
											</li>
											<li class="nav-item">
												<a class="nav-link" data-toggle="modal" data-target="#about-us">About us</a>
											</li>
								</ul>
							</div>
							
								 <div class="modal fade" id="about-us" role="dialog">
							    <div class="modal-dialog">		
							    
							      <!-- Modal content-->
							      <div class="modal-content">
							      <h2 align = "center">About us!</h2>
							        <div class="modal-header">
							          <button type="button" class="close" data-dismiss="modal">&times;</button>
							        </div>
							        <div class="modal-body">
							          <div class="row">

							
							<!-- Giovanni Profile -->
									<div class="col-lg-6">
										<a href="https://github.com/gioiannu">
											<div class="blog_right_sidebar">
												<aside class="single_sidebar_widget author_widget">
													<img class = "author_img rounded-circle" src="./img/giovanni.jpg" alt="">
												</aside>
													<h3 align="center">Giovanni Iannuzzi</h3>
											</div>
										</a>
										<h5 class="product-name" align="center">Developer</h5>
									</div>
									<!-- Giovanni Profile -->
									
									<!-- Alessandro Profile -->
									<div class="col-lg-6">
										<a href="https://github.com/Debbinson">
											<div class="blog_right_sidebar">
												<aside class="single_sidebar_widget author_widget">
													<img class="author_img rounded-circle" src="./img/alessandro.jpg" alt="">
												</aside>
													<h3 align="center">Alessandro De Bartolo</h3>
											</div>
										</a>
										<h5 class="product-name" align="center">Developer</h5>
									</div>
									<!-- Alessandro Profile -->
							
									</div>
							        </div>
							        <div class="modal-footer">
							          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							        </div>
							      </div>
							      
							    </div>
							  </div>
								
								
								
							<div class="col-lg-5">
								<ul class="nav navbar-nav navbar-right right_nav pull-right">
										<hr>
										
										<li class="nav-item">
										<a href="logout" class="icons">
											<i class="fa fa-sign-out" aria-hidden="true"></i>
											
										</a>
										</li>
										
										<hr>
									
									
										<hr>
	
										<li class="nav-item">
											<a href="user" class="icons">
												<i class="fa fa-user" aria-hidden="true"></i>
											</a>
										</li>
									<hr>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</nav>
		</div>
	</header>
	<!--================Header Menu Area =================-->
	
	
		<section class="banner_area">
        <div class="banner_inner d-flex align-items-center">
            <div class="container">
                <div class="banner_content text-center">
                
                    <h2>Shopping Cart</h2>
                    
                    
                </div>
            </div>
        </div>
    </section>       	
	
	<!-- ========================= CHECKOUT====================0 -->
<!--  <section class="banner_inner d-flex align-items-center"> -->	
		<section class="cart_area">
		<div class="container">
		<c:set var="totale" value="${0}" />
		<c:if test="${empty CARRELLO_UTENTE}">
		<h3 class="text-center">Shopping cart is empty</h3>
		<h5 class="text-center">Click here to vist our catalogue</h5>
		<p align="center">
		<a class="gray_btn center" href="index">Catalogue</a>	
		</p>								
		</c:if>
	<c:if test="${not empty CARRELLO_UTENTE}">
		<h3 class="text-center">Your order</h3>
			<div class="cart_inner">
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Product</th>
								<th scope="col">Price</th>
								<th scope="col">Rating</th>
								<th scope="col">Total</th>
							</tr>
						</thead>
						<tbody>
						
						<c:forEach items="${CARRELLO_UTENTE}" var="brano">	

							<tr>
								<td>
									<div class="media">
										<div class="d-flex">
											<img src="img/product/single-product/cart-1.jpg" alt="">
										</div>
										<div class="media-body">
											<a href='brano?whatsend=GetDettaglioBrano&idBrano=${brano.idBrano}'>${brano.titolo}</a>
										</div>
									</div>
								</td>
								<td>
									<h5>${brano.prezzo} €</h5>
								</td>
								<c:set var="totale" value="${totale+brano.prezzo}" />
								<td>
									<div class="product_count">
											<strong>${brano.stelle}</strong>									
									</div>
								</td>
								<td>
									<h5>${totale} €</h5>
								</td>
								<td class="text-right">
										<a onclick='removeFromCart(${brano.idBrano})' class="icons">
											<i class="fa fa-close" aria-hidden="true" id="delete_button"></i>
											
										</a>
								</td>
							</tr>
							</c:forEach>
							<tr>
								<td>

								</td>
								<td>

								</td>
								<td>
									<h5>Subtotal</h5>
								</td>
								<td>
									<h5>${totale} €</h5>
								</td>
								
							</tr>
						
							<tr class="out_button_area">
								
								<td>

								</td>
								<td>

								</td>
								<td>
									<div class="checkout_btn_inner">
										<a class="gray_btn" href="index">Continue Shopping</a>									
										<button id="place_order_btn" class="main_btn">Proceed to checkout</button>
										<form id="pay_with_card" class="pull-right" action='checkout?whatsend=StripePayment&total=${totale*100}' method="post" style="display:none">
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
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			</c:if>
		</div>
	</section>
		
		
		
		
		<!-- <div class="blog_right_sidebar">
			<div class="col-lg-12   " id="checkout_area">
			<h2>Your Order</h2>
			<table class="table" id="product-table">
			
							<thead>
								<tr>
									<th>Name</th>
									<th>Rating</th>
									<th></th>
									<th></th>
									<th class="text-center">Price</th>
									<th class="text-right"></th>
								</tr>
							</thead>
					 <div class="order_box" >  
							
							
							<tbody id="brani_table">
							
							<c:forEach items="${CARRELLO_UTENTE}" var="brano">	
								<tr id="song${brano.idBrano}">
									<td class="details"><a href='brano?whatsend=GetDettaglioBrano&idBrano=${brano.idBrano}'>${brano.titolo}</a></td>
									<td class="details"><strong>${brano.stelle}</strong></td>
									<td></td>
									<td></td>											
										<td class="total text-center"><strong>${brano.prezzo} €</strong></td>
										<td class="text-right">
										<a onclick='removeFromCart(${brano.idBrano})' class="icons">
											<i class="fa fa-close" aria-hidden="true" id="delete_button"></i>
											
										</a>
										</td>
									
									
								</tr>
								
							</c:forEach>
							</tbody>
							
							<tfoot>
								<tr>
									<c:set var="totale" value="${0}" />
									<c:forEach items="${CARRELLO_UTENTE}" var="brano">
										<c:set var="totale" value="${totale+brano.prezzo}" />
									</c:forEach>
									<th class="empty" colspan="3"></th>
									<th>TOTAL</th>
									<th id="totale_th" colspan="2" class="total">${totale} €</th>
								</tr>
							</tfoot>
						</table>	
							
							<div class="pull-right" id="button_for_payment">
							<button id="place_order_btn" class="genric-btn danger-border circle">Place Order</button>
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
					</div>   -->
 <!-- </section>   -->

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
