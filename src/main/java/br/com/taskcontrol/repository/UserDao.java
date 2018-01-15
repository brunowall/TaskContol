package br.com.taskcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.taskcontrol.models.User;

public interface UserDao extends JpaRepository<User,String>{
	

}
