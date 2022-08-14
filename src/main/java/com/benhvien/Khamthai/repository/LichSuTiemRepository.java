package com.benhvien.Khamthai.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.benhvien.Khamthai.model.LichSuTiemModel;

@Repository
public interface LichSuTiemRepository extends MongoRepository<LichSuTiemModel, String> {

	@Query(value = "{'$and': [ {'coSoYTeId': ?0}, {'ngayTiem' : {$gte:?1,$lt:?2}}] }")
	List<LichSuTiemModel> findByCSYTAndDate(String CSYTId, String timeStart, String timeEnd);
}
