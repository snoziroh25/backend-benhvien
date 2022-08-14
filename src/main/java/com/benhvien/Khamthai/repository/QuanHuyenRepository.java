package com.benhvien.Khamthai.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.benhvien.Khamthai.model.QuanHuyenModel;

@Repository
public interface QuanHuyenRepository extends MongoRepository<QuanHuyenModel, String> {


	@Query(value = "{'tinhThanhId': ?0}")
	List<QuanHuyenModel> findByTinhThanhId(String tinhThanhId);
}
