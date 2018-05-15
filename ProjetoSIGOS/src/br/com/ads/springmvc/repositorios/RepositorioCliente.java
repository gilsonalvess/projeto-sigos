package br.com.ads.springmvc.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ads.springmvc.models.Cliente;

public interface RepositorioCliente extends JpaRepository<Cliente, Long> {
	
	@Query("SELECT a FROM Cliente a WHERE a.nomeFantasia LIKE %:nome%")
	List<Cliente> findByNome(@Param("nome") String nome);
	
	@Query("SELECT a FROM Cliente a WHERE a.id = :id")
	List<Cliente> findById(@Param("id") Long id);
	
}
