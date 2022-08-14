package com.benhvien.Khamthai.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.benhvien.Khamthai.model.XaPhuongModel;

@Repository
public interface XaPhuongRepository extends MongoRepository<XaPhuongModel, String> {

	@Query(value = "{'quanHuyenId': ?0}")
	List<XaPhuongModel> findByQuanHuyenId(String quanHuyenId);
}
