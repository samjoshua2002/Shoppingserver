package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Aentity;
import com.example.demo.entity.Centity;
import com.example.demo.entity.Pentity;
import com.example.demo.repository.Arepository;
import com.example.demo.repository.Crepository;
import com.example.demo.repository.Prepository;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.234:3000","https://trendix-seven.vercel.app/"})
@RequestMapping("/cart")
public class Ccontroller {

    @Autowired
    private Crepository crepo;

    @Autowired
    private Prepository prepo;

    @Autowired
    private Arepository arepo;
    

    @PostMapping("/addcart/{uid}/{pid}/{size}/{quantity}")
    @SuppressWarnings("CallToPrintStackTrace")
    public ResponseEntity<String> addToWishlist(@PathVariable long uid, @PathVariable long pid, @PathVariable String size,@PathVariable int quantity) {
        Optional<Aentity> user = arepo.findById(uid);
        Optional<Pentity> product = prepo.findById(pid);

        try {
            if (user.isPresent() && product.isPresent()) {
                if (!crepo.existsByAentityUseridAndPentityId(uid, pid)) {
                    Centity centity = new Centity();
                    centity.setAentity(user.get());
                    centity.setPentity(product.get());
                    centity.setSize(size);
                    centity.setQuantity(quantity);
                    crepo.save(centity);
                    return ResponseEntity.status(HttpStatus.CREATED).body("Added to cart");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product already in cart");
                }
            } else {
                if (!user.isPresent()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
                }
                if (!product.isPresent()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product does not exist");
                }
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong while adding to cart");
        }
    }

    
    @GetMapping("/getproducts/{uid}")
    @SuppressWarnings("CallToPrintStackTrace")
    public ResponseEntity<List<Pentity>> getProducts(@PathVariable long uid) {
        Optional<Aentity> user = arepo.findById(uid);

        try {
            if (user.isPresent()) {
                List<Centity> wish = crepo.findByAentityUserid(uid);
                List<Pentity> products = wish.stream()
                                              .map(Centity::getPentity)
                                              .collect(Collectors.toList());
                return ResponseEntity.ok(products);  // Return the list of products with a 200 OK status
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @GetMapping("/incart/{uid}/{pid}")
    public ResponseEntity<Boolean> isLiked(@PathVariable long uid, @PathVariable long pid) {
        boolean inCart = crepo.existsByAentityUseridAndPentityId(uid, pid);
        return ResponseEntity.ok(inCart);
    }
    
    @GetMapping("/size/{uid}/{pid}")
    public String size(@PathVariable long uid, @PathVariable long pid) {
        Optional<Centity> size = crepo.findByAentityUseridAndPentityId(uid, pid);
        if(size.isPresent()) {
        	return size.get().getSize();
        }
        return null;
    }
    
    @GetMapping("/quantity/{uid}/{pid}")
    public int quantity(@PathVariable long uid, @PathVariable long pid) {
        Optional<Centity> quantity = crepo.findByAentityUseridAndPentityId(uid, pid);
        if(quantity.isPresent()) {
        	return quantity.get().getQuantity();
        }
        return 1;
    }

    @GetMapping("/count/{uid}")
    public int count(@PathVariable long uid) {
    	List<Centity> wish = crepo.findByAentityUserid(uid);
        List<Pentity> products = wish.stream()
                                      .map(Centity::getPentity)
                                      .collect(Collectors.toList());
        return products.size();
    }
    
    @DeleteMapping("/delcart/{uid}/{pid}")
    @SuppressWarnings("CallToPrintStackTrace")
    public ResponseEntity<String> removeFromCart(@PathVariable long uid, @PathVariable long pid) {
        try {
            Optional<Centity> wishlistItem = crepo.findByAentityUseridAndPentityId(uid, pid);

            if (wishlistItem.isPresent()) {
                crepo.delete(wishlistItem.get());
                return ResponseEntity.status(HttpStatus.OK).body("Removed from cart");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("cart item not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while removing from cart");
        }
    }
}


