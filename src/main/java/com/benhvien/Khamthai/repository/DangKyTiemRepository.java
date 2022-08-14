package com.benhvien.Khamthai.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.benhvien.Khamthai.model.DangKyTiemModel;

@Repository
public interface DangKyTiemRepository extends MongoRepository<DangKyTiemModel, String> {

	@Query(value = "{'csytId': ?0}")
	List<DangKyTiemModel> findByCSYTId(String csytId);
}
