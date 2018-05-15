package br.com.ads.springmvc.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ads.springmvc.models.TipoEquipamento;

public interface RepositorioTipoEquipamento extends JpaRepository<TipoEquipamento, Long>{

}
