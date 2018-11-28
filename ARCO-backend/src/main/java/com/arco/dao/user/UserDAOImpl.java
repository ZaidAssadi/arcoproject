package com.arco.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.arco.dao.CustomHibernateDaoSupport;
import com.arco.model.ArcoUser;

@Repository
public class UserDAOImpl extends CustomHibernateDaoSupport implements UserDAO {


	public void createUser(ArcoUser arcoUser) {
		getHibernateTemplate().save(arcoUser);

	}



	@Override
	public List<ArcoUser> getUsers() {
		return (List<ArcoUser>)getHibernateTemplate().find("from ArcoUser");
	}



	@Override
	public ArcoUser getUserByUsername(String username) {
		List <ArcoUser> arcoUsers = (List<ArcoUser>)getHibernateTemplate().find("from ArcoUser u where u.username=?",username);

		if(arcoUsers != null && arcoUsers.size()>0) {
			return arcoUsers.get(0);
		}
		return null;
	}

}
