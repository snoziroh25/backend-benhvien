package com.benhvien.Khamthai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.benhvien.Khamthai.model.SoKhamModel;

@Repository
public interface SoKhamRepository extends MongoRepository<SoKhamModel, String> {

	@Query(value = "{'$and': [ {'thaiPhuId': ?0}, {'trangThai' : false}] }")
	Optional<SoKhamModel> findByThaiPhuIdAndTrangThaiMo(String thaiPhuId);

	@Query(value = "{'thaiPhuId':?0}")
	List<SoKhamModel> findByThaiPhuId(String thaiPhuId);

}
