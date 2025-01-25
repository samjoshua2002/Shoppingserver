package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Rdto;
import com.example.demo.entity.Aentity;
import com.example.demo.entity.Pentity;
import com.example.demo.entity.Rentity;
import com.example.demo.repository.Arepository;
import com.example.demo.repository.Prepository;
import com.example.demo.repository.Rrepository;
import com.example.demo.service.Userservice;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.234:3000","https://trendix-seven.vercel.app/"})
@RequestMapping("/review")
public class Rcontroller {
	
	@Autowired
	private Rrepository rrepo;

	@Autowired
	private Prepository prepo;

    @Autowired
    private Arepository arepo;
    
    @Autowired
    private Userservice uservice;
	
    @PostMapping("/addreview/{uid}/{pid}")
    public String addreview(@PathVariable long uid, @PathVariable long pid,@RequestBody Rentity rentity) {
    	 Optional<Aentity> user = arepo.findById(uid);
         Optional<Pentity> product = prepo.findById(pid);
         if (user.isPresent() && product.isPresent()) 
         {
         rentity.setAentity(user.get());
         rentity.setPentity(product.get());
         rrepo.save(rentity);
         return "added";
         }else {
        	 return "error"; 
         }
    }
    
    @DeleteMapping("/delreview/{rid}")
    public String delreview(@PathVariable long rid) {
    	Optional<Rentity> review = rrepo.findById(rid);
         if(review.isPresent()) {
        	 rrepo.delete(review.get());
         return "deleted";
         }else {
        	 return "error"; 
         }
    }
    
    @PutMapping("/changereview/{rid}")
    public String changereview(@PathVariable long rid,@RequestBody Rentity rentity) {
    	 Optional<Rentity> review = rrepo.findById(rid);
         if (review.isPresent()) 
         {
            	 Rentity changing=review.get();
            	 changing.setComment(rentity.getComment());
            	 changing.setDate(rentity.getDate());
            	 rrepo.save(changing);
         return "changed";
         }else {
        	 return "error"; 
         }
    }
    
    @GetMapping("/getreview/{pid}")
    public List<Rdto> getReviewsWithUsernames(@PathVariable long pid) {
        List<Rentity> review = rrepo.findByPentityId(pid);
        List<Rdto> userComments = review.stream()
                .map(reviews -> {
                    // Fetch the username using the userid
                    String username = uservice.getusername(reviews.getAentity().getUserid());
                    
                    // Return a DTO with username and comment
                    return new Rdto(username, reviews.getComment(), reviews.getDate(),reviews.getRid());
                })
                .collect(Collectors.toList());

        return userComments;
    }   
}