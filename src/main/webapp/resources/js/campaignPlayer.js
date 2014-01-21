$(document).ready(function() {
			$('#d20Button').bind('click', function(){
				var random;
				var result = 'Roll: ';
				
				var rolls = $('#d20').val();
				
				for(var i = 0; i < rolls; i++)
				{
					random = Math.random() * 1000;
					result.concat(i + ' - ' + parseInt((random % 20) + 1) + '; ');
				}
				
				alert(result);
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