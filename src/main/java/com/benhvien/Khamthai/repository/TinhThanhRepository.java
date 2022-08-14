package com.benhvien.Khamthai.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.benhvien.Khamthai.model.TinhThanhModel;

@Repository
public interface TinhThanhRepository extends MongoRepository<TinhThanhModel, String> {

	@Query(value = "{'ten': ?0}")
	Optional<TinhThanhModel> findByName(String ten);
	
}
