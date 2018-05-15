package br.com.ads.springmvc.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ads.springmvc.models.Orcamento;

public interface RepositorioOrcamento extends JpaRepository<Orcamento, Long>{

}
