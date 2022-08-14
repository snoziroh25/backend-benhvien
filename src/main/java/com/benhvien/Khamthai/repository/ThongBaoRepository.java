package com.benhvien.Khamthai.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.benhvien.Khamthai.model.ThongBaoModel;

@Repository
public interface ThongBaoRepository extends MongoRepository<ThongBaoModel, String> {

	@Query(value = "{'thaiPhuId':?0}")
	List<ThongBaoModel> findByThaiPhuId(String thaiPhuId);
}
