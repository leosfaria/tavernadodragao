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

//$(document).click(function() {
// 	if($('#addFriend').attr('type') == "text")
//	{
// 		$('#addFriend').attr('type', 'button');
// 		$('#addFriend').val("Add friend");
// 		
// 		$('#searchFriendView').html("");
// 		$('#searchFriendView').css("background-color", "transparent");
//		$('#searchFriendView').css("width", "1px");
//		$('#searchFriendView').css("height", "1px");
//	}
//});

$(document).ready(function() {		
			$('#addFriend').bind('click', function(event){
					$('#addFriend').attr('type', 'text');
					$('#addFriend').val("");
					
					event.stopPropagation();
			});
			$('#addFriend').bind('keypress', function(event) {
				if (event.keyCode == 13) {
					$.ajax({
						type:'POST',
						data: {user: $('#addFriend').val(), campaignId: $('#campaignId').val()},
						url: 'http://tavernadodragao.com.br:8081/searchFriendCampaign',
						success: function(response) {
							var message = eval( "(" + response + ")");
							var userCount = 0;
							
							if (message != ""){
								message.forEach(function (msg) { 
									$('#searchFriendView').append("<div>" +
											"<div id=\"searchFriendViewImg\" class=\"img\">" +
												"<img src=\"../resources/css/images/yourImageDefault.jpg\" height=\"20\" width=\"20\" border=\"1\">" +
											"</div>" +
											"<div id=\"searchFriendViewData\" class=\"friendData\">" +
												"<h3>" + msg.username + "</h3>" +
											"</div>" +
											"<div id=\"searchFriendViewButton\" class=\"friendSubmitButton\">" +
												"<form id=\"addFriendToCampaign\" action=\"addFriendToCampaign\" method=\"post\">" +
													"<input id=\"addFriendToCampaignButton\" type=\"submit\" value=\"Add\">" +
													"<input type=\"hidden\" id=\"friendIdAddFriend\" name=\"friendIdAddFriend\" value=\"" + msg.userId + "\" />" +
													"<input type=\"hidden\" id=\"campaignIdAddFriend\" name=\"campaignIdAddFriend\" value=\"" + msg.campaignId + "\" />" +
												"</form>" +
											"</div>" +
										"</div>");
									userCount++;
								});
								
								$('#searchFriendView').css("background-color", "rgb(224, 224, 224)");
								$('#searchFriendView').css("width", 230);
								$('#searchFriendView').css("height", userCount * 30);
							}
						},
						error:function(request,status,errorThrown){
						}
					});
					
					$('#addFriend').val("");
				}
			});
	
			$('#d20Button').bind('click', function(){
				$('#rollResult').html("Result:<br>" + generateRandomNumber(20));
			});
			$('#d20PlusButton').bind('click', function(){
				$('#rollResult').html("Result:<br>" + generateRandomNumberPlus(20));
			});
			$('#d12Button').bind('click', function(){
				$('#rollResult').html("Result:<br>" + generateRandomNumber(12));
			});
			$('#d12PlusButton').bind('click', function(){
				$('#rollResult').html("Result:<br>" + generateRandomNumberPlus(12));
			});
			$('#d10Button').bind('click', function(){
				$('#rollResult').html("Result:<br>" + generateRandomNumber(10));
			});
			$('#d10PlusButton').bind('click', function(){
				$('#rollResult').html("Result:<br>" + generateRandomNumberPlus(10));
			});
			$('#d8Button').bind('click', function(){
				$('#rollResult').html("Result:<br>" + generateRandomNumber(8));
			});
			$('#d8PlusButton').bind('click', function(){
				$('#rollResult').html("Result:<br>" + generateRandomNumberPlus(8));
			});
			$('#d6Button').bind('click', function(){
				$('#rollResult').html("Result:<br>" + generateRandomNumber(6));
			});
			$('#d6PlusButton').bind('click', function(){
				$('#rollResult').html("Result:<br>" + generateRandomNumberPlus(6));
			});
			$('#d4Button').bind('click', function(){
				$('#rollResult').html("Result:<br>" + generateRandomNumber(4));
			});
			$('#d4PlusButton').bind('click', function(){
				$('#rollResult').html("Result:<br>" + generateRandomNumberPlus(4));
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