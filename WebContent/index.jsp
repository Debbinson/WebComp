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
	<title>Rockstar</title>
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
</head>

<body>

	<!--================Header Menu Area =================-->
	<header class="header_area">
		<div class="top_menu row m0">
			<div class="container-fluid">
				<div class="float-right">
					<ul class="right_side">
						<li>
							<c:if test="${LOGINED_USER==null}">
							<a href="login">
								Login/Register
							</a>
							</c:if>
							<c:if test="${LOGINED_USER!=null}">
							<a href="user">
								My Account
							</a>
							</c:if>
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

	<!--================Home Banner Area =================-->
	<section class="banner_area">
		<div class="banner_inner d-flex align-items-center">
			<div class="container">
			</div>
		</div>
	</section>
	<!--================End Home Banner Area =================-->

	<!--================Category Product Area =================-->
	<section class="cat_product_area section_gap">
		<div class="container-fluid">
			<div class="row flex-row-reverse">
				<div class="col-lg-9">
					
					<div class="latest_product_inner row" id = "row-catalogo">			
						<c:forEach items="${CATALOGO}" var="brano">
						<c:if test="${brano.disponibile==true}">
						
						<div class="col-lg-3 col-md-3 col-sm-6">
							<div class="f_p_item">
								<div class="f_p_img">
									<img class="img-fluid" src="img/product/feature-product/f-p-1.jpg" alt="">
									<div class="p_icon">
									<c:set var="download" value="${false}" />
										<c:if test="${brano.prezzo==0}">
											<c:set var="download" value="${true}" />
										</c:if>
										<c:forEach items="${UPLOADED_SONG}" var="uploaded">
											<c:if test="${uploaded.idBrano==brano.idBrano}">
												<c:set var="download" value="${true}" />
											</c:if>
										</c:forEach>
										<c:forEach items="${PURCHASED_SONG}" var="purchased">
											<c:if test="${purchased.idBrano==brano.idBrano}">
												<c:set var="download" value="${true}" />
											</c:if>
										</c:forEach>
										
										<c:if test="${download==true}">
											<a href="SONG/${brano.path}" download><i class="fa fa-arrow-down"></i></a>
										</c:if>
										<c:if test="${download==false}">
											<button class="genric-btn danger-border circle" onclick="addToCart(${brano.idBrano})"><i class="fa fa-shopping-cart"></i></button>
										</c:if>
									</div>
									
								</div>
								<div class = "product-rating">
									<c:forEach begin="1" end="${brano.stelle}" varStatus="loop">
										<i class= "fa fa-star"></i>
									</c:forEach>
									<c:forEach begin="1" end="${5-brano.stelle}" varStatus="loop">
										<i class= "fa fa-star-o empty"></i>
									</c:forEach>
								
								</div>
								<a href="brano?whatsend=GetDettaglioBrano&idBrano=${brano.idBrano}">
									<h4>${brano.titolo}</h4>
								</a>
								<h5>${brano.prezzo} €</h5>
							</div>
						</div>
						</c:if>
						</c:forEach>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="left_sidebar_area">
						<aside class="left_widgets p_filter_widgets">
							<div class="l_w_title">
								<h3>Categories</h3>
							</div>
							<div class="widgets_inner">
								<ul class="list" id ="category-list"></ul>								
							</div>
						</aside>
						<aside class="left_widgets p_filter_widgets">
							<div class="l_w_title">
								<h3>Product Filters</h3>
							</div>
							<div class="widgets_inner">
								<h4>Artist</h4>
								<ul class="list" id ="artist-list">
								</ul>
							</div>
							<div class="widgets_inner">
								<h4>Price</h4>
								<div class="range_item">
									<div id="slider-range"></div>
									<div class="row m0">
										<label for="amount">Insert max price:    </label>
										<input type="text" id="price" placeholder = "example: 100€">
									</div>
								</div>
							</div>
						</aside>
					</div>
				</div>
			</div>

		</div>
	</section>
	<!--================End Category Product Area =================-->






	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/popper.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/stellar.js"></script>
	<script src="vendors/lightbox/simpleLightbox.min.js"></script>
	<script src="vendors/nice-select/js/jquery.nice-select.min.js"></script>
	<script src="vendors/isotope/imagesloaded.pkgd.min.js"></script>
	<script src="vendors/isotope/isotope-min.js"></script>
	<script src="vendors/owl-carousel/owl.carousel.min.js"></script>
	<script src="js/jquery.ajaxchimp.min.js"></script>
	<script src="js/mail-script.js"></script>
	<script src="vendors/jquery-ui/jquery-ui.js"></script>
	<script src="vendors/counter-up/jquery.waypoints.min.js"></script>
	<script src="vendors/counter-up/jquery.counterup.js"></script>
	<script src="js/theme.js"></script>
	<script src="js/index.js"></script>
	<script src="js/AddToCart.js"></script>
	
	
</body>

</html>