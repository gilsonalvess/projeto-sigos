package br.com.ads.springmvc.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ads.springmvc.models.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario, Long>{
	
	Usuario findByUsername (String username);

}
