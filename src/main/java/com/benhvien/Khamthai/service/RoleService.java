package com.benhvien.Khamthai.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benhvien.Khamthai.model.RoleModel;
import com.benhvien.Khamthai.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	public List<RoleModel> getAll() {
		return roleRepository.findAll();
	};

	public void deleteById(String id) {
		Optional<RoleModel> option = roleRepository.findById(id);
		if (!option.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			roleRepository.deleteById(id);
		}
	};

	public RoleModel create(RoleModel role) {
		Optional<RoleModel> option = roleRepository.findByTitle(role.getTitle());
		if (option.isPresent()) {
			throw new EntityNotFoundException("Role already exist!");
		}
		return roleRepository.save(role);
	};

	public RoleModel update(RoleModel role, String id) {
		Optional<RoleModel> option = roleRepository.findById(id);
		if (!option.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			role.setId(id);
			if (role.getTitle() == null || role.getTitle().isEmpty()) {
				role.setTitle(option.get().getTitle().trim());
			}
			return roleRepository.save(role);
		}
	};
}
