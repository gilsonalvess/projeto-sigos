package br.com.ads.springmvc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column(length = 10, nullable = false)
	@Size(min = 3, max = 10, message = "O nome de usuário deve conter entre 3 e 10 caracteres")
	@NotEmpty(message = "O nome de usuário é obrigatório!")
	@NotNull(message = "O nome de usuário é obrigatório!")
	private String username;
	
	@Column(length = 150, nullable = false)
	private String password;
	
	@Column(length = 20, nullable = false)
	private String role;
	
	@Column(name = "matricula_tecnico", length = 20, nullable = true)
	private String matriculaTecnico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMatriculaTecnico() {
		return matriculaTecnico;
	}

	public void setMatriculaTecnico(String matriculaTecnico) {
		this.matriculaTecnico = matriculaTecnico;
	}
	
}
