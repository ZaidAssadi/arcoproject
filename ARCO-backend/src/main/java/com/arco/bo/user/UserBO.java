package com.arco.bo.user;

import java.util.List;

import com.arco.exception.ArcoBusinessException;
import com.arco.model.ArcoUser;



public interface UserBO {
	
	ArcoUser createUser(ArcoUser arcoUser) throws ArcoBusinessException;
	
	List<ArcoUser> getUsers();

	ArcoUser login(String username, String password); 
	

}
