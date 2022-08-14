package com.benhvien.Khamthai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.benhvien.Khamthai.dto.HoSoKhamTreDto;
import com.benhvien.Khamthai.model.BacSiModel;
import com.benhvien.Khamthai.model.CoSoYTeModel;
import com.benhvien.Khamthai.model.HoSoKhamTreModel;
import com.benhvien.Khamthai.model.TreSoSinhModel;
import com.benhvien.Khamthai.repository.BacSiRepository;
import com.benhvien.Khamthai.repository.CoSoYTeRepository;
import com.benhvien.Khamthai.repository.HoSoKhamTreRepository;
import com.benhvien.Khamthai.repository.TreSoSinhRepository;

@Service
public class HoSoKhamTreService {

	@Autowired
	HoSoKhamTreRepository hoSoKhamTreRepository;

	@Autowired
	TreSoSinhRepository treSoSinhRepository;

	@Autowired
	BacSiRepository bacSiRepository;

	@Autowired
	CoSoYTeRepository coSoYTeRepository;

	public List<HoSoKhamTreModel> getAll() {
		return hoSoKhamTreRepository.findAll();
	};

	public void deleteById(String id) {
		Optional<HoSoKhamTreModel> optional = hoSoKhamTreRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			hoSoKhamTreRepository.deleteById(id);
		}
	};

	public HoSoKhamTreModel create(HoSoKhamTreModel hoSoKham) {
		Optional<TreSoSinhModel> opTre = treSoSinhRepository.findById(hoSoKham.getTreSoSinhId());
		if (!opTre.isPresent()) {
			throw new EntityNotFoundException("ID tre khong ton tai!");
		}
		Optional<BacSiModel> opBacSi = bacSiRepository.findById(hoSoKham.getBacSiId());
		if (!opBacSi.isPresent()) {
			throw new EntityNotFoundException("ID bac si khong ton tai!");
		}
		Optional<CoSoYTeModel> opCSYT = coSoYTeRepository.findById(hoSoKham.getCoSoYTeId());
		if (!opCSYT.isPresent()) {
			throw new EntityNotFoundException("ID CSYT khong ton tai!");
		}
		return hoSoKhamTreRepository.save(hoSoKham);
	};

	public List<HoSoKhamTreDto> getDto(List<HoSoKhamTreModel> list) {
		List<HoSoKhamTreDto> listDto = new ArrayList<>();
		for (HoSoKhamTreModel hoSo : list) {
			HoSoKhamTreDto dto = new HoSoKhamTreDto();
			Optional<CoSoYTeModel> opCSYT = coSoYTeRepository.findById(hoSo.getCoSoYTeId());
			if (!opCSYT.isPresent()) {
				throw new EntityNotFoundException("CSYT Id not found!");
			}
			Optional<BacSiModel> opBacSi = bacSiRepository.findById(hoSo.getBacSiId());
			if (!opBacSi.isPresent()) {
				throw new EntityNotFoundException("Bac Si Id not found!");
			}
			Optional<TreSoSinhModel> opTre = treSoSinhRepository.findById(hoSo.getTreSoSinhId());
			if (!opTre.isPresent()) {
				throw new EntityNotFoundException("Tre So Sinh Id not found!");
			}
			BeanUtils.copyProperties(hoSo, dto);
			dto.setTenBacSi(opBacSi.get().getTen());
			dto.setTenCSYT(opCSYT.get().getTen());
			dto.setTen(opTre.get().getTen());
			listDto.add(dto);
		}
		return listDto;
	}

	public PagedListHolder<HoSoKhamTreDto> getByTreId(int page, int size, String id) {
		List<HoSoKhamTreModel> listHoSo = hoSoKhamTreRepository.findByTreId(id);
		PagedListHolder<HoSoKhamTreDto> res = new PagedListHolder<>(getDto(listHoSo));
		res.setPage(page);
		res.setPageSize(size);
		return res;
	}

	public HoSoKhamTreModel update(HoSoKhamTreModel hoSoKham, String id) {
		Optional<HoSoKhamTreModel> op = hoSoKhamTreRepository.findById(id);
		if (!op.isPresent()) {
			throw new EntityNotFoundException("ID ho so khong ton tai!");
		}
		hoSoKham.setId(id);
		Optional<TreSoSinhModel> opTre = treSoSinhRepository.findById(hoSoKham.getTreSoSinhId());
		if (!opTre.isPresent()) {
			throw new EntityNotFoundException("ID tre khong ton tai!");
		}
		Optional<BacSiModel> opBacSi = bacSiRepository.findById(hoSoKham.getBacSiId());
		if (!opBacSi.isPresent()) {
			throw new EntityNotFoundException("ID bac si khong ton tai!");
		}
		if (hoSoKham.getTrangThai().equals("NEW")) {
			hoSoKham.setTrangThai("UPDATED");
		}
		return hoSoKhamTreRepository.save(hoSoKham);
	};

}
