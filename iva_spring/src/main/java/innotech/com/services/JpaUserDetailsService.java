package innotech.com.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import innotech.com.dao.IUsuarioDao;
import innotech.com.entididades.Role;
import innotech.com.entididades.Usuarios;

@Service("jpaDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private IUsuarioDao  usuarioDao;
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (username == null) {
			logger.error(" No existe el usuario: ".concat(username));	
			throw new UsernameNotFoundException("Usuario No Existe "+username);
		}
		
		Usuarios usuario = usuarioDao.findByUsername(username);
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (Role role: usuario.getRoles()) {
			logger.info("Role: " .concat(role.getAuthority() ));
			authorities.add(new SimpleGrantedAuthority(role.getAuthority() ));
		}
		
		if (authorities.isEmpty()) {
			logger.error("Error en el Login, usuario: ".concat(username).concat(" no tiene roles asociados"));	
			throw new UsernameNotFoundException("Error en Login. Usuario:  "+username+ "  No tiene roles Asociados. ");
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

}
