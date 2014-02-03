
function searchResult() {
	document.forms['search'].submit(); 
	return false;
}

$(document).ready(function() {
	$('#searchLink').bind('click', function(){
		searchResult();	
	});
	
	$('#searchInput').bind('keypress', function(event) {
		if (event.keyCode == 13) {
			searchResult();
		}
	});
	
	var resultSearchRowsNumber = document.getElementById("resultSearchTable").rows.length;
	
	if(resultSearchRowsNumber > 0)
	{
		$('#resultDisplay').css("background-color", "rgb(224, 224, 224)");
		$('#resultDisplay').css("width", $('#searchInput').width().toString());
		
		var eachRowHeight = document.getElementById("resultSearchTable").rows[0].offsetHeight + 3;
		$('#resultDisplay').css("height", (eachRowHeight * resultSearchRowsNumber));
	}
});