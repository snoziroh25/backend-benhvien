package com.benhvien.Khamthai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.benhvien.Khamthai.model.CaLamModel;

@Repository
public interface CaLamRepository extends MongoRepository<CaLamModel, String> {

	@Query(value = "{'$and':[{'ten': ?0},{'csytId':?1}]}")
	Optional<CaLamModel> findByNameAndCsytId(String ten, String csytId);

	@Query(value = "{'csytId':?0}")
	List<CaLamModel> findByCsytId(String csytId);
}
