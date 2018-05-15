
//Controla a paginação da página "Lista de OS"
$(document).ready(function(){
	var table = '#table-os'
	$('#maxRows').on('change', function(){
		$('.pagination-os').html('')
		var trnum = 0
		var maxRows = parseInt($(this).val())
		var totalRows = $(table+' tbody tr').length
		$(table+' tr:gt(0)').each(function(){
			trnum++
		    if (trnum > maxRows) {
		        $(this).hide()
		     }
		     if(trnum <= maxRows){
		        $(this).show()
		     }
		});
		if (totalRows > maxRows) {
			var pagenum = Math.ceil(totalRows/maxRows)
		    	for(var i=1; i<=pagenum;){
		    		$('.pagination-os').append('<a page-data="'+i+'" class="w3-button">'+ i++ +'</a>').show()
		    	}
		}
		$('.pagination-os a:first-child').addClass('w3-gray')
		$('.pagination-os a').on('click', function(){
			var pageNum = $(this).attr('page-data')
		    var trIndex = 0;
		    $('.pagination-os a').removeClass('w3-gray')
		    $(this).addClass('w3-gray')
		    $(table+' tr:gt(0)').each(function(){
		    	trIndex++
		        if(trIndex > (maxRows*pageNum) || trIndex <= ((maxRows*pageNum)-maxRows)){
		            $(this).hide()
		         }
		         else{
		        	 $(this).show()
		         }
		    });
		})
	})
})