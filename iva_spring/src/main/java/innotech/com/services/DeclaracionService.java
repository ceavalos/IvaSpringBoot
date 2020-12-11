package innotech.com.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import innotech.com.entididades.Declaracion;
import innotech.com.entididades.Empresa;

public interface DeclaracionService {

    public List<Declaracion> findAll();
	
	public Page<Declaracion> findAll(Pageable pageable);

	public void save(Declaracion declaracion);
	
	public Declaracion findOne(Long id);
	
	public void delete(Long id);
	
	public List<Declaracion> findEmpresa(Empresa empresa);
	
}
