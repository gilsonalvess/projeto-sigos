
/*********************************************** Operações novo orçamento ******************************************************/

$(document).ready(function(){	
	//Adiciona Itens de servico no novo orçamento
    $("#select-item-novo-orc").on("change", function() {  
    	var idOs = $('#idOs').val();
    	var idServico = $("#select-item-novo-orc option:selected").val();  
    	$.ajax({
			method: 'GET',
				url: 'http://localhost:8080/ProjetoSIGOS/orcamento/novo_orcamento/'+ idOs +'?servicoItem=' + idServico,
				success: function(data){
					window.location.reload(true);
				},
				error: function(){
					alert("Houve um erro na requisição!");
				}
		});    	
    });	
    
 //Remove itens de serviço do novo orçamento
$(document).ready(function(){	    
    $(document).on("click", "#remove-item-novo-orc", function() {  
    	var idOs = $('#idOs').val();
    	var idServ =  $(this).closest('tr').find('.numero-serv').html();
    	//alert("Servico="+idServico);
    	$.ajax({
			method: 'GET',	
			url: 'http://localhost:8080/ProjetoSIGOS/orcamento/novo_orcamento/'+ idOs +'?removeItem=' + idServ,
				success: function(data){
					window.location.reload(true);
				},
				error: function(){
					alert("Houve um erro na requisição!");
				}
		});  	
    });	
  });
});


/*********************************************** Operações alterar orçamento ******************************************************/

$(document).ready(function(){	
	//Adiciona Itens de servico no orçamento
    $("#select-item-orc").on("change", function() {  
    	var idOrc = $('#id-orc').val();
    	var idServico = $("#select-item-orc option:selected").val();  
    	$.ajax({
			method: 'GET',
				url: 'http://localhost:8080/ProjetoSIGOS/orcamento/alterar/'+ idOrc +'?servicoItem=' + idServico,
				success: function(data){
					window.location.reload(true);
				},
				error: function(){
					alert("Houve um erro na requisição!");
				}
		});    	
    });	
    
 //Remove itens de serviço do orçamento
$(document).ready(function(){	    
    $(document).on("click", "#remove-item-orc", function() {  
    	var idOrc = $('#id-orc').val();
    	var idServ =  $(this).closest('tr').find('.numero-serv').html();
    	$.ajax({
			method: 'GET',	
			url: 'http://localhost:8080/ProjetoSIGOS/orcamento/alterar/'+ idOrc +'?removeItem=' + idServ,
				success: function(data){
					window.location.reload(true);
				},
				error: function(){
					alert("Houve um erro na requisição!");
				}
		});  	
    });	
  });

/*********************************************** Operação aprovar orçamento ******************************************************/
$(document).ready(function(){	    
    $(document).on("click", "#btn-aprovar", function() {  
    	var id = $('#id-orc').val();
    	//var idServ =  $(this).closest('tr').find('.numero-serv').html();
    	$.ajax({
			method: 'GET',	
			url: 'http://localhost:8080/ProjetoSIGOS/orcamento/aprovar/'+id,
				success: function(data){
					//window.location.reload(true);
					document.getElementById('id-alert-aprovado').style.display='block';
					$(document).on("click", "#btn-ok", function() {
						window.location.assign("http://localhost:8080/ProjetoSIGOS/orcamento/lista_orcamento");
					});					
				},
				error: function(){
					alert("Houve um erro na requisição!");
				}
		});  	
    });	
  });

/******************************************************** Enviar orçamento ******************************************************/
$(document).ready(function(){	    
    $(document).on("click", "#btn-enviar", function() {  
    	var id = $('#id-orc').val();   	
    	$('#id-modal-enviando').show();    	  	
    	$.ajax({    		
			method: 'GET',	
			url: 'http://localhost:8080/ProjetoSIGOS/orcamento/envia_orcamento/'+id,
				success: function(data){
					document.getElementById('id-modal-enviando').style.display='none';
					window.location.reload(true);
				},
				error: function(){
					document.getElementById('id-modal-enviando').style.display='none';
					alert("Erro ao enviar! Verifique sua conexão");
				}
		});  	
    });	
  });

});