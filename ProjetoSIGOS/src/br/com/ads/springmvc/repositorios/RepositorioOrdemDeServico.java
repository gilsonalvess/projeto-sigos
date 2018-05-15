package br.com.ads.springmvc.repositorios;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ads.springmvc.models.OrdemDeServico;
import br.com.ads.springmvc.models.Tecnico;

public interface RepositorioOrdemDeServico extends JpaRepository<OrdemDeServico, Long>{
	
	@Query("SELECT a.tecnico FROM AcaoTecnica a WHERE a.ordemDeServico.id = :id")
	Tecnico findByIdTec(@Param("id") Long id);
	
	@Query("SELECT a.ordemDeServico FROM AcaoTecnica a WHERE a.tecnico.id = :idTec")
	Set<OrdemDeServico> findAllOsTec(@Param("idTec") Long idTec);
}
