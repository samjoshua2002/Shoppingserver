package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Pentity;
import com.example.demo.repository.Prepository;
import com.example.demo.service.Pservice;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@CrossOrigin(origins="*")
@RequestMapping("/product")

public class Pcontroller {
	
	@Autowired
	private Pservice pservice;
	
	@Autowired
	private Prepository prepo;
	
	@PostMapping("/add")
	public String add(@RequestBody Pentity product) {
		pservice.addProduct(product);
		return "product added successfully";
	}
	
	@PutMapping("/update/{id}")
	public String update(@PathVariable long id, @RequestBody Pentity product) {
		pservice.updateProduct(id, product);
		return "product updated";
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
	    try {
	        boolean isDeleted = pservice.deleteProduct(id);
	        if (isDeleted) {
	            return "Product with ID " + id + " deleted successfully.";
	        } else {
	            return "Product with ID " + id + " not found.";
	        }
	    } catch (Exception e) {
	        return "An error occurred while deleting the product: " + e.getMessage();
	    }
	}

	@GetMapping("/get/{id}")
	public Pentity get(@PathVariable long id) {
		Optional<Pentity> product=prepo.findById(id);
		if(product.isPresent()) {
			
			Pentity gproduct= pservice.getProduct(id);
			return gproduct;
		}
		return null;
	}
	
	@GetMapping("/gettype/{type}")
	public List<Pentity> gettype(@PathVariable String type) {
	    return prepo.findByType(type); 
	}

	@GetMapping("/getall")
	public List<Pentity> getall(){
		
		return prepo.findAll();
	}
	



}
