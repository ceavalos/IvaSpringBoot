package innotech.com.dao;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import innotech.com.entididades.Cliente;


public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>{

	public List<Cliente> findByNrc(String nrc);
	
}
