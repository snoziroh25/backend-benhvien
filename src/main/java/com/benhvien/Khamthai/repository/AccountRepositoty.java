package com.benhvien.Khamthai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.benhvien.Khamthai.model.AccountModel;

@Repository
public interface AccountRepositoty extends MongoRepository<AccountModel, String> {

	@Query(value = "{'username': ?0}")
	Optional<AccountModel> findByUsername(String username);

	@Query(value = "{'infoId': { '$in' : ?0 } }")
	List<AccountModel> findByListId(List<String> listId);

	@Query(value = "{'roleId': ?0}")
	List<AccountModel> findListByRoleId(String roleId);

	@Query(value = "{'infoId': ?0}")
	Optional<AccountModel> findByInfoId(String infoId);
}
