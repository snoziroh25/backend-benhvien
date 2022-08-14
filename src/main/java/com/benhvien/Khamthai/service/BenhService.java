package com.benhvien.Khamthai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.benhvien.Khamthai.dto.BenhDto;
import com.benhvien.Khamthai.model.BenhModel;
import com.benhvien.Khamthai.repository.BenhRepository;

@Service
public class BenhService {

	@Autowired
	BenhRepository benhRepository;

	public List<BenhModel> getAll() {
		return benhRepository.findAll();
	};

	public void deleteById(String id) {
		Optional<BenhModel> optional = benhRepository.findById(id);
		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			benhRepository.deleteById(id);
		}
	};

	public BenhModel create(BenhModel benh) {
		Optional<BenhModel> optional = benhRepository.findByName(benh.getTen());
		if (optional.isPresent()) {
			throw new EntityNotFoundException("Co So Y Te already exist!");
		} else {
			return benhRepository.save(benh);
		}
	};

	public BenhModel update(BenhModel benh, String id) {
		Optional<BenhModel> optional = benhRepository.findById(id);
		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			benh.setId(id);
			if (benh.getTen() == null || benh.getTen().isEmpty()) {
				benh.setTen(optional.get().getTen());
			}
			if (benh.getMoTa() == null || benh.getMoTa().isEmpty()) {
				benh.setMoTa(optional.get().getMoTa());
			}
			return benhRepository.save(benh);
		}
	};

	public List<BenhModel> getBenhByTarget(String target) {
		switch (target) {
		case "tatca":
			return benhRepository.findBenhCanTiem();
		case "TRE":
			return benhRepository.findBenhCanTiemOTre();
		case "THAIPHU":
			return benhRepository.findBenhCanTiemOThaiPhu();
		default:
			return null;
		}
	}

	public PagedListHolder<BenhDto> getBenh(int page, int size) {
		List<BenhModel> list = benhRepository.findAll();
		PagedListHolder<BenhDto> res = new PagedListHolder<>(getListDto(list));
		res.setPage(page);
		res.setPageSize(size);
		return res;
	};

	public List<BenhDto> getListDto(List<BenhModel> list) {
		List<BenhDto> res = new ArrayList<>();
		for (BenhModel benh : list) {
			BenhDto dto = new BenhDto();
			BeanUtils.copyProperties(benh, dto);
			if (benh.isBenhOThaiPhu()) {
				dto.setDoiTuong("THAIPHU");
			} else {
				dto.setDoiTuong("TRE");
			}
			res.add(dto);
		}
		return res;
	}
}
