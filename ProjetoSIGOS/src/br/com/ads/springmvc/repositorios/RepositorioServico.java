package br.com.ads.springmvc.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ads.springmvc.models.Servico;

public interface RepositorioServico extends JpaRepository<Servico, Long>{

}
