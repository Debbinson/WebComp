<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">

<head>
    <!-- Required meta tags -->
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${LOGINED_USER.username}-Rockstar</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="vendors/linericon/style.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
    <link rel="stylesheet" href="vendors/lightbox/simpleLightbox.css">
    <link rel="stylesheet" href="vendors/nice-select/css/nice-select.css">
    <link rel="stylesheet" href="vendors/animate-css/animate.css">
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
			
		<section class="banner_area">
        <div class="banner_inner d-flex align-items-center">
            <div class="container">
                <div class="banner_content text-center">
                
                    <h2>Your Profile Page</h2>
                    
                    
                </div>
            </div>
        </div>
    </section>       	
			<div class="blog_right_sidebar container">
			<div class="col-lg-4 pull-right">
                    <div class="blog_right_sidebar">
                        <aside class="single_sidebar_widget search_widget">
                            <div class="br"></div>
                        </aside>
                        <aside class="single_sidebar_widget author_widget">
                            <img class="author_img rounded-circle" src="img/blog/author.png" alt="">
                            <h4>ADMIN</h4>
                            <p>Website Administrator</p>

                            <div class="br"></div>
                        </aside>                            
                   		 </div>
                	</div>
                	<div class="col-lg-8 posts-list">
			<section class="product_description_area">			

				<ul class="nav nav-tabs" id="myTab" role="tablist">
					<li class="nav-item">
						<a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Songs</a>
					</li>
				</ul>
			</section>
			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
					<div class="row">
					<div class="col-md-3">
					<div class="row">			
		          
		        	</div>
		        	</div>
		        	
					
				</div>
				</div>
				<div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
					<div class="row" id="upload_section">
					<div class="col-md-3">
						<div align="center">
							<br>
			
							<h2>Songs list</h2>
							<table class= "blog_right_sidebar" id="songs-table">
								<tr>
									<th>Song</th>
									<th>Date</th>
									<th>Remove</th>
								</tr>
							</table>
						</div>
					</div>
					
				</div>
				</div>
				
			</div>
		</div>
		</div>

    



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
    <script src="js/theme.js"></script>
    <script src="js/SuccessError_Popup.js"></script>
   	<script src="js/BraniHandler.js"></script>
   	<script src="js/admin.js"></script>
	<script src="js/UpdateQty.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
   
</body>

</html>