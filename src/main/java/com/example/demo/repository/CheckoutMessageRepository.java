package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.CheckoutMessage;

public interface CheckoutMessageRepository extends JpaRepository<CheckoutMessage, Long> {
}
