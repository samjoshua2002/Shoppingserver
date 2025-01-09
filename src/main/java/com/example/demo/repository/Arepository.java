package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Aentity;

@Repository

public interface Arepository extends JpaRepository<Aentity,Long> {
	Optional<Aentity> findByUseremail(String useremail);
	Optional<Aentity> findByUseremailOrUsernumber(String useremail,long usernumber);
}
