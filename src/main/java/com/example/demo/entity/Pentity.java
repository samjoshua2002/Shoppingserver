package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Entity
public class Pentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String brand;

    @Column(name = "original_price")
    private int originalPrice;

    @Column(name = "discounted_price")
    private int discountedPrice;

    @Column(name = "image_url")
    private String imageUrl;


    private String category;
    private String type;

    @Lob
    private String description;

    private int rating;

    // Store sizes as a JSON string in the same table
    @Lob
    @Column(name = "sizes")
    private String sizes;  // This will be stored as a JSON string in the database

    @OneToMany(mappedBy = "pentity", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Wentity> wishlists;

    // Default sizes as a list
    private static final List<String> DEFAULT_SIZES = Arrays.asList("Medium", "Small", "Large");

    // Constructor with sizes
    public Pentity(Long id, String title, String brand, int originalPrice, int discountedPrice, String imageUrl,
                     String category, String type, String description, int rating, List<String> sizes,
                    Set<Wentity> wishlists) {
        this.id = id;
        this.title = title;
        this.brand = brand;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.imageUrl = imageUrl;
        this.category = category;
        this.type = type;
        this.description = description;
        this.rating = rating;
        // Store sizes as JSON string in the database
        this.sizes = sizes != null && !sizes.isEmpty() ? toJson(sizes) : toJson(DEFAULT_SIZES);  // Use default sizes if none provided
        this.wishlists = wishlists;
    }

    // Default constructor
    public Pentity() {
        this.sizes = toJson(DEFAULT_SIZES); // Set default sizes in constructor
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(int discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes != null && !sizes.isEmpty() ? toJson(sizes) : toJson(DEFAULT_SIZES); // Use default sizes if none provided
    }

    public Set<Wentity> getWishlists() {
        return wishlists;
    }

    public void setWishlists(Set<Wentity> wishlists) {
        this.wishlists = wishlists;
    }

    // Helper method to convert List<String> to JSON string
    private String toJson(List<String> sizes) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(sizes);
        } catch (IOException e) {
            throw new RuntimeException("Error converting sizes to JSON", e);
        }
    }

    // Helper method to convert JSON string to List<String>
    public List<String> getSizesList() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(sizes, List.class);
        } catch (IOException e) {
            throw new RuntimeException("Error converting JSON to sizes list", e);
        }
    }
}
