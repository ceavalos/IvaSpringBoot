package innotech.com.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import innotech.com.entididades.Declaracion;
import innotech.com.entididades.Empresa;

public interface DeclaracionDao extends PagingAndSortingRepository<Declaracion, Long>{
	
	public List<Declaracion> findByEmpresa(Empresa empresa);
	
}
