package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Aentity;
import com.example.demo.repository.Arepository;

@Service
public class Userservice {
	
	@Autowired
	private Arepository arepo;
	
	public String getusername(long uid) {
		Optional<Aentity> user=arepo.findByUserid(uid);
		return user.get().getUsername();
	}

}
