package innotech.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import innotech.com.dao.IventasDao;
import innotech.com.entididades.Venta;

@Service
public class VentaServiceImp  implements VentaService{
	
	@Autowired
	private IventasDao ventaServiceImp;
	
	@Override
	@Transactional(readOnly = true)
	public List<Venta> findAll() {
		return (List<Venta>) ventaServiceImp.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Venta> findAll(Pageable pageable) {
		return ventaServiceImp.findAll(pageable);
		
	}

	@Override
	@Transactional
	public void save(Venta venta) {
		// TODO Auto-generated method stub
		ventaServiceImp.save(venta);				
	}

	@Override
	@Transactional(readOnly = true)
	public Venta findOne(Long id) {
		return ventaServiceImp.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		ventaServiceImp.deleteById(id);
	}

	@Override
	@Transactional
	public List<Venta> findByDeclaracion(Long declaracion) {
		return ventaServiceImp.findByDeclaracion(declaracion);
	}

}
