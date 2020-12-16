package innotech.com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import innotech.com.entididades.Role;
 

 
 
public interface RoleRepository extends CrudRepository<Role, Integer> {
     
}