package com.example.demo.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Pentity;
import com.example.demo.repository.Prepository;

@Service

public class Pservice {
	
	@Autowired
	private Prepository prepo;
	
        @SuppressWarnings("CallToPrintStackTrace")
	public String addProduct(Pentity product) {
		try {
			prepo.save(product);
			return "product sucessfuly added";
		} catch (Exception e) {
			 e.printStackTrace();
		     return "An error occurred during adding product";
		}
		
	}
	
        @SuppressWarnings("CallToPrintStackTrace")
	public void updateProduct(long productid, Pentity product) {
    try {
        // Fetch the product from the database
        Pentity uproduct = prepo.findById(productid)
            .orElseThrow(() -> new RuntimeException("Product not found with id " + productid));

        // Manually copy properties from the input product to the fetched entity
        uproduct.setTitle(product.getTitle());
        uproduct.setBrand(product.getBrand());
        uproduct.setOriginalPrice(product.getOriginalPrice());
        uproduct.setDiscountedPrice(product.getDiscountedPrice());
        uproduct.setImageUrl(product.getImageUrl());
        uproduct.setCategory(product.getCategory());
        uproduct.setType(product.getType());
        uproduct.setDescription(product.getDescription());
        uproduct.setRating(product.getRating());

        // Only update sizes if provided in the input product
        if (product.getSizes() != null && !product.getSizes().isEmpty()) {
            uproduct.setSizes(product.getSizesList());  // Update sizes if provided
        }

        // Save the updated product back to the database
        prepo.save(uproduct);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

	
        @SuppressWarnings("CallToPrintStackTrace")
	public boolean deleteProduct(long productid) {
	    try {
	        Optional<Pentity> uproduct = prepo.findById(productid);
	        if (uproduct.isPresent()) {
	            prepo.delete(uproduct.get());
	            return true; // Deletion successful
	        } else {
	            return false; // Product not found
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("An error occurred while deleting the product.");
	    }
	}
	
        @SuppressWarnings("CallToPrintStackTrace")
	public Pentity getProduct(long productid) {
		 try {
			 @SuppressWarnings("deprecation")
			Pentity product=prepo.getOne(productid);
			 
		        return product;
		            
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		 return null;
	}


}
