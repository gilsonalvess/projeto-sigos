package br.com.ads.springmvc.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ads.springmvc.models.ModeloEquipamento;

public interface RepositorioModeloEquipamento extends JpaRepository<ModeloEquipamento, Long>{
	

}
