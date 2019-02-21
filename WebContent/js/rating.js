function get_commenti(){
	$.ajax({
			url: "commenti",
			type: "GET",
			dataType: 'json',
			data: {"whatsend": "commento"},
			success: function(result,status,xhr) {
	           console.log("Entered", status);
	           updateCommento(JSON.parse(xhr.responseText));
	       },
	       error: function(error) {
	       	console.log("Error", error);
	       }
		});
}

$(document).ready(function() {
  	 get_commenti();
  	
      $("#add-comment").click(function () {
    	  
      	var commento= $("#message").val();
      	var rating = $("input[name='rating']:checked").val();
      	
      	var valutazione = {
      			rating : rating ,
      			commento : commento ,
      			Brano_idBrano : 0,
      			Utente_nomeUtente : ""
      	};
      	
      	$.ajax({
              type: "POST",
              url: "rating",
              dataType: 'json',
              data: {"valutazione": JSON.stringify(valutazione), "whatsend": "valuta"},
              success: function(status) {
            	success_error("Success!", "Comment inserted");
              	console.log("Entered: ", status);
              	get_commenti();
              },
              error: function(error) {
              	success_error("e", "Comment not inserted");
              	console.log("Error: ", error);
              	
              }
          });
      	
      	document.getElementById("message").value="";
      });
  });

  function updateCommento(valutazioni){
	
	var newComment="";
  	var comment_section = $("#comment-section");
  	
  	if(valutazioni.length == 0) {
  		console.log("empty")
  		newComment = "<div><h4>No comment found!</h4></div>";
  	} else {
	  	for(var i = 0; i < valutazioni.length; i++) {
		  	newComment += "<div class='review_item'>" +
		  					"<div class='media'>" +
		  					"<div class='d-flex'>" +
		  					"<img src='img/product/single-product/review-1.png' alt=''>" +
		  					"</div><div class='media-body'>" +
							
		  						"<div><a><i class='fa fa-user-o'><h4> " + valutazioni[i].Utente_nomeUtente  + "</h4></i></a></div>" +
		  						"<div><a href='#'><i class='fa fa-clock-o'></i>" + valutazioni[i].date_ins + "</a></div>";
		  					for(var j = 0; j < valutazioni[i].rating; j++)
		  						newComment += "<i class='fa fa-star'></i>";
		  					for(var j = 0; j< 5-valutazioni[i].rating; j++)
		  						newComment += "<i class='fa fa-star-o empty'></i>"
		  					newComment += "</div></div>";
		  					newComment += "<p>" + valutazioni[i].commento + "</p></div>";
		  									
	  	}
  	}
  	
  	
  	comment_section.html(newComment);
  	
    var votoComplessivo = 0;
    for (var i = 0; i < valutazioni.length; i++) {
  	  votoComplessivo+=valutazioni[i].rating;
    }
    var stella = 0;
    if (valutazioni.length != 0) {
    	stella = votoComplessivo / valutazioni.length;	
    }
    var star="";
    star+= "<h5>Overall</h5>";
    star+= "<h4>"+ stella +"</h4>";
    for(var i = 1;i < stella; i++){
  	  star+="<i class='fa fa-star'></i>";
    }
    for(var i = 0; i < 5-stella;i++){
  	  star+="<i class='fa fa-star-o empty'></i>";
    }
    
    star+= "<h6>" +valutazioni.length + " Reviews</h6>";
    
    document.getElementById("starsofsong").innerHTML=star;
    
    
    
    
 	var count5Stars=0;
    var count4Stars=0;
    var count3Stars=0;
    var count2Stars=0;
    var count1Stars=0;
    for (var i = 0; i < valutazioni.length; i++) {
	    if(valutazioni[i].rating == 5){
	    	count5Stars++;
	    }
	    if(valutazioni[i].rating == 4){
	    	count4Stars++;
	    }
	    if(valutazioni[i].rating == 3){
	    	count3Stars++;
	    }
	    if(valutazioni[i].rating == 2){
	    	count2Stars++;
	    }
	    if(valutazioni[i].rating == 1){
	    	count1Stars++;
	    }
    }
    
    var countToDisplay="";
    
    var star_section = $("#number_of_stars");
    countToDisplay+="<h3>Based on " + valutazioni.length + " Reviews</h3>";
    countToDisplay+="<ul class='list'><li><a>5 Stars";
    countToDisplay+="<i class='fa fa-star'></i>" +
    				"<i class='fa fa-star'></i>" +
    				"<i class='fa fa-star'></i>" +
    				"<i class='fa fa-star'></i>" +
    				"<i class='fa fa-star'></i>" + " " +count5Stars + "</a></li>";
    
    countToDisplay+="<li><a>4 Stars";
    countToDisplay+="<i class='fa fa-star'></i>" +
    				"<i class='fa fa-star'></i>" +
    				"<i class='fa fa-star'></i>" +
    				"<i class='fa fa-star'></i>" +
    				"<i class='fa fa-star-o empty'></i>" + " " + count4Stars + "</a></li>";
    
    countToDisplay+="<li><a>3 Stars";
    countToDisplay+="<i class='fa fa-star'></i>" +
    				"<i class='fa fa-star'></i>" +
    				"<i class='fa fa-star'></i>" +
    				"<i class='fa fa-star-o empty'></i>" +
    				"<i class='fa fa-star-o empty'></i>" + " " + count3Stars + "</a></li>";
    
    countToDisplay+="<li><a>2 Stars";
    countToDisplay+="<i class='fa fa-star'></i>" +
    				"<i class='fa fa-star'></i>" +
    				"<i class='fa fa-star-o empty'></i>" +
    				"<i class='fa fa-star-o empty'></i>" +
    				"<i class='fa fa-star-o empty'></i>" + " " + count2Stars + "</a></li>";
    
    countToDisplay+="<li><a>1 Stars";
    countToDisplay+="<i class='fa fa-star'></i>" +
    				"<i class='fa fa-star-o empty'></i>" +
    				"<i class='fa fa-star-o empty'></i>" +
    				"<i class='fa fa-star-o empty'></i>" +
    				"<i class='fa fa-star-o empty'></i>" + " " + count1Stars + "</a></li></ul>";
    
    
    star_section.html(countToDisplay);
    
   
    
 }