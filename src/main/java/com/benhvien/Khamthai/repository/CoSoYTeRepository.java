package com.benhvien.Khamthai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.benhvien.Khamthai.model.CoSoYTeModel;

@Repository
public interface CoSoYTeRepository extends MongoRepository<CoSoYTeModel, String> {

	@Query(value = "{'ten': ?0}")
	Optional<CoSoYTeModel> findByName(String ten);

	@Query(value = "{'xaPhuongId': ?0}")
	List<CoSoYTeModel> findByXaPhuongId(String xaPhuongId);

	@Query(value = "{'soYTeId': ?0}")
	List<CoSoYTeModel> findBySoYTeId(String soYTeId);
}
