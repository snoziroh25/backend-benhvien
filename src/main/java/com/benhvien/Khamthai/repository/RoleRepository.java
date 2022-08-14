package com.benhvien.Khamthai.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.benhvien.Khamthai.model.RoleModel;

@Repository
public interface RoleRepository extends MongoRepository<RoleModel, String> {

	@Query(value = "{'title': ?0 }")
	Optional<RoleModel> findByTitle(String title);

	@Query(value = "{'title': ?0 }")
	RoleModel findByRoleTitle(String title);

}
