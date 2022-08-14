package com.benhvien.Khamthai.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.benhvien.Khamthai.model.AccountModel;
import com.benhvien.Khamthai.model.CaLamModel;
import com.benhvien.Khamthai.model.CoSoYTeModel;
import com.benhvien.Khamthai.repository.AccountRepositoty;
import com.benhvien.Khamthai.repository.CaLamRepository;
import com.benhvien.Khamthai.repository.CoSoYTeRepository;

@Service
public class CaLamService {

	@Autowired
	CaLamRepository caLamRepository;

	@Autowired
	CoSoYTeRepository coSoYTeRepository;

	@Autowired
	AccountRepositoty accountRepositoty;

	public List<CaLamModel> getAll() {
		return caLamRepository.findAll();
	};

	public List<CaLamModel> getByUsername(String username) {
		Optional<AccountModel> opAccount = accountRepositoty.findByUsername(username);
		if (opAccount.isEmpty()) {
			throw new EntityNotFoundException("Username not found!");
		}
		return caLamRepository.findByCsytId(opAccount.get().getInfoId());
	}

	public PagedListHolder<CaLamModel> get(int page, int size, String username) {
		Optional<AccountModel> opAccount = accountRepositoty.findByUsername(username);
		if (opAccount.isEmpty()) {
			throw new EntityNotFoundException("Username not found!");
		}
		Optional<CoSoYTeModel> opCsyt = coSoYTeRepository.findById(opAccount.get().getInfoId());
		if (opCsyt.isEmpty()) {
			throw new EntityNotFoundException("CSYT Id not found!");
		}
		PagedListHolder<CaLamModel> res = new PagedListHolder<>(
				caLamRepository.findByCsytId(opAccount.get().getInfoId()));
		res.setPage(page);
		res.setPageSize(size);
		return res;
	};

	public void deleteById(String id) {
		Optional<CaLamModel> optional = caLamRepository.findById(id);
		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			caLamRepository.deleteById(id);
		}
	};

	public CaLamModel create(CaLamModel caLam, String username) {
		Optional<AccountModel> opAccount = accountRepositoty.findByUsername(username);
		if (opAccount.isEmpty()) {
			throw new EntityNotFoundException("Username not found!");
		}
		caLam.setCsytId(opAccount.get().getInfoId());
		Optional<CoSoYTeModel> opCsyt = coSoYTeRepository.findById(caLam.getCsytId());
		if (opCsyt.isEmpty()) {
			throw new EntityNotFoundException("CSYT Id not found!");
		}
		Optional<CaLamModel> optional = caLamRepository.findByNameAndCsytId(caLam.getTen(), caLam.getCsytId());
		if (optional.isPresent()) {
			throw new EntityNotFoundException("Da ton tai!");
		}
		return caLamRepository.save(caLam);
	};

	public CaLamModel update(CaLamModel caLam, String id) {
		Optional<CaLamModel> optional = caLamRepository.findById(id);
		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Id not found!");
		}
		Optional<CoSoYTeModel> opCsyt = coSoYTeRepository.findById(caLam.getCsytId());
		if (opCsyt.isEmpty()) {
			throw new EntityNotFoundException("CSYT Id not found!");
		}
		caLam.setId(id);
		if (caLam.getTen() == null || caLam.getTen().isEmpty()) {
			caLam.setTen(optional.get().getTen());
		}
		return caLamRepository.save(caLam);
	};
}
