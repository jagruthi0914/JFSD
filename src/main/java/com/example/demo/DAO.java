package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
@Component
public class DAO {
	@Autowired
	UserInterface repo;
	
	public void insert(User user) {
		repo.save(user);
	}
	
	public List<User> retrieveAll(){
		return repo.findAll();
	}
	
	public User findUser(String email) {
		return repo.findByEmail(email);
	}
	
	public String deleteUser(String email) {
		repo.delete(findUser(email));
		return "Deleted " + email;
	}
	
	public String updateUser(User user) {
		deleteUser(user.getEmail());
		insert(user);
		return "updated";
	}
	
	public List<User> page(int page,int limit){
		System.out.println("dao");
		Sort sort = Sort.by(Sort.Direction.ASC, "name");
		Pageable pageable = PageRequest.of(page, limit, sort);
	    return repo.findAll(pageable).toList();
	}
	
}

