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
        <div class="top_menu row m0">
            <div class="container-fluid">
                <div class="float-right">
                    <ul class="right_side">
                        <li>
                            <a href="index">
                                Home
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Go to cart
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                About Us
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
           
                    </div>
                </div>
            </nav>
        </div>
    </header>
    <!--================Header Menu Area =================-->
			
		
    
    
    <section class="blog_area single-post-area p_120">
		<div class="container">
			<ul class="nav nav-tabs" id="myTab" role="tablist">
				<li class="nav-item">
					<a class="nav-link show active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Profile</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Download</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Upload</a>
				</li>
			</ul>
			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
					<div class="row">
					<div class="col-md-3">
					<div class="row">
		                <aside class="single_sidebar_widget author_widget">
							<img class="author_img rounded-circle" src="img/blog/author.png" alt="">
							<h4>${LOGINED_USER.username}</h4>
							<p>The new Rockstar</p>
						</aside>	
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
								<a class="btn primary-btn" name="update-user">Modify BIO</a>
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
								<a class="btn primary-btn" name="update-billing-address">Modify
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
					<div class="row">
					<div class="col-md-3">
						<div align="center">
							<br>
							<h2>Your Upload</h2>
							<table id="your-upload-table">
								<tr>
									<th>Song</th>
									<th>Date</th>
									<th>Remove</th>
								</tr>
							</table>
						</div>
					</div>
					<div class="col-md-9">
						<div align="center">
							<div align="center">
								<br>
								<h2>Upload Now</h2>
								<!--Form Upload-->
								<div id="controls">
									<form id="form-upload" class="form-inline" enctype="multipart/form-data">
										<div class="form-group">
											<label class="btn primary-btn" for="choose-btn">Choose file</label> 
											<input id="choose-btn" style="display: none" type="file" multiple onchange="show_table()" accept="audio/*">
											<a class="btn primary-btn" id="upload-btn">Upload</a>
										</div>
										<br><br>
										<table id="upload-table">
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
				</div>
				
			</div>
		</div>
	</section>

    



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
   
</body>

</html>