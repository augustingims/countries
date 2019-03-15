/**
 * 
 */
$(document).ready(function(){
	
	$('.nBtn, .table .eBtn').on('click', function(event){
		event.preventDefault();
		
		var href = $(this).attr('href');
		var text = $(this).text();
		
		if(text == 'Edit'){
			$.get(href,function(country,status){
				$('.myForm #id').val(country.id);
				$('.myForm #name').val(country.name);
				$('.myForm #capital').val(country.capital);
			});
			  
			$('.myForm #exampleModal').modal();
			
		}else {
			
			$('.myForm #id').val('');
			$('.myForm #name').val('');
			$('.myForm #capital').val('');
		
			$('.myForm #exampleModal').modal();
		}
		
	});
	
	$('.table .dBtn').on('click', function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$('#exampleModalCenter #delRef').attr('href',href);
		$('#exampleModalCenter').modal();
		
	});
	
});