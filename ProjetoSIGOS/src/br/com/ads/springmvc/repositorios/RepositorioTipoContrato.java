package br.com.ads.springmvc.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ads.springmvc.models.TipoContrato;

public interface RepositorioTipoContrato extends JpaRepository<TipoContrato, Long>{

}
