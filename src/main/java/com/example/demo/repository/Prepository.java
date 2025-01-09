package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Pentity;

@Repository

public interface Prepository extends JpaRepository<Pentity,Long>{
	List<Pentity> findByType(String type);

}
