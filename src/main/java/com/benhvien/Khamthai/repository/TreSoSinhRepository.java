package com.benhvien.Khamthai.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.benhvien.Khamthai.model.TreSoSinhModel;

@Repository
public interface TreSoSinhRepository extends MongoRepository<TreSoSinhModel, String> {

	@Query(value = "{'coSoYTeId': {'$in' : ?0 }}")
	List<TreSoSinhModel> findByListCoSoYTeId(List<String> listCSYTId);

	@Query(value = "{'coSoYTeId': ?0 }")
	List<TreSoSinhModel> findByCoSoYTeId(String coSoYTeId);

	@Query(value = "{'thaiPhuId': ?0 }")
	List<TreSoSinhModel> findByThaiPhuId(String thaiPhuId);

	@Query(value = "{'$and':[{'ngaySinh':{$gte:?0,$lt:?1}},{'coSoYTeId': ?2}]}")
	List<TreSoSinhModel> findByCSYTAndDate(String timeStart, String timeEnd, String CSYTId);
}
