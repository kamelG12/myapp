function initComportementJs(){
	console.log("initComportementJs");
	
	//$('#btnEnregistrer').on('click',enregistrer);
	//$('#btnListeInscriptions').on('click',recupererListeInscriptions);
}

$( function() {
	// ici, le DOM de la page est entièrement défini et peut être manipulé via jQuery
	console.log("via jquery");
	$('#btnEnregistrer').on('click',enregistrer);
	$('#btnListeInscriptions').on('click',recupererListeInscriptions);
});



function recupererListeInscriptions(){
	$.ajax({
        type: "GET",
        url: "./services/rest/client",
		   success: function (data,status,xhr) {
			   console.log(JSON.stringify(data));
			   $("#spanTableau").html(JSON.stringify(data)); 
		   }   
		});
}

function enregistrer(){
		  var nom =  $('#nom').val();
		  var prenom = $("#prenom").val();  
		  
		  var client = {
			numClient : null,
			nom : nom ,
			prenom : prenom 
		  }; //expression "objet lit(t)eral javascript" (très proche du format JSON) .
		  
		  //on peut dynamiquement ajouter des propriétés supplémentaires sur l'objet client :
		  client.telephone = $("#telephone").val();
		  client.adresse = $("#adresse").val();
		  client.email = $("#email").val();
	      var clientAsJsonString = JSON.stringify(client);
	      console.log(clientAsJsonString);
	      
	      $.ajax({
	           type: "POST",
	           url: "./services/rest/client",
	           contentType : "application/json" ,
	           dataType: "json",
			   data: clientAsJsonString,
	           success: function (data) {
	        	   $("#spanRes").html(JSON.stringify(data)); 
	           },
	           error: function(xhr, status, error){
	        	   $("#spanRes").html( "status:" + status + " error : " + error ) ;
	           }
			  });
			
}