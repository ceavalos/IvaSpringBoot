package innotech.com.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import innotech.com.dao.DeclaracionDao;
import innotech.com.entididades.Declaracion;

@Service
public class DeclaracionServiceImp implements DeclaracionService {

	@Autowired
	DeclaracionDao declaracionDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Declaracion> findAll() {		
		return (List<Declaracion>) declaracionDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Declaracion> findAll(Pageable pageable) {	
		return declaracionDao.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Declaracion declaracion) {
		declaracionDao.save(declaracion);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Declaracion findOne(Long id) {		
		return declaracionDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		declaracionDao.deleteById(id);		
	}

}
