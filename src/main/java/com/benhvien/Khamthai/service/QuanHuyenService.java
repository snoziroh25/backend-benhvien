package com.benhvien.Khamthai.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benhvien.Khamthai.model.QuanHuyenModel;
import com.benhvien.Khamthai.model.TinhThanhModel;
import com.benhvien.Khamthai.repository.QuanHuyenRepository;
import com.benhvien.Khamthai.repository.TinhThanhRepository;

@Service
public class QuanHuyenService {

	@Autowired
	QuanHuyenRepository quanHuyenRepository;

	@Autowired
	TinhThanhRepository tinhThanhRepository;

	public List<QuanHuyenModel> getAll() {
		return quanHuyenRepository.findAll();
	};

	public void deleteById(String id) {
		Optional<QuanHuyenModel> optional = quanHuyenRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			quanHuyenRepository.deleteById(id);
		}
	};

	public List<QuanHuyenModel> getByTinhThanhId(String tinhThanhId) {
		Optional<TinhThanhModel> optional = tinhThanhRepository.findById(tinhThanhId);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		}
		return quanHuyenRepository.findByTinhThanhId(tinhThanhId);
	}

	public QuanHuyenModel create(QuanHuyenModel quanHuyen) {
		return quanHuyenRepository.save(quanHuyen);
	};

	public List<QuanHuyenModel> getListByQuanHuyenId(String quanHuyenId) {
		Optional<QuanHuyenModel> op = quanHuyenRepository.findById(quanHuyenId);
		if (!op.isPresent()) {
			throw new EntityNotFoundException("Quan huyen Id not found!");
		}
		return quanHuyenRepository.findByTinhThanhId(op.get().getTinhThanhId());
	}

	public QuanHuyenModel update(QuanHuyenModel quanHuyen, String id) {
		Optional<QuanHuyenModel> optional = quanHuyenRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			quanHuyen.setId(id);
			if (quanHuyen.getTen() == null || quanHuyen.getTen().isEmpty()) {
				quanHuyen.setTen(optional.get().getTen());
			}
			if (quanHuyen.getTinhThanhId() == null || quanHuyen.getTinhThanhId().isEmpty()) {
				quanHuyen.setTinhThanhId(optional.get().getTinhThanhId());
			} else {
				Optional<TinhThanhModel> opTinhThanh = tinhThanhRepository.findById(quanHuyen.getTinhThanhId());
				if (!opTinhThanh.isPresent()) {
					throw new EntityNotFoundException("Id tinh thanh not found!");
				}
			}
			return quanHuyenRepository.save(quanHuyen);
		}
	};
}
