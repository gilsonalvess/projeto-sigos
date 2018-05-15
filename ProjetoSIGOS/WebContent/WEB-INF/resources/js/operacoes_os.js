$(document).ready(function(){

	var matTec = $("#matTec").text();	

	$("#btn-iniciar").show();
	
	if(matTec == ""){
		$("#divTecnico").hide();	
	}
	else{
		$("#divTecnico").show();	
		$("#btn-iniciar").hide();
	}
	
	$("#btn-alterar-equip").click(function() {
		document.getElementById('id-modal-equip').style.display='block';		
	});
	
	$("#btn-iniciar").click(function() {
		document.getElementById('id-modal-tecnico').style.display='block';		
	});
	
	$("#btn-alterar-tec").click(function() {
		document.getElementById('id-modal-tecnico').style.display='block';		
	});
	
	//Ações da tela de listar_os
	
$(document).ready(function(){
		$('table tr').each(function(){
			var statusText = $(this).find('#status-os').text();
			statusColor = $(this).find(".w3-khaki");
			
			if(statusText == "Não iniciada"){
			    //statusColor.removeClass("w3-khaki");
			    statusColor.addClass("w3-red");
			} 
			if(statusText == "Orçamento"){
				statusColor.removeClass("w3-khaki");
			    statusColor.addClass("w3-blue");
			} 
			if(statusText == "Serviço em execução"){
				statusColor.removeClass("w3-khaki");
			    statusColor.addClass("w3-teal");			    
			}
			if(statusText == "Finalizada"){
				statusColor.removeClass("w3-khaki");
			    statusColor.addClass("w3-green");			    
			} 
			
		 });
		 
		function myFunction(id) {
		    var x = document.getElementById(id);
		    if (x.className.indexOf("w3-show") == -1) {
		        x.className += " w3-show";
		    } else { 
		        x.className = x.className.replace(" w3-show", "");
		    }
		}
		
		//Opções button orcamento
		$(document).ready(function(){
			var status = $('#status-os').text();
			var id_os = $('#id_os').val(); 
			if(status == "Orçamento" || status == "Serviço em execução"){	
				$('#btn-orcamento').show();
				$('#btn-finalizar-os').show();
				$('#btn-orcamento').text("Editar orçamento");
				$('#btn-orcamento').attr("href", "/ProjetoSIGOS/orcamento/alterar/"+id_os);
			} 			
			if(status == "Não iniciada" || status == "Finalizada"){
				$('#btn-orcamento').hide();
				$("#btn-alterar-equip").hide();
				$("#btn-alterar-tec").hide();
			}
		})
		
	//Ajax consulta ações O.S.
		$(document).on("click", "#btn-info-os", function(e){
			e.preventDefault;
			var id_os =  $(this).closest('tr').find('.numero-os').html();			
			$.ajax({
				method: 'GET',
					url: 'http://localhost:8080/ProjetoSIGOS/ordem_de_servico/informacoesOS?id=' + id_os,
					success: function(data){
						$('#tbl-info-os tbody > tr').remove();						
						/*$('#title').text("Ordem de Serviço Nº: " + data[0].ordemDeServico.id);
						$('#data-abertura').text("Criada em: "+ data[0].ordemDeServico.dataAbertura);
						$('#id-nome-cliente').html("<strong>Cliente: </strong>" + data[0].ordemDeServico.equipamento.cliente.razaoSocial);
						$('#id-nome-tecnico').html("<strong>Tecnico do Serviço: </strong>" + data[0].tecnico.nome);
						$('#id-ns-equip').html("<strong>Nº de Série do Equipamento: </strong>" + data[0].ordemDeServico.equipamento.numeroSerie);*/
						$.each(data, function(index, acaoTecnica){
							$('#tbl-info-os tbody').append(
								'<tr>' + 
									'<td>' + acaoTecnica.descricao + '</td>' +
									'<td>' + acaoTecnica.dataAcao + '</td>' +
								'</tr>'										
							);
						});					
					},
					error: function(){
						alert("Houve um erro na requisição!");
					}
			});
		});	
	});

/*********************************************** Operação finalizar OS ******************************************************/
$(document).ready(function(){	    
    $(document).on("click", "#btn-finalizar-os", function() {  
    	var id = $('#id_os').val();
    	$.ajax({
			method: 'GET',	
			url: 'http://localhost:8080/ProjetoSIGOS/ordem_de_servico/finalizar/'+id,
				success: function(data){
					//window.location.reload(true);
					//document.getElementById('id-alert-aprovado').style.display='block';
					//$(document).on("click", "#btn-ok", function() {
						window.location.assign("http://localhost:8080/ProjetoSIGOS/ordem_de_servico/lista_os");
					//});					
				},
				error: function(){
					alert("Houve um erro na requisição!");
				}
		});  	
    });	
});		
//modal	
	var id_os = $('#num_os').val();
	var sucesso = $('#sucesso').val();
	if(sucesso == "sucesso"){
		//window.location.reload(true);
		document.getElementById('id-alert-termo').style.display='block';
		$(document).on("click", "#btn-ok-os", function() {
			window.location.assign("http://localhost:8080/ProjetoSIGOS/ordem_de_servico/lista_os");
		});
	}						
});