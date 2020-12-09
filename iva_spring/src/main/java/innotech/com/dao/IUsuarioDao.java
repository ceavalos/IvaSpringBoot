package innotech.com.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import innotech.com.entididades.Usuarios;

public interface IUsuarioDao extends CrudRepository<Usuarios, Long> {

	public Usuarios findByUsername(String username);
	
}
