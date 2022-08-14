package com.benhvien.Khamthai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.benhvien.Khamthai.dto.VacxinDto;
import com.benhvien.Khamthai.model.BenhModel;
import com.benhvien.Khamthai.model.VacxinModel;
import com.benhvien.Khamthai.repository.BenhRepository;
import com.benhvien.Khamthai.repository.VacxinRepository;

@Service
public class VacxinService {

	@Autowired
	VacxinRepository vacxinRepository;

	@Autowired
	BenhRepository benhRepository;

	public List<VacxinModel> getAll() {
		return vacxinRepository.findAll();
	};

	public PagedListHolder<VacxinDto> get(int pageNumber, int pageSize) {
		List<VacxinModel> list = vacxinRepository.findAll();
		PagedListHolder<VacxinDto> page = new PagedListHolder<>(getListDto(list));
		page.setPageSize(pageSize);
		page.setPage(pageNumber);
		return page;
	};

	public List<VacxinDto> getListDto(List<VacxinModel> list) {
		List<VacxinDto> res = new ArrayList<>();
		for (VacxinModel vacxin : list) {
			VacxinDto dto = new VacxinDto();
			BeanUtils.copyProperties(vacxin, dto);
			Optional<BenhModel> opBenh = benhRepository.findById(vacxin.getBenhId());
			if (!opBenh.isPresent()) {
				throw new EntityNotFoundException("Benh Id not found!");
			}
			dto.setTenBenh(opBenh.get().getTen());
			res.add(dto);
		}
		return res;
	}

	public void deleteById(String id) {
		Optional<VacxinModel> optional = vacxinRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			vacxinRepository.deleteById(id);
		}
	};

	public List<VacxinModel> getByBenhId(String benhId) {
		return vacxinRepository.findByBenhId(benhId);
	}

	public VacxinModel create(VacxinModel vacxin) {
		Optional<VacxinModel> optional = vacxinRepository.findByName(vacxin.getTen());
		if (optional.isPresent()) {
			throw new EntityNotFoundException("Da ton tai!");
		} else {
			return vacxinRepository.save(vacxin);
		}
	};

	public VacxinModel update(VacxinModel vacxin, String id) {
		Optional<VacxinModel> optional = vacxinRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			vacxin.setId(id);
			if (vacxin.getTen() == null || vacxin.getTen().isEmpty()) {
				vacxin.setTen(optional.get().getTen());
			}
			if (vacxin.getBenhId() == null || vacxin.getBenhId().isEmpty()) {
				vacxin.setBenhId(optional.get().getBenhId());
			} else {
				Optional<BenhModel> opBenh = benhRepository.findById(vacxin.getBenhId());
				if (!opBenh.isPresent()) {
					throw new EntityNotFoundException("Id benh not found!");
				}
			}
			return vacxinRepository.save(vacxin);
		}
	};
}
