package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Centity;

@Repository

public interface Crepository extends JpaRepository<Centity, Long>{
	boolean existsByAentityUseridAndPentityId(long userid,long productid);
	
	List<Centity> findByAentityUserid(long userid);
	
	Optional<Centity> findByAentityUseridAndPentityId(long userid, long productid);

}
