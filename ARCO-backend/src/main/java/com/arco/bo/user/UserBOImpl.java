package com.arco.bo.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arco.dao.user.UserDAO;
import com.arco.exception.ArcoBusinessException;
import com.arco.model.ArcoUser;

@Service
public class UserBOImpl implements UserBO{
	
	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional(readOnly=false,rollbackFor={ArcoBusinessException.class})
	public ArcoUser createUser(ArcoUser arcoUser) throws ArcoBusinessException {
		try {
			userDAO.createUser(arcoUser);
			return arcoUser;
		}catch(Exception e) {
			e.printStackTrace();
			throw new ArcoBusinessException("User could not be created");
		}
	}

	@Override
	public List<ArcoUser> getUsers() {
		return userDAO.getUsers();
	}

	@Override
	@Transactional(readOnly=true)
	public ArcoUser login(String username, String password){
		ArcoUser user = userDAO.getUserByUsername(username);
		if(user == null) {
			return null;
		}else {
			if(user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
		
	}
	
	

}
