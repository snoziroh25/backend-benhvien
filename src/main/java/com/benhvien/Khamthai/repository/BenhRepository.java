package com.benhvien.Khamthai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.benhvien.Khamthai.model.BenhModel;

@Repository
public interface BenhRepository extends MongoRepository<BenhModel, String> {

	@Query(value = "{'ten': ?0}")
	Optional<BenhModel> findByName(String ten);

	@Query(value = "{'benhOThaiPhu': {$eq: true}}")
	Page<BenhModel> findAtThaiPhu(Pageable pageable);

	@Query(value = "{'benhOThaiPhu': {$ne: true}}")
	Page<BenhModel> findAtTreSoSinh(Pageable pageable);

	@Query(value = "{'canTiem': {$eq: true}}")
	Page<BenhModel> findVacxinNeeded(Pageable pageable);
	
	@Query(value = "{'$and':[{'canTiem': {$eq: true}},{'benhOThaiPhu': {$eq: true}}]}")
	List<BenhModel> findBenhCanTiemOThaiPhu();
	
	@Query(value = "{'$and':[{'canTiem': {$eq: true}},{'benhOThaiPhu': {$ne: true}}]}")
	List<BenhModel> findBenhCanTiemOTre(); 
	
	@Query(value = "{'canTiem': {$eq: true}}")
	List<BenhModel> findBenhCanTiem();
}
