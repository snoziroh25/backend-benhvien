package com.benhvien.Khamthai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.benhvien.Khamthai.dto.HoSoKhamDto;
import com.benhvien.Khamthai.model.BacSiModel;
import com.benhvien.Khamthai.model.CoSoYTeModel;
import com.benhvien.Khamthai.model.HoSoKhamModel;
import com.benhvien.Khamthai.model.SoKhamModel;
import com.benhvien.Khamthai.repository.BacSiRepository;
import com.benhvien.Khamthai.repository.CoSoYTeRepository;
import com.benhvien.Khamthai.repository.HoSoKhamRepository;
import com.benhvien.Khamthai.repository.SoKhamRepository;

@Service
public class HoSoKhamService {

	@Autowired
	HoSoKhamRepository hoSoKhamRepository;

	@Autowired
	SoKhamRepository soKhamRepository;

	@Autowired
	BacSiRepository bacSiRepository;

	@Autowired
	CoSoYTeRepository coSoYTeRepository;

	public List<HoSoKhamModel> getAll() {
		return hoSoKhamRepository.findAll();
	};

	public void deleteById(String id) {
		Optional<HoSoKhamModel> optional = hoSoKhamRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			hoSoKhamRepository.deleteById(id);
		}
	};

	public HoSoKhamModel create(HoSoKhamModel hoSoKham) {
		Optional<SoKhamModel> opSoKham = soKhamRepository.findById(hoSoKham.getSoKhamId());
		if (!opSoKham.isPresent()) {
			throw new EntityNotFoundException("ID ho so khong ton tai!");
		}
		hoSoKham.setTrangThai("NEW");
		return hoSoKhamRepository.save(hoSoKham);
	};

	public HoSoKhamModel update(HoSoKhamModel hoSoKham, String id) {
		Optional<HoSoKhamModel> optional = hoSoKhamRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			hoSoKham.setId(id);
			if (hoSoKham.getGhiChu() == null || hoSoKham.getGhiChu().isEmpty()) {
				hoSoKham.setGhiChu(optional.get().getGhiChu());
			}
			if (hoSoKham.getSoKhamId() == null || hoSoKham.getSoKhamId().isEmpty()) {
				hoSoKham.setSoKhamId(optional.get().getSoKhamId());
			} else {
				Optional<SoKhamModel> opSoKham = soKhamRepository.findById(hoSoKham.getSoKhamId());
				if (!opSoKham.isPresent()) {
					throw new EntityNotFoundException("Id so kham not found!");
				}
			}
			if (hoSoKham.getBacSiId() == null || hoSoKham.getBacSiId().isEmpty()) {
				hoSoKham.setBacSiId(optional.get().getBacSiId());
			} else {
				Optional<BacSiModel> opBacSy = bacSiRepository.findById(hoSoKham.getBacSiId());
				if (!opBacSy.isPresent()) {
					throw new EntityNotFoundException("Id bac si not found!");
				}
			}
			if (hoSoKham.getNgayKham() == null || hoSoKham.getNgayKham().isEmpty()) {
				hoSoKham.setNgayKham(optional.get().getNgayKham());
			}
			if (hoSoKham.getTrangThai().equals("NEW")) {
				hoSoKham.setTrangThai("UPDATED");
			}
			return hoSoKhamRepository.save(hoSoKham);
		}
	};

	public List<HoSoKhamDto> getListHoSoKhamDto(List<HoSoKhamModel> listHoSo) {
		List<HoSoKhamDto> listDto = new ArrayList<>();
		for (HoSoKhamModel hoSo : listHoSo) {
			HoSoKhamDto dto = new HoSoKhamDto();
			Optional<CoSoYTeModel> opCSYT = coSoYTeRepository.findById(hoSo.getCoSoYTeId());
			if (!opCSYT.isPresent()) {
				throw new EntityNotFoundException("CSYT Id not found!");
			}
			Optional<BacSiModel> opBacSi = bacSiRepository.findById(hoSo.getBacSiId());
			if (!opBacSi.isPresent()) {
				throw new EntityNotFoundException("Bac Si Id not found!");
			}
			BeanUtils.copyProperties(hoSo, dto);
			dto.setTenBacSi(opBacSi.get().getTen());
			dto.setTenCoSoYTe(opCSYT.get().getTen());
			listDto.add(dto);
		}
		return listDto;
	}

	public PagedListHolder<HoSoKhamDto> getBySoKhamId(int page, int size, String soKhamId) {
		List<HoSoKhamModel> listHoSoKham = hoSoKhamRepository.findBySoKhamId(soKhamId);
		PagedListHolder<HoSoKhamDto> res = new PagedListHolder<>(getListHoSoKhamDto(listHoSoKham));
		res.setPage(page);
		res.setPageSize(size);
		return res;
	}
}
