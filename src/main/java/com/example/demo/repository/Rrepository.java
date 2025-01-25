package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Rentity;


@Repository
public interface Rrepository extends JpaRepository<Rentity,Long> {
	List<Rentity> findByPentityId(long id);
	List<Rentity> findByAentityUserid(long userid);

}
