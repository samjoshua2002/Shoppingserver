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
import com.example.demo.entity.Pentity;
import com.example.demo.entity.Wentity;
import com.example.demo.repository.Arepository;
import com.example.demo.repository.Prepository;
import com.example.demo.repository.Wrepository;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.234:3000","https://trendix-seven.vercel.app/"})
@RequestMapping("/wishlist")
public class Wcontroller {

    @Autowired
    private Wrepository wrepo;

    @Autowired
    private Prepository prepo;

    @Autowired
    private Arepository arepo;

  
    @PostMapping("/addwishlist/{uid}/{pid}")
    @SuppressWarnings("CallToPrintStackTrace")
    public ResponseEntity<String> addToWishlist(@PathVariable long uid, @PathVariable long pid) {
        Optional<Aentity> user = arepo.findById(uid);
        Optional<Pentity> product = prepo.findById(pid);

        try {
            if (user.isPresent() && product.isPresent()) {
                if (!wrepo.existsByAentityUseridAndPentityId(uid, pid)) {
                    Wentity wentity = new Wentity();
                    wentity.setAentity(user.get());
                    wentity.setPentity(product.get());
                    wrepo.save(wentity);
                    return ResponseEntity.status(HttpStatus.CREATED).body("Added to wishlist");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product already in wishlist");
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong while adding to wishlist");
        }
    }
    
    @GetMapping("/isLiked/{uid}/{pid}")
    public ResponseEntity<Boolean> isLiked(@PathVariable long uid, @PathVariable long pid) {
        boolean isLiked = wrepo.existsByAentityUseridAndPentityId(uid, pid);
        return ResponseEntity.ok(isLiked);
    }


    
    @GetMapping("/getproducts/{uid}")
    @SuppressWarnings("CallToPrintStackTrace")
    public ResponseEntity<List<Pentity>> getProducts(@PathVariable long uid) {
        Optional<Aentity> user = arepo.findById(uid);

        try {
            if (user.isPresent()) {
                List<Wentity> wish = wrepo.findByAentityUserid(uid);
                List<Pentity> products = wish.stream()
                                              .map(Wentity::getPentity)
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

    @DeleteMapping("/delwishlist/{uid}/{pid}")
    @SuppressWarnings("CallToPrintStackTrace")
    public ResponseEntity<String> removeFromWishlist(@PathVariable long uid, @PathVariable long pid) {
        try {
            Optional<Wentity> wishlistItem = wrepo.findByAentityUseridAndPentityId(uid, pid);

            if (wishlistItem.isPresent()) {
                wrepo.delete(wishlistItem.get());
                return ResponseEntity.status(HttpStatus.OK).body("Removed from wishlist");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wishlist item not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while removing from wishlist");
        }
    }
    
    @PostMapping("/like/{uid}/{pid}")
    public ResponseEntity<String> like(@PathVariable long uid, @PathVariable long pid) {
        Optional<Aentity> user = arepo.findById(uid);
        Optional<Pentity> product = prepo.findById(pid);

        if (user.isPresent() && product.isPresent()) {
            Optional<Wentity> wishlistItem = wrepo.findByAentityUseridAndPentityId(uid, pid);

            if (wishlistItem.isPresent()) {
                Wentity wentity = wishlistItem.get();
                wentity.setLiked(true); // Set liked status
                wrepo.save(wentity);
            } else {
                Wentity wentity = new Wentity();
                wentity.setAentity(user.get());
                wentity.setPentity(product.get());
                wentity.setLiked(true);
                wrepo.save(wentity);
            }

            return ResponseEntity.status(HttpStatus.OK).body("Product liked");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Product not found");
    }

    @PostMapping("/unlike/{uid}/{pid}")
    public ResponseEntity<String> unlike(@PathVariable long uid, @PathVariable long pid) {
        Optional<Wentity> wishlistItem = wrepo.findByAentityUseridAndPentityId(uid, pid);

        if (wishlistItem.isPresent()) {
            Wentity wentity = wishlistItem.get();
            wentity.setLiked(false); // Set liked status
            wrepo.save(wentity);
            return ResponseEntity.status(HttpStatus.OK).body("Product unliked");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wishlist entry not found");
    }

}
