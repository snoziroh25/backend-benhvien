package com.benhvien.Khamthai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.benhvien.Khamthai.dto.DangKyTiemDto;
import com.benhvien.Khamthai.model.BenhModel;
import com.benhvien.Khamthai.model.CoSoYTeModel;
import com.benhvien.Khamthai.model.DangKyTiemModel;
import com.benhvien.Khamthai.model.ThaiPhuModel;
import com.benhvien.Khamthai.model.TreSoSinhModel;
import com.benhvien.Khamthai.repository.BenhRepository;
import com.benhvien.Khamthai.repository.CoSoYTeRepository;
import com.benhvien.Khamthai.repository.DangKyTiemRepository;
import com.benhvien.Khamthai.repository.ThaiPhuRepository;
import com.benhvien.Khamthai.repository.TreSoSinhRepository;

@Service
public class DangKyTiemService {

	@Autowired
	DangKyTiemRepository dangKyTiemRepository;

	@Autowired
	CoSoYTeRepository coSoYTeRepository;

	@Autowired
	ThaiPhuRepository thaiPhuRepository;

	@Autowired
	BenhRepository benhRepository;

	@Autowired
	TreSoSinhRepository treSoSinhRepository;

	public DangKyTiemModel create(DangKyTiemModel dangKyTiem) {
		if (dangKyTiem.getTarget().equals("TRE")) {
			Optional<TreSoSinhModel> opTre = treSoSinhRepository.findById(dangKyTiem.getTargetId());
			if (!opTre.isPresent()) {
				throw new EntityNotFoundException("Tre so sinh Id not found!");
			}
		}
		Optional<CoSoYTeModel> csytOp = coSoYTeRepository.findById(dangKyTiem.getCsytId());
		if (!csytOp.isPresent()) {
			throw new EntityNotFoundException("CSYT Id not found!");
		}
		Optional<BenhModel> benhOp = benhRepository.findById(dangKyTiem.getBenhId());
		if (!benhOp.isPresent()) {
			throw new EntityNotFoundException("Benh Id not found!");
		}
		Optional<ThaiPhuModel> opThaiPhu = thaiPhuRepository.findById(dangKyTiem.getThaiPhuId());
		if (!opThaiPhu.isPresent()) {
			throw new EntityNotFoundException("Thai Phu Id not found!");
		}
		return dangKyTiemRepository.save(dangKyTiem);
	};

	public List<DangKyTiemDto> getDto(List<DangKyTiemModel> listDangKy) {
		List<DangKyTiemDto> res = new ArrayList<>();
		for (DangKyTiemModel dangKy : listDangKy) {
			DangKyTiemDto dto = new DangKyTiemDto();
			BeanUtils.copyProperties(dangKy, dto);
			Optional<ThaiPhuModel> opThaiPhu = thaiPhuRepository.findById(dto.getThaiPhuId());
			if (!opThaiPhu.isPresent()) {
				throw new EntityNotFoundException("Thai Phu Id not found!");
			}
			Optional<CoSoYTeModel> csytOp = coSoYTeRepository.findById(dto.getCsytId());
			if (!csytOp.isPresent()) {
				throw new EntityNotFoundException("CSYT Id not found!");
			}
			Optional<BenhModel> benhOp = benhRepository.findById(dto.getBenhId());
			if (!benhOp.isPresent()) {
				throw new EntityNotFoundException("Benh Id not found!");
			}
			if (dto.getTarget().equals("TRE")) {
				dto.setTarget("Trẻ sơ sinh");
				Optional<TreSoSinhModel> opTre = treSoSinhRepository.findById(dto.getTargetId());
				if (!opTre.isPresent()) {
					throw new EntityNotFoundException("Tre so sinh Id not found!");
				}
				dto.setTargetName(opTre.get().getTen());
			} else {
				dto.setTarget("Thai phụ");
				dto.setTargetName(opThaiPhu.get().getTen());
			}
			dto.setTenBenh(benhOp.get().getTen());
			dto.setTenThaiPhu(opThaiPhu.get().getTen());
			res.add(dto);
		}
		return res;
	}

	public PagedListHolder<DangKyTiemDto> get(int page, int size, String csytId) {
		List<DangKyTiemDto> list = getDto(dangKyTiemRepository.findByCSYTId(csytId));
		PagedListHolder<DangKyTiemDto> res = new PagedListHolder<>(list);
		res.setPage(page);
		res.setPageSize(size);
		return res;
	};

	public List<DangKyTiemModel> getAll() {
		return dangKyTiemRepository.findAll();
	};
}
