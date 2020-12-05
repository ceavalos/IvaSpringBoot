package innotech.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import innotech.com.dao.IComprasDao;
import innotech.com.entididades.Compra;
import innotech.com.entididades.Declaracion;

@Service
public class CompraServiceImp implements CompraService{

	
	@Autowired
	private IComprasDao compraServiceImp;
	
	@Override
	@Transactional(readOnly = true)
	public List<Compra> findAll() {
		return (List<Compra>) compraServiceImp.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Compra> findAll(Pageable pageable) {
		return compraServiceImp.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Compra compra) {
		compraServiceImp.save(compra);		
	}

	@Override
	@Transactional(readOnly = true)
	public Compra findOne(Long id) {
		return compraServiceImp.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		compraServiceImp.deleteById(id);
		
	}

	@Override
	public List<Compra> findByDeclaracion(Long declaracion) {
		return compraServiceImp.findByDeclaracion(declaracion);
	}

	

	



}
