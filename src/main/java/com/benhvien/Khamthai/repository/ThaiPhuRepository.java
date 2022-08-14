package com.benhvien.Khamthai.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.benhvien.Khamthai.model.ThaiPhuModel;

@Repository
public interface ThaiPhuRepository extends MongoRepository<ThaiPhuModel, String> {

	
	@Query(value = "{'cccd': ?0}")
	Optional<ThaiPhuModel> findByCCCD(String cccd);
}
