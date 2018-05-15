package br.com.ads.springmvc.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ads.springmvc.models.Cliente;
import br.com.ads.springmvc.models.Equipamento;

public interface RepositorioEquipamento extends JpaRepository<Equipamento, Long>{
	
	@Query("SELECT a.cliente FROM Equipamento a WHERE a.cliente.razaoSocial LIKE %:nome%")
	List<Cliente> findByNome(@Param("nome") String nome);
	
	@Query("SELECT a FROM Equipamento a WHERE a.cliente.id = :id")
	List<Equipamento> findByIdEquip(@Param("id") Long id);
	
	@Query("SELECT a FROM Equipamento a WHERE a.numeroSerie = :numSerie")
	List <Equipamento> findByNumSerie(@Param("numSerie") String numSerie);

}
