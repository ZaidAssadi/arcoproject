package com.arco.dao.user;

import java.util.List;

import com.arco.model.ArcoUser;

public interface UserDAO {
	
	void createUser(ArcoUser arcoUser);
	
	List<ArcoUser> getUsers();

	ArcoUser getUserByUsername(String username); 
	
	
}
