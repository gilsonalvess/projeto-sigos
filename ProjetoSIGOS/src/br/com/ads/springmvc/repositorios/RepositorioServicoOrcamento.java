package br.com.ads.springmvc.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.ads.springmvc.models.ServicoOrcamento;

public interface RepositorioServicoOrcamento extends JpaRepository<ServicoOrcamento, Long>{
	
	@Transactional
	@Modifying
	@Query("DELETE FROM ServicoOrcamento WHERE orcamento.id = :id")
	 void deleteItensOrc (@Param("id") Long id);
}
	