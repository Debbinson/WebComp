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
                            <h4>${LOGINED_USER.username}</h4>
                            <p>User/Artist</p>
                            <div class="social_icon">
                                <a href="https://it-it.facebook.com/">
                                    <i class="fa fa-facebook"></i>
                                </a>
                                <a href="https://twitter.com/login?lang=it">
                                    <i class="fa fa-twitter"></i>
                                </a>
                                <a href="https://github.com/">
                                    <i class="fa fa-github"></i>
                                </a>
                                <a href="https://www.instagram.com/?hl=it">
                                    <i class="fa fa-instagram"></i>
                                </a>
                            </div>
                            <p>Every registered user can download and upload songs, try to make your own song
                            and post it here. With the help of our community you could become a real Rockstar!
                            </p>
                            <div class="br"></div>
                        </aside>                            
                   		 </div>
                	</div>
                	<div class="col-lg-8 posts-list">
			<section class="product_description_area">			

				<ul class="nav nav-tabs" id="myTab" role="tablist">
					<li class="nav-item">
						<a class="nav-link show active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Profile</a>
					</li>					<li class="nav-item">

						<a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Download</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Upload</a>
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
		        	
					<div class="col-md-3">
						<div class="billing-details">
							<div class="section-title">
								<h3 class="title">BIO</h3>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="username"
									placeholder="Username" value="${LOGINED_USER.username}"
									required>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="user-name"
									placeholder="Name" value="${LOGINED_USER.nome}" required>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="user-last-name"
									placeholder="Address" value="${LOGINED_USER.cognome}" required>
							</div>
							<div class="form-group">
								<input class="input" type="email" name="user-email"
									placeholder="Email" value="${LOGINED_USER.email}" required>
							</div>
							<div class="form-group">
								<a class="btn submit_btn" name="update-user">Modify BIO</a>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="billing-details">
							<div class="section-title">
								<h3 class="title">Billing Details</h3>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="first-name"
									placeholder="First-name" value="${INDIRIZZO_FATTURAZIONE.nome}"
									required>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="last-name"
									placeholder="Last-name" value="${INDIRIZZO_FATTURAZIONE.cognome}"
									required>
							</div>
							<div class="form-group">
								<input class="input" type="email" name="email"
									placeholder="Email" value="${INDIRIZZO_FATTURAZIONE.email}" required>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="address"
									placeholder="Address" value="${INDIRIZZO_FATTURAZIONE.indirizzo}"
									required>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="city" placeholder="City"
									value="${INDIRIZZO_FATTURAZIONE.citta}" required>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="country"
									placeholder="Country" value="${INDIRIZZO_FATTURAZIONE.nazione}"
									required>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="zip-code"
									placeholder="ZIP Code" value="${INDIRIZZO_FATTURAZIONE.cap}" required>
							</div>
							<div class="form-group">
								<input class="input" type="tel" name="tel"
									placeholder="Telephone" value="${INDIRIZZO_FATTURAZIONE.telefono}"
									required>
							</div>
							<div class="form-group">
								<input class="input" type="hidden"
									name="indirizzo-fatturazione-utente"
									value="${INDIRIZZO_FATTURAZIONE.indirizzoFatturazione_Utente}">
							</div>
							<div class="form-group">
								<a class="btn submit_btn" name="update-billing-address">Modify
									Billing Address</a>
							</div>
						</div>
					</div>
				</div>
				</div>
				<div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
					<div class="row">
					<div class="col-md-12">
						<div align="center">
							<h2>Your download</h2>
							<table id="acquisto-table">
								<tr>
									<th>Song</th>
									<th>Date</th>
									<th>Download</th>
								</tr>
							</table>
						</div>

					</div>
				</div>
				</div>
				<div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
					<div class="row" id="upload_section">
					<div class="col-md-3">
						<div align="center">
							<br>
			
							<h2>Your Upload</h2>
							<table class= "blog_right_sidebar" id="your-upload-table">
								<tr>
									<th>Song</th>
									<th>Date</th>
									<th>Remove</th>
								</tr>
							</table>
						</div>
					</div>
					
					<div class="col-md-9">
								<br>
								
								<h2>Upload Now</h2>
								<!--Form Upload-->
								<div id="controls">
									<form id="form-upload" class="form-inline" enctype="multipart/form-data">
										<div class="form-group">
											<label class="btn submit_btn" for="choose-btn">Choose file</label> 
											<input id="choose-btn" style="display: none" type="file" multiple onchange="show_table()" accept="audio/*">
											<a class="btn submit_btn" id="upload-btn">Upload</a>
										</div>
										<br><br>
										
									</form>
						</div>
					</div>
					<form id="form-upload" class="form-inline" enctype="multipart/form-data">
					<table id="upload-table" class= "blog_right_sidebar upload_table_class">
								<tr>
									<th>File</th>
									<th>Title</th>
									<th>Genre</th>
									<th>Description</th>
									<th>Price</th>
								</tr>
					</table>
					</form>
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
	<script src="js/UploadHandler.js"></script>
	<script src="js/UpdateUser.js"></script>
	<script src="js/UpdateQty.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
   
</body>

</html>