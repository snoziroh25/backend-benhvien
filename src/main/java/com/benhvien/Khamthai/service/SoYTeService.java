package com.benhvien.Khamthai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.benhvien.Khamthai.dto.SoYTeDto;
import com.benhvien.Khamthai.model.AccountModel;
import com.benhvien.Khamthai.model.SoYTeModel;
import com.benhvien.Khamthai.repository.AccountRepositoty;
import com.benhvien.Khamthai.repository.RoleRepository;
import com.benhvien.Khamthai.repository.SoYTeRepository;
import com.benhvien.Khamthai.request.SoYTeRequest;

@Service
public class SoYTeService {

	@Autowired
	SoYTeRepository soYTeRepository;

	@Autowired
	AccountRepositoty accountRepositoty;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	RoleRepository roleRepository;

	public List<SoYTeModel> getAll() {
		return soYTeRepository.findAll();
	};

	public List<SoYTeDto> getListDto(List<SoYTeModel> list) {
		List<SoYTeDto> res = new ArrayList<>();
		for (SoYTeModel soYTe : list) {
			SoYTeDto dto = new SoYTeDto();
			Optional<AccountModel> opAccount = accountRepositoty.findByInfoId(soYTe.getId());
			if (opAccount.isPresent()) {
				dto.setUsername(opAccount.get().getUsername());
			}
			BeanUtils.copyProperties(soYTe, dto);
			res.add(dto);
		}
		return res;
	};

	public Page<SoYTeDto> get(Pageable pageable) {
		List<SoYTeDto> res = getListDto(soYTeRepository.findAll());
		return new PageImpl<>(res, pageable, res.size());
	};

	public void deleteById(String id) {
		Optional<SoYTeModel> optional = soYTeRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Product Id not found!");
		} else {
			soYTeRepository.deleteById(id);
		}
	};

	public SoYTeModel create(SoYTeRequest request) {
		Optional<AccountModel> opAccount = accountRepositoty.findByUsername(request.getUsername());
		if (opAccount.isPresent()) {
			throw new EntityNotFoundException("Username already exist!");
		}
		Optional<SoYTeModel> optional = soYTeRepository.findByName(request.getTen());
		if (optional.isPresent()) {
			throw new EntityNotFoundException("So Y Te da ton tai!");
		}
		SoYTeModel soYTe = new SoYTeModel();
		BeanUtils.copyProperties(request, soYTe);
		soYTe = soYTeRepository.save(soYTe);
		AccountModel account = new AccountModel();
		account.setUsername(request.getUsername());
		account.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
		account.setInfoId(soYTe.getId());
		account.setRoleId(roleRepository.findByRoleTitle("SOYTE").getId());
		accountRepositoty.save(account);
		return soYTe;
	};

	public SoYTeModel update(SoYTeModel soYTe, String id) {
		Optional<SoYTeModel> optional = soYTeRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("So Y Te Id not found!");
		} else {
			soYTe.setId(id);
			if (soYTe.getTen() == null || soYTe.getTen().isEmpty()) {
				soYTe.setTen(optional.get().getTen());
			}
			if (soYTe.getDiaChi() == null || soYTe.getDiaChi().isEmpty()) {
				soYTe.setDiaChi(optional.get().getDiaChi());
			}
			return soYTeRepository.save(soYTe);
		}
	};
}
