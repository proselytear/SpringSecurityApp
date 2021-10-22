package net.proselyte.springsecurityapp.dao;

import net.proselyte.springsecurityapp.model.User;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findAll();
  

}
