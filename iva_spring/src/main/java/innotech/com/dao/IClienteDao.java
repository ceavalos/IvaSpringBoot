package innotech.com.dao;


import org.springframework.data.repository.PagingAndSortingRepository;

import innotech.com.entididades.Cliente;


public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>{


}
