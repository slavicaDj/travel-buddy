function callAPI(data) {
		
	if (data.status === 'success') {
		var city = document.getElementById('oglasForma:odrediste').value;
		
	    var xhttp = new XMLHttpRequest();
	    xhttp.open('GET', 'http://api.openweathermap.org/data/2.5/weather?&appid=7d3d7f48ded7a5dbb5b09587cdb25986&units=metric&q=' + city);
	    
	    xhttp.onreadystatechange = function() {
	    	if (xhttp.readyState == 4 && xhttp.status == 200){
	    		var response = JSON.parse(xhttp.responseText);
	    		var ispis = "Grad: " + response.name + "\n" + 
	    					"Opis uslova: " + response.weather["0"].description + "\n" +
	    					"Temperatura: " + response.main.temp + "\n" + 
	    					"Brzina vjetra: " + response.wind.speed + "km/h" + "\n" + 
	    					"Vlažnost vazduha: " + response.main.humidity + "%";
	    		alert(ispis);
	        }
	    }
	
	    xhttp.send();
	}
	return true;
}

