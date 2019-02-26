
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">


<script src="js/audio.js"></script>
<head>

	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" href="img/favicon.png" type="image/png">
	<title>Rockstar - ${BRANO.titolo}</title>
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
													<a class="nav-link" href="https://it-it.facebook.com/">Facebook</a>
													<li class="nav-item">
														<a class="nav-link" href="https://www.instagram.com/?hl=it">Instagram</a>
														<li class="nav-item">
															<a class="nav-link" href="https://twitter.com/login?lang=it">Twitter</a>
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
									<c:if test="${LOGINED_USER==null}">
									<hr>
									<li class="nav-item">
										<a href="login" class="icons">
											<i class="fa fa-sign-in" aria-hidden="true"></i>
										</a>
									</li>
									</c:if>
									<c:if test="${LOGINED_USER!=null}">
										<hr>
										
										<li class="nav-item">
										<a href="logout" class="icons">
											<i class="fa fa-sign-out" aria-hidden="true"></i>
											
										</a>
									</li>
										
										<li class="nav-item">
											<a href="user" class="icons">
												<i class="fa fa-user" aria-hidden="true"></i>
											</a>
										</li>
	
										<hr>
									
									
										<hr>
	
										<li class="nav-item">
										<div class="header-btns-icon">
											<a href="checkout" class="icons">
												<i class="fa fa-shopping-cart"></i>
												 <span id="qtySpan" class="qty"></span>
											</a>
											</div>
										</li>
									</c:if>
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

	
	<!--================Single Product Area =================-->
	<div class="product_image_area">
		<div class="container">
			<div class="row s_product_inner">
				<div class="col-lg-6">
					<div class="s_product_img">
						<div id="carouselExampleIndicators">
							<div class="carousel-inner">
								<div class="carousel-item active">
								<div class= "container">
									<img class="d-block w-100" src="img/product/single-product/s-product-1.jpg">
									<div class= "audioBlock">
										<audio id="player" controls onloadeddata="reproducePreview(this,${BRANO.prezzo})" name="media" >
											<source src="SONG/${BRANO.path}" type="audio/mp3">	
											<source src="SONG/${BRANO.path}" type="audio/ogg">
										</audio>
										
									</div>
								
								</div>
									
								</div>
								<div class="carousel-item">
									
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-5 offset-lg-1">
					<div class="s_product_text">
						<h3><c:out value="${BRANO.titolo}"></c:out></h3>
						<h2><c:out value="${BRANO.prezzo}"></c:out>€</h2>
						<ul class="list">
							<li>
								<a class="active" href="#">
									<span>Category:</span>  <c:out value="${CATEGORIA_BRANO.nome}"></c:out></a>
							</li>
						</ul>
						<p><c:out value="${BRANO.descrizione}"></c:out></p>
						<div class="card_area">
						
									<c:set var="download" value="${false}" />
										<c:if test="${BRANO.prezzo<=0}">
											<c:set var="download" value="${true}" />
										</c:if>
										<c:forEach items="${UPLOADED_SONG}" var="uploaded">
											<c:if test="${uploaded.idBrano==BRANO.idBrano}">
												<c:set var="download" value="${true}" />
											</c:if>
										</c:forEach>
										<c:forEach items="${PURCHASED_SONG}" var="purchased">
											<c:if test="${purchased.idBrano==BRANO.idBrano}">
												<c:set var="download" value="${true}" />
											</c:if>
										</c:forEach>
						
						
						
						
						
						<c:if test="${download== true}">
							<a href="SONG/${BRANO.path}"class="genric-btn danger-border circle" download>Download</a>
						</c:if>
						<c:if test="${download==false}">
							<a class="genric-btn danger-border circle" onclick="addToCart(${BRANO.idBrano})">Add to Cart</a>
						</c:if>
										
							<a class="icon_btn" href="#">
								<i class="lnr lnr lnr-diamond"></i>
							</a>
							<a class="icon_btn" href="#">
								<i class="lnr lnr lnr-heart"></i>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--================End Single Product Area =================-->

	<!--================Product Description Area =================-->
	<section class="product_description_area">
		<div class="container">
			<div class="nav nav-tabs" id="myTab" role="tablist">
				<h3>Reviews</h3>
			</div>
			<div class="tab-content" id="myTabContent">
				
				<div class="tab-pane fade show active" id="review" role="tabpanel" aria-labelledby="review-tab">
					<div class="row">
						<div class="col-lg-6">
							<div class="row total_rate">
								<div class="col-6">
									<div class="box_total">
										<div id="starsofsong" class="product-rating">
								
										</div>
									</div>
								</div>
								<div class="col-6">
									<div id="number_of_stars" class="rating_list" >
									
									</div>
								</div>								
							</div>
							
							<div class="review_list" id= "comment-section">
								
							</div>
						</div>
						<div class="col-lg-6">
						
							<div class="review_box">
							<c:if test="${LOGINED_USER!=null}">
											<h4>Add a Review</h4>
											<div class="form-group">
											<div class="input-rating">
												<strong class="text-uppercase">Your Rating: </strong>
												<div class="stars">
													<input type="radio" id="star5" name="rating" value="5" /><label for="star5"></label> 
													<input type="radio" id="star4" name="rating" value="4" /><label for="star4"></label> 
													<input type="radio" id="star3" name="rating" value="3" checked="checked" /><label for="star3"></label> 
													<input type="radio" id="star2" name="rating" value="2" /><label for="star2"></label> 
													<input type="radio" id="star1" name="rating" value="1" /><label for="star1"></label>
												</div>
											</div>
										</div>
											<form class="row contact_form" id="contactForm">
												<div class="col-md-12">
													<div class="form-group">
														<textarea class="form-control" id="message" placeholder="Review"></textarea>
													</div>
												</div>
												<div class="col-md-12">
													<a class="btn submit_btn" id="add-comment">Submit Now</a>
												</div>
											</form>
								</c:if>
								<c:if test="${LOGINED_USER==null}">
									<h4>Do you want to rate this song? Log in</h4>
									<div class="col-md-12">
										<a class="btn submit_btn" href="login">Log in</a>
										<a href="registration">Not already registered? Tap here to become a rockstar</a>
									</div>
								</c:if>
							</div>
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================End Product Description Area =================-->

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="vendors/lightbox/simpleLightbox.min.js"></script>
	<script src="vendors/nice-select/js/jquery.nice-select.min.js"></script>
	<script src="vendors/isotope/imagesloaded.pkgd.min.js"></script>
	<script src="vendors/isotope/isotope-min.js"></script>
	<script src="vendors/owl-carousel/owl.carousel.min.js"></script>
	<script src="js/jquery.ajaxchimp.min.js"></script>
	<script src="vendors/jquery-ui/jquery-ui.js"></script>
	<script src="vendors/counter-up/jquery.waypoints.min.js"></script>
	<script src="vendors/counter-up/jquery.counterup.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
	<script src="js/theme.js"></script>
	<script src="js/index.js"></script>
	<script src="js/rating.js"></script>
	<script src="js/AddToCart.js"></script>
	<script src="js/UpdateQty.js"></script>
</body>

</html>