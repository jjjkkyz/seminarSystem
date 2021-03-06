var $ = jQuery.noConflict(); 
var formSubmitted = 'false';

jQuery(document).ready(function($) {	

	$('#formSuccessMessageWrap').hide(0);
	$('.formValidationError').fadeOut(0);
	
	// fields focus function starts
	$('input[type="text"], input[type="password"], textarea').focus(function(){
		if($(this).val() == $(this).attr('data-dummy')){
			$(this).val('');
		};	
	});
	// fields focus function ends
		
	// fields blur function starts
	$('input, textarea').blur(function(){
    	if($(this).val() == ''){		    
			$(this).val($(this).attr('data-dummy'));				
		};			
	});
	// fields blur function ends
		
	// submit form data starts	   
    function submitData(currentForm, formType){     
		formSubmitted = 'true';		
		var formInput = $('#' + currentForm).serialize();		
		// $.post($('#' + currentForm).attr('action'),formInput, function(data){			
		// 	$('#' + currentForm).hide();
		// 	$('#formSuccessMessageWrap').fadeIn(500);			
		// });
		window.location.href='homepage.html';
	};
	// submit form data function starts	
	// validate form function starts
	function validateForm(currentForm, formType){		
		// hide any error messages starts
	    $('.formValidationError').hide();
		$('.fieldHasError').removeClass('fieldHasError');
		var count=4;
	    // hide any error messages ends	
		$('#' + currentForm + ' .requiredField').each(function(i){		   	 
			if($(this).val() == '' || $(this).val() == $(this).attr('data-dummy')){				
				$(this).val($(this).attr('data-dummy'));	
				$(this).focus();
				$(this).addClass('fieldHasError');
				$('#' + $(this).attr('id') + 'Error').fadeIn(300);
				count=count-1;
				return false;					   
			};				
					

   		});	
   		if($('#contactNameField').val()!=$('#contactEmailField').val())
   		{
   			$('#contactEmailField').focus();
			$('#contactEmailField').addClass('fieldHasError');
			$('#differentError').fadeIn(300);
			count=count-1;
   		}
   		if(formSubmitted == 'false' && count==4){
			 	submitData(currentForm, formType);
			};
  
	};
	// validate form function ends	
	
	// contact button function starts
	$('#contactSubmitButton').click(function() {	
		var flag2=validateForm($(this).attr('data-formId'));	
	    return false;		
	});
	// contact button function ends
	
	
	
});
/*////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
/*//////////////////// Document Ready Function Ends                                                                       */
/*////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
