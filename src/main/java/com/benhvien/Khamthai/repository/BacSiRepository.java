package com.benhvien.Khamthai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.benhvien.Khamthai.model.BacSiModel;

@Repository
public interface BacSiRepository extends MongoRepository<BacSiModel, String> {

	@Query(value = "{'ten': ?0}")
	Optional<BacSiModel> findByName(String ten);

	@Query(value = "{'id': {'$nin' : ?0 }}")
	List<BacSiModel> findByDateAndCaLam(List<String> listBacSiId);

	@Query(value = "{'$and':[{'id': {'$nin' : ?0 }},{'coSoYTeId':?1}]}")
	List<BacSiModel> findByDateAndCaLamAndCSYTId(List<String> listBacSiId, String csytId);

	@Query(value = "{'coSoYTeId':?0}")
	List<BacSiModel> findByCSYTId(String csytId);
}
