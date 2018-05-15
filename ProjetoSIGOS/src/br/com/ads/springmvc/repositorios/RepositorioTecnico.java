package br.com.ads.springmvc.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ads.springmvc.models.Tecnico;

public interface RepositorioTecnico extends JpaRepository<Tecnico, Long>{
	
	@Query("SELECT a FROM Tecnico a WHERE a.matricula = :matriculaTec")
	Tecnico findByMatricula(@Param("matriculaTec") String matriculaTec);

}
