package com.benhvien.Khamthai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.benhvien.Khamthai.model.LichKhamModel;

@Repository
public interface LichKhamRepository extends MongoRepository<LichKhamModel, String> {

	@Query(value = "{'$and': [ {'bacSiId': ?0}, {'caLamId' : ?1}, {'ngayKham' : ?2}] }")
	Optional<LichKhamModel> findByBacSiIdAndCaLamId(String bacSiId, String caLamId, String ngayKham);

	@Query(value = "{'$and': [ {'coSoYTeId': ?0}, {'ngayKham' : ?1}, {'thaiPhuId' : null}] }")
	List<LichKhamModel> findByCoSoYTeAndNgayKham(String coSoYTeId, String ngayKham);

	@Query(value = "{'$and': [ {'coSoYTeId': ?0}, {'ngayKham' : ?1}, {'bacSiId' : ?2}, {'caLamId' : ?3}] }")
	Optional<LichKhamModel> findByLichKham(String coSoYTeId, String ngayKham, String bacSiId, String caLamId);

	@Query(value = "{'$and': [ {'ngayKham': ?0}, {'caLamId' : ?1}]}")
	List<LichKhamModel> findByDateAndCaLam(String ngayKham, String caLamId);

	@Query(value = "{'coSoYTeId':?0}")
	List<LichKhamModel> findByCsytId(String csytId);
}
