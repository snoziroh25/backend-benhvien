package com.benhvien.Khamthai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.benhvien.Khamthai.model.VacxinModel;

@Repository
public interface VacxinRepository extends MongoRepository<VacxinModel, String> {

	@Query(value = "{'ten': ?0}")
	Optional<VacxinModel> findByName(String ten);

	@Query(value = "{'benhId':?0}")
	List<VacxinModel> findByBenhId(String benhId);
}
