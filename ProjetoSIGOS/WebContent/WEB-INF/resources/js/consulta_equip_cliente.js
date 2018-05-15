
   /* $(function() {
        $("#cliente").autocomplete({
            source: function(request, response) {
                $.ajax({
                  	url: "/ProjetoSIGOS/ordem_de_servico/autoCompCliente",
                    type: "GET",
                    data: { term: request.term },

                    dataType: "json",

                    success: function(data) {
                    	response($.map(data, function(item){
                    		//id = item.id; 
                    	    return {
                    	                label: item.nomeFantasia, 	
										value: item.id
                    	            };
                    	}));
                    }
               });              
            },
            select: function (e,  ui) {
            	id = ui.item.value;
            	buscaCliente(id)    
            	//$('#cliente').remove();
            	 e.preventDefault();
            	 $(this).val(ui.item.label = "");
                 $('#cliente').html(ui.item.value = "");
            }
        });
   }); */

$(document).ready(function(){
	$('#dadosOS').hide();
	$('#alerta').hide();
	$(document).on('click', "#pesquisarEquip", function() {		
		var numSerie = $("#inputBuscaEquip").val();	
		if(numSerie == ""){
			alert("Informe o número de série");
			return;
		}
		$.ajax({
			method: 'GET',
			url: 'http://localhost:8080/ProjetoSIGOS/ordem_de_servico/equipCliente?numSerie=' + numSerie, 
			success: function(data){
				if(data == ""){
					$('#dadosOS').hide();
					$('#btn-salva-os').prop("disabled",true);
					$('#alerta-nao-localizado').fadeIn("slow");
					$('#inputBuscaEquip').val("");
					$('#inputBuscaEquip').focus();
					$('#alerta-nao-localizado').fadeOut(3000);						
					return;
				}
				else{
					$.each(data, function(index, data){	
						if(data.manutencao == false){ // false é o valor correto
							$('#id_equipamento').val(data.id);
							$('#nomeCliente').html(data.cliente.nomeFantasia);
							$('#emailCliente').html(data.cliente.enderecoContato.email);
							$('#foneCliente').html(data.cliente.enderecoContato.fone);
							$("#inputBuscaEquip").val("");					
							$('#numeroSerie').html(data.numeroSerie);
							$('#modelo').html(data.tipoEquipamento.descricao + " - " + data.modeloEquipamento.descricao);
							$('#dadosOS').fadeIn("slow");	
							$('#btn-salva-os').prop("disabled",false);
						}else{
							$('#dadosOS').hide();
							$('#btn-salva-os').prop("disabled",true);
							$('#alerta-equip-os').fadeIn("slow");
							$('#inputBuscaEquip').val("");
							$('#inputBuscaEquip').focus();
							$('#alerta-equip-os').fadeOut(3000);
						}
					});						
				}								
			},
			error: function(){
				alert("Houve um erro na requisição!");
			}			
		});
	});
});

