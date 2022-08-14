package com.benhvien.Khamthai.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benhvien.Khamthai.model.TinhThanhModel;
import com.benhvien.Khamthai.repository.TinhThanhRepository;

@Service
public class TinhThanhService {

	@Autowired
	TinhThanhRepository tinhThanhRepository;

	public List<TinhThanhModel> getAll() {
		return tinhThanhRepository.findAll();
	};

	public void deleteById(String id) {
		Optional<TinhThanhModel> optional = tinhThanhRepository.findById(id);
		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			tinhThanhRepository.deleteById(id);
		}
	};

	public TinhThanhModel create(TinhThanhModel tinhThanh) {
		Optional<TinhThanhModel> optional = tinhThanhRepository.findByName(tinhThanh.getTen());
		if (optional.isPresent()) {
			throw new EntityNotFoundException("Da ton tai!");
		} else {
			return tinhThanhRepository.save(tinhThanh);
		}
	};

	public TinhThanhModel update(TinhThanhModel tinhThanh, String id) {
		Optional<TinhThanhModel> optional = tinhThanhRepository.findById(id);
		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			tinhThanh.setId(id);
			if (tinhThanh.getTen() == null || tinhThanh.getTen().isEmpty()) {
				tinhThanh.setTen(optional.get().getTen());
			}
			return tinhThanhRepository.save(tinhThanh);
		}
	};
}
