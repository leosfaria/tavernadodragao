function generateRandomNumber(dice) {
	var random;
	var result = ' d' + dice + ' roll: ';
	
	var rolls = $('#d' + dice).val();
	
	for(var i = 0; i < rolls; i++)
	{
		random = Math.random() * 1000;
		var number = i + 1;
		result = result.concat( parseInt((random % dice) + 1) + '; ');
	}
	
	return result;
}

$(document).ready(function() {
			$('#d20Button').bind('click', function(){
				$('#message').val(generateRandomNumber(20));

				var e = jQuery.Event("keypress");
				e.which = 13;
				e.keyCode = 13;
				$('#message').trigger(e);
			});
			$('#d12Button').bind('click', function(){
				alert(generateRandomNumber(12));
			});
			$('#d10Button').bind('click', function(){
				alert(generateRandomNumber(10));
			});
			$('#d8Button').bind('click', function(){
				alert(generateRandomNumber(8));
			});
			$('#d6Button').bind('click', function(){
				alert(generateRandomNumber(6));
			});
			$('#d4Button').bind('click', function(){
				alert(generateRandomNumber(4));
			});
			
			$('#message').bind('keypress', function(event) {
				if (event.keyCode == 13) {
					$.ajax({
						type:'POST',
						data: 'message=' + $('#message').val() + '&campaignId=' + $('#campaignId').val(),
						url: 'http://172.20.22.65:8081/chatCampaign',
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
					data: 'campaignId=' + $('#campaignId').val(),
					url: 'http://172.20.22.65:8081/chatCampaign',
					success: function(response) {
						var message = eval( "(" + response + ")");
						
						message.forEach(function (msg) {
							$('#chatView').append( msg.user + ":" + msg.msg + "<br>");
						});
					},
					error:function(request,status,errorThrown){
					}
				});
			}, 2000);
		});