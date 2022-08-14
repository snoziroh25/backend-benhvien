package com.benhvien.Khamthai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.benhvien.Khamthai.dto.CoSoYTeDto;
import com.benhvien.Khamthai.model.AccountModel;
import com.benhvien.Khamthai.model.CoSoYTeModel;
import com.benhvien.Khamthai.model.RoleModel;
import com.benhvien.Khamthai.model.SoYTeModel;
import com.benhvien.Khamthai.model.XaPhuongModel;
import com.benhvien.Khamthai.repository.AccountRepositoty;
import com.benhvien.Khamthai.repository.CoSoYTeRepository;
import com.benhvien.Khamthai.repository.RoleRepository;
import com.benhvien.Khamthai.repository.SoYTeRepository;
import com.benhvien.Khamthai.repository.XaPhuongRepository;
import com.benhvien.Khamthai.request.CoSoYTeRequest;

@Service
public class CoSoYTeService {

	@Autowired
	CoSoYTeRepository coSoYTeRepository;

	@Autowired
	SoYTeRepository soYTeRepository;

	@Autowired
	XaPhuongRepository xaPhuongRepository;

	@Autowired
	AccountRepositoty accountRepositoty;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	XaPhuongService xaPhuongService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public List<CoSoYTeModel> getAll() {
		return coSoYTeRepository.findAll();
	};

	public PagedListHolder<CoSoYTeDto> getByUsername(String username, int page, int size) {
		Optional<AccountModel> opAccount = accountRepositoty.findByUsername(username);
		if (!opAccount.isPresent()) {
			throw new EntityNotFoundException("Account doesn't exist!");
		}
		Optional<RoleModel> opRole = roleRepository.findById(opAccount.get().getRoleId());
		if (!opRole.isPresent()) {
			throw new EntityNotFoundException("Role Id not found!");
		}

		List<CoSoYTeModel> listCSYT = new ArrayList<>();
		switch (opRole.get().getTitle()) {
		case "ADMIN":
			listCSYT = coSoYTeRepository.findAll();
			break;
		case "SOYTE":
			listCSYT = coSoYTeRepository.findBySoYTeId(opAccount.get().getInfoId());
			break;
		default:
			break;
		}
		PagedListHolder<CoSoYTeDto> res = new PagedListHolder<>(getListCoSoYTeDto(listCSYT));
		res.setPage(page);
		res.setPageSize(size);
		return res;
	}

	public Page<CoSoYTeDto> get(Pageable pageable) {
		List<CoSoYTeModel> listCSYT = coSoYTeRepository.findAll();
		return new PageImpl<>(getListCoSoYTeDto(listCSYT), pageable, listCSYT.size());
	};

	public List<CoSoYTeDto> getListCoSoYTeDto(List<CoSoYTeModel> listCSYT) {
		List<CoSoYTeDto> listDto = new ArrayList<>();
		for (CoSoYTeModel csyt : listCSYT) {
			CoSoYTeDto dto = new CoSoYTeDto();
			Optional<SoYTeModel> opSoYTe = soYTeRepository.findById(csyt.getSoYTeId());
			if (!opSoYTe.isPresent()) {
				throw new EntityNotFoundException("So Y Te Id not found!");
			}
			dto.setSoYTe(opSoYTe.get().getTen());
			dto.setDiaChiChiTiet(csyt.getDiaChi() + " - " + xaPhuongService.getDetail(csyt.getXaPhuongId()));
			Optional<AccountModel> opAccount = accountRepositoty.findByInfoId(csyt.getId());
			if (opAccount.isPresent()) {
				dto.setUsername(opAccount.get().getUsername());
			}
			BeanUtils.copyProperties(csyt, dto);
			listDto.add(dto);
		}
		return listDto;
	}

	public void deleteById(String id) {
		Optional<CoSoYTeModel> optional = coSoYTeRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Co So Y Te Id not found!");
		} else {
			coSoYTeRepository.deleteById(id);
		}
	};

	public List<CoSoYTeModel> getByXaPhuongId(String xaPhuongId) {
		Optional<XaPhuongModel> opXaPhuong = xaPhuongRepository.findById(xaPhuongId);
		if (!opXaPhuong.isPresent()) {
			throw new EntityNotFoundException("Xa Phuong Id not found!");
		}
		return coSoYTeRepository.findByXaPhuongId(xaPhuongId);
	}

	public CoSoYTeModel create(CoSoYTeRequest request) {
		Optional<AccountModel> opAccount = accountRepositoty.findByUsername(request.getUsername());
		if (opAccount.isPresent()) {
			throw new EntityNotFoundException("Username already exist!");
		}
		Optional<CoSoYTeModel> optional = coSoYTeRepository.findByName(request.getTen());
		if (optional.isPresent()) {
			throw new EntityNotFoundException("Co So Y Te already exist!");
		}
		Optional<SoYTeModel> opSoYTe = soYTeRepository.findById(request.getSoYTeId());
		if (!opSoYTe.isPresent()) {
			throw new EntityNotFoundException("So Y Te Id not found!");
		}
		Optional<XaPhuongModel> opXaPhuong = xaPhuongRepository.findById(request.getXaPhuongId());
		if (!opXaPhuong.isPresent()) {
			throw new EntityNotFoundException("Xa Phuong Id not found!");
		}
		CoSoYTeModel csyt = new CoSoYTeModel();
		csyt.setDiaChi(request.getDiaChi());
		csyt.setTen(request.getTen());
		csyt.setSoYTeId(request.getSoYTeId());
		csyt.setXaPhuongId(request.getXaPhuongId());
		csyt = coSoYTeRepository.save(csyt);
		AccountModel account = new AccountModel();
		account.setInfoId(csyt.getId());
		account.setUsername(request.getUsername());
		account.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
		Optional<RoleModel> opRole = roleRepository.findByTitle("COSOYTE");
		if (!opRole.isPresent()) {
			throw new EntityNotFoundException("Role not found!");
		}
		account.setRoleId(opRole.get().getId());
		accountRepositoty.save(account);
		return csyt;
	};

	public CoSoYTeModel update(CoSoYTeRequest request, String id) {
		Optional<CoSoYTeModel> optional = coSoYTeRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Co So Y Te Id not found!");
		} else {
			Optional<SoYTeModel> opSoYTe = soYTeRepository.findById(request.getSoYTeId());
			if (!opSoYTe.isPresent()) {
				throw new EntityNotFoundException("So Y Te Id not found!");
			}
			CoSoYTeModel csyt = optional.get();
			csyt.setTen(request.getTen());
			csyt.setDiaChi(request.getDiaChi());
			csyt.setSoYTeId(request.getSoYTeId());
			csyt.setXaPhuongId(request.getXaPhuongId());
			return coSoYTeRepository.save(csyt);
		}
	};

}
