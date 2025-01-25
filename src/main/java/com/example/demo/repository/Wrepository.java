package com.example.demo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Wentity;

@Repository

public interface Wrepository extends JpaRepository<Wentity, Long>{
	boolean existsByAentityUseridAndPentityId(long userid,long productid);
	
	List<Wentity> findByAentityUserid(long userid);
	
	
	Optional<Wentity> findByAentityUseridAndPentityId(long userid, long productid);

}
