package innotech.com.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import innotech.com.entididades.Empresa;


public interface IEmpresaService {

	public List<Empresa> findAll();

	public Page<Empresa> findAll(Pageable pageable);

	public Empresa findById(Long id);

	public Empresa save(Empresa empresa);

	public void delete(Long id);
}
