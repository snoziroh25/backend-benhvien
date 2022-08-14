package com.benhvien.Khamthai.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.benhvien.Khamthai.model.SoYTeModel;

@Repository
public interface SoYTeRepository extends MongoRepository<SoYTeModel, String> {

	@Query(value = "{'ten': ?0}")
	Optional<SoYTeModel> findByName(String ten);
}
