package br.com.ads.springmvc.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.ads.springmvc.models.AcaoTecnica;
import br.com.ads.springmvc.models.Tecnico;

public interface RepositorioAcaoTecnica extends JpaRepository<AcaoTecnica, Long>{
	
	@Query("SELECT a.tecnico FROM AcaoTecnica a WHERE a.ordemDeServico.id = :id")
	Tecnico tecnicoPorIdOs(@Param("id") Long id);	
	
	@Query("SELECT a FROM AcaoTecnica a WHERE a.ordemDeServico.id = :id")
	List<AcaoTecnica> findAllByOS(@Param("id") Long id);
	
	@Transactional
	@Modifying
	@Query("UPDATE AcaoTecnica a SET a.tecnico = :tec WHERE a.ordemDeServico.id = :idOs")
	void updateTecnico(@Param("tec") Tecnico tec, @Param("idOs") Long idOs);
	
}
