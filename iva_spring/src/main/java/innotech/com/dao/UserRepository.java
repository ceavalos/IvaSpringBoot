package innotech.com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import innotech.com.entididades.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
 