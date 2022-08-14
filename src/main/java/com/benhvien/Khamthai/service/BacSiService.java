package com.benhvien.Khamthai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.benhvien.Khamthai.model.BacSiModel;
import com.benhvien.Khamthai.model.CoSoYTeModel;
import com.benhvien.Khamthai.model.LichKhamModel;
import com.benhvien.Khamthai.repository.BacSiRepository;
import com.benhvien.Khamthai.repository.CoSoYTeRepository;
import com.benhvien.Khamthai.repository.LichKhamRepository;

@Service
public class BacSiService {

	@Autowired
	BacSiRepository bacSiRepository;

	@Autowired
	LichKhamRepository lichKhamRepository;

	@Autowired
	CoSoYTeRepository coSoYTeRepository;

	public List<BacSiModel> getAll() {
		return bacSiRepository.findAll();
	};

	public Page<BacSiModel> get(Pageable pageable) {
		return bacSiRepository.findAll(pageable);
	};

	public void deleteById(String id) {
		Optional<BacSiModel> optional = bacSiRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			bacSiRepository.deleteById(id);
		}
	};

	List<String> getListBacSiByLichKham(List<LichKhamModel> listLichKham) {
		List<String> listBacSiId = new ArrayList<>();
		for (LichKhamModel lichKham : listLichKham) {
			listBacSiId.add(lichKham.getBacSiId());
		}
		return listBacSiId;
	}

	public List<BacSiModel> getByDateAndCaLam(String date, String caLamId, String csytId) {
		List<LichKhamModel> listLichKham = lichKhamRepository.findByDateAndCaLam(date, caLamId);
		List<String> listBacSiId = getListBacSiByLichKham(listLichKham);
		return bacSiRepository.findByDateAndCaLamAndCSYTId(listBacSiId, csytId);
	}

	public BacSiModel create(BacSiModel bacSi) {
		Optional<BacSiModel> optional = bacSiRepository.findByName(bacSi.getTen());
		if (optional.isPresent()) {
			throw new EntityNotFoundException("Da ton tai!");
		} else {
			return bacSiRepository.save(bacSi);
		}
	};

	public BacSiModel update(BacSiModel bacSi, String id) {
		Optional<BacSiModel> optional = bacSiRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			bacSi.setId(id);
			if (bacSi.getTen() == null || bacSi.getTen().isEmpty()) {
				bacSi.setTen(optional.get().getTen());
			}
			if (bacSi.getSdt() == null || bacSi.getSdt().isEmpty()) {
				bacSi.setSdt(optional.get().getSdt());
			}
			return bacSiRepository.save(bacSi);
		}
	};

	public List<BacSiModel> getByCSYTId(String csytId) {
		Optional<CoSoYTeModel> csytOp = coSoYTeRepository.findById(csytId);
		if (!csytOp.isPresent()) {
			throw new EntityNotFoundException("CSYT Id not found!");
		}
		return bacSiRepository.findByCSYTId(csytId);
	}

	public PagedListHolder<BacSiModel> getPageByCSYTId(String csytId, int page, int size) {
		Optional<CoSoYTeModel> csytOp = coSoYTeRepository.findById(csytId);
		if (!csytOp.isPresent()) {
			throw new EntityNotFoundException("CSYT Id not found!");
		}
		PagedListHolder<BacSiModel> res = new PagedListHolder<>(bacSiRepository.findByCSYTId(csytId));
		res.setPage(page);
		res.setPageSize(size);
		return res;
	}
}
