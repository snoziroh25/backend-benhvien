package com.benhvien.Khamthai.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.benhvien.Khamthai.model.HoSoKhamModel;

@Repository
public interface HoSoKhamRepository extends MongoRepository<HoSoKhamModel, String> {

	
	@Query(value = "{'soKhamId':?0}")
	List<HoSoKhamModel> findBySoKhamId(String id);
	
	@Query(value = "{'$and':[{'ngayKham':{$gte:?0,$lt:?1}},{'coSoYTeId': ?2}]}")
	List<HoSoKhamModel> findByCSYTAndDate(String timeStart, String timeEnd, String CSYTId);
}
