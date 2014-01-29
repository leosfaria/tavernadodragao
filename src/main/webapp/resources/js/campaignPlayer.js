var colors = ["red", "green", "blue", "black", "yellow"];
var players = new Array();

function generateRandomNumber(dice) {
	var random;
	
	var rolls = $('#d' + dice).val();
	var result = rolls + 'd' + dice + ' roll: ';
	
	for(var i = 0; i < rolls; i++)
	{
		random = Math.random() * 1000;
		result = result.concat( parseInt((random % dice) + 1) + '; ');
	}
	
	return result;
}

function generateRandomNumberPlus(dice) {
	if ($('#d' + dice).val() == 1)
		return generateRandomNumber(dice);
	
	var random;
	
	var rolls = $('#d' + dice).val();
	var result = rolls + 'd' + dice + ' roll: ';
	var som = 0;
	
	for(var i = 0; i < rolls; i++)
	{
		random = Math.random() * 1000;
		
		som += parseInt((random % dice) + 1) ;
	}
	
	result = result.concat( som + ';');
	
	return result;
}

function submitResult(message) {
	$('#message').val(message);

	var e = jQuery.Event("keypress");
	e.which = 13;
	e.keyCode = 13;
	$('#message').trigger(e);
}

$(document).ready(function() {
			$('#d20Button').bind('click', function(){
				submitResult(generateRandomNumber(20));
			});
			$('#d20PlusButton').bind('click', function(){
				submitResult(generateRandomNumberPlus(20));
			});
			$('#d12Button').bind('click', function(){
				submitResult(generateRandomNumber(12));
			});
			$('#d12PlusButton').bind('click', function(){
				submitResult(generateRandomNumberPlus(12));
			});
			$('#d10Button').bind('click', function(){
				submitResult(generateRandomNumber(10));
			});
			$('#d10PlusButton').bind('click', function(){
				submitResult(generateRandomNumberPlus(10));
			});
			$('#d8Button').bind('click', function(){
				submitResult(generateRandomNumber(8));
			});
			$('#d8PlusButton').bind('click', function(){
				submitResult(generateRandomNumberPlus(8));
			});
			$('#d6Button').bind('click', function(){
				submitResult(generateRandomNumber(6));
			});
			$('#d6PlusButton').bind('click', function(){
				submitResult(generateRandomNumberPlus(6));
			});
			$('#d4Button').bind('click', function(){
				submitResult(generateRandomNumber(4));
			});
			$('#d4PlusButton').bind('click', function(){
				submitResult(generateRandomNumberPlus(4));
			});
			
			$('#message').bind('keypress', function(event) {
				if (event.keyCode == 13) {
					$.ajax({
						type:'POST',
						data: {message: $('#message').val(), campaignId: $('#campaignId').val()},
						url: 'http://tavernadodragao.com.br:8081/chatCampaign',
						success: function(response) {
							var message = eval( "(" + response + ")");
							$('#chatView').append( message[0].user + ":" + message[0].msg + "<br>");
						},
						error:function(request,status,errorThrown){
						}
					});
					
					$('#message').val("");
				}
			});
			
			window.setInterval(function(){
				$.ajax({
					type:'POST',
					data: {campaignId: $('#campaignId').val()},
					url: 'http://tavernadodragao.com.br:8081/chatCampaign',
					success: function(response) {
						var message = eval( "(" + response + ")");
						
						message.forEach(function (msg) {
							var playerIndex = players.indexOf(msg.user);
							
							if (playerIndex == -1) {
								players.push(msg.user);
								playerIndex = players.indexOf(msg.user);
							}
								
							if (playerIndex > 4)
								playerIndex = playerIndex % 5;
							
							$('#chatView').append("<font color=\"" + colors[playerIndex] + "\">" + msg.user + ":</font>" + msg.msg + "<br>");
						});
					},
					error:function(request,status,errorThrown){
					}
				});
			}, 2000);
		});