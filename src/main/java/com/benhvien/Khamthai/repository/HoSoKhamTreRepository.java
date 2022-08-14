package com.benhvien.Khamthai.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.benhvien.Khamthai.model.HoSoKhamTreModel;

@Repository
public interface HoSoKhamTreRepository extends MongoRepository<HoSoKhamTreModel, String> {

	@Query(value = "{'treSoSinhId':?0}")
	List<HoSoKhamTreModel> findByTreId(String treSoSinhId);
	
	@Query(value = "{'$and':[{'ngayKham':{$gte:?0,$lt:?1}},{'coSoYTeId': ?2}]}")
	List<HoSoKhamTreModel> findByCSYTAndDate(String timeStart, String timeEnd, String CSYTId);
}
