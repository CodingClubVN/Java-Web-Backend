package com.se.codingclub.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.se.codingclub.dao.UserDAO;
import com.se.codingclub.entity.User;

@Repository
public class UserDAOImpl implements UserDAO{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> getListUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		User user = new User();
		Session session = sessionFactory.getCurrentSession();
		user = session.find(User.class, id);
		return user;
	}

	@Override
	@Transactional
	public boolean createUser(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.persist(user);
		return true;
	}
	@Override
	@Transactional
	public boolean updatePassword(int id, String password) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.find(User.class, id);
		user.setPassword(password);
		session.merge(user);
		return true;
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		String sql = "select * from users where userName ='"+username+"'";
		Session session = sessionFactory.openSession();
		users = session.createNativeQuery(sql, User.class).getResultList();
		for(User item : users) {
			System.out.println(item.toString());
		}
		if(users.size()!=0) {
			return users.get(0);
		}else {
			return null;
		}
	}

	@Override
	@Transactional
	public User updateUser(int id,User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		User userOld = session.find(User.class, id);
		userOld.setFirstName(user.getFirstName());
		userOld.setLastName(user.getLastName());
		userOld.setTelephone(user.getTelephone());
		userOld.setAddress(user.getAddress());
		session.merge(userOld);
		return userOld;
	}

}
