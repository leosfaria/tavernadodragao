$(document).ready(function() {
			$('#d20Button').bind('click', function(){
				var random = Math.random() * 1000;
				$('#d20').val();
				
				alert(parseInt((random % 20) + 1));
			});
			$('#d12Button').bind('click', function(){
				alert('d12');
			});
			$('#d10Button').bind('click', function(){
				alert('d10');
			});
			$('#d8Button').bind('click', function(){
				alert('d8');
			});
			$('#d6Button').bind('click', function(){
				alert('d6');
			});
			$('#d4Button').bind('click', function(){
				alert('d4');
			});
		});