package com.benhvien.Khamthai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.benhvien.Khamthai.dto.DangKyKhamDto;
import com.benhvien.Khamthai.dto.DtoThaiPhu;
import com.benhvien.Khamthai.model.AccountModel;
import com.benhvien.Khamthai.model.BacSiModel;
import com.benhvien.Khamthai.model.CaLamModel;
import com.benhvien.Khamthai.model.LichKhamModel;
import com.benhvien.Khamthai.model.ThaiPhuModel;
import com.benhvien.Khamthai.repository.AccountRepositoty;
import com.benhvien.Khamthai.repository.BacSiRepository;
import com.benhvien.Khamthai.repository.CaLamRepository;
import com.benhvien.Khamthai.repository.LichKhamRepository;
import com.benhvien.Khamthai.repository.RoleRepository;
import com.benhvien.Khamthai.repository.ThaiPhuRepository;
import com.benhvien.Khamthai.request.ThaiPhuRequest;

@Service
public class ThaiPhuService {

	@Autowired
	ThaiPhuRepository thaiPhuRepository;

	@Autowired
	LichKhamRepository lichKhamRepository;

	@Autowired
	BacSiRepository bacSiRepository;

	@Autowired
	CaLamRepository caLamRepository;

	@Autowired
	AccountRepositoty accountRepositoty;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public List<ThaiPhuModel> getAll() {
		return thaiPhuRepository.findAll();
	};

	public PagedListHolder<DtoThaiPhu> get(int page, int size) {
		List<DtoThaiPhu> listDto = new ArrayList<>();
		List<ThaiPhuModel> listThaiPhu = thaiPhuRepository.findAll();
		for (ThaiPhuModel thaiPhu : listThaiPhu) {
			DtoThaiPhu dto = new DtoThaiPhu();
			BeanUtils.copyProperties(thaiPhu, dto);
			Optional<AccountModel> opAccount = accountRepositoty.findByInfoId(thaiPhu.getId());
			if (opAccount.isPresent()) {
				dto.setUsername(opAccount.get().getUsername());
			}
			listDto.add(dto);
		}
		PagedListHolder<DtoThaiPhu> res = new PagedListHolder<>(listDto);
		res.setPage(page);
		res.setPageSize(size);
		return res;
	};

	public void deleteById(String id) {
		Optional<ThaiPhuModel> optional = thaiPhuRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Thai Phu Id not found!");
		}
		Optional<AccountModel> opAccount = accountRepositoty.findByInfoId(id);
		if (!opAccount.isPresent()) {
			throw new EntityNotFoundException("Account not found!");
		}
		accountRepositoty.deleteById(opAccount.get().getId());
		thaiPhuRepository.deleteById(id);
	};

	public ThaiPhuModel create(ThaiPhuRequest request) {
		Optional<ThaiPhuModel> optional = thaiPhuRepository.findByCCCD(request.getCccd());
		if (optional.isPresent()) {
			throw new EntityNotFoundException("Thai Phu already exist!");
		}
		Optional<AccountModel> opAccount = accountRepositoty.findByUsername(request.getUsername());
		if (opAccount.isPresent()) {
			throw new EntityNotFoundException("Username already exist!");
		}
		ThaiPhuModel thaiPhu = new ThaiPhuModel();
		BeanUtils.copyProperties(request, thaiPhu);
		thaiPhu = thaiPhuRepository.save(thaiPhu);
		AccountModel account = new AccountModel();
		account.setUsername(request.getUsername());
		account.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
		account.setInfoId(thaiPhu.getId());
		account.setRoleId(roleRepository.findByRoleTitle("THAIPHU").getId());
		accountRepositoty.save(account);
		return thaiPhu;
	};

	public ThaiPhuModel update(ThaiPhuModel thaiPhu, String id) {
		Optional<ThaiPhuModel> optional = thaiPhuRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Product Id not found!");
		} else {
			thaiPhu.setId(id);
			if (thaiPhu.getCccd() == null || thaiPhu.getCccd().isEmpty()) {
				thaiPhu.setCccd(optional.get().getCccd());
			}
			if (thaiPhu.getDiaChi() == null || thaiPhu.getDiaChi().isEmpty()) {
				thaiPhu.setDiaChi(optional.get().getDiaChi());
			}
			if (thaiPhu.getEmail() == null || thaiPhu.getEmail().isEmpty()) {
				thaiPhu.setEmail(optional.get().getEmail());
			}
			if (thaiPhu.getNgaySinh() == null || thaiPhu.getNgaySinh().isEmpty()) {
				thaiPhu.setNgaySinh(optional.get().getNgaySinh());
			}
			if (thaiPhu.getSdt() == null || thaiPhu.getSdt().isEmpty()) {
				thaiPhu.setSdt(optional.get().getSdt());
			}
			if (thaiPhu.getTen() == null || thaiPhu.getTen().isEmpty()) {
				thaiPhu.setTen(optional.get().getTen());
			}
			return thaiPhuRepository.save(thaiPhu);
		}
	};

	public DangKyKhamDto dangKyKham(LichKhamModel lichKham) {
		DangKyKhamDto res = new DangKyKhamDto();
		Optional<LichKhamModel> opLichKham = lichKhamRepository.findByBacSiIdAndCaLamId(lichKham.getBacSiId(),
				lichKham.getCaLamId(), lichKham.getNgayKham());
		if (opLichKham.isPresent() && !opLichKham.get().getThaiPhuId().isEmpty()) {
			throw new EntityNotFoundException("Ca lam da duoc dang ky truoc do!");
		}
		Optional<ThaiPhuModel> opThaiPhu = thaiPhuRepository.findById(lichKham.getThaiPhuId());
		if (!opThaiPhu.isPresent()) {
			throw new EntityNotFoundException("Khong tim thay id thai phu!");
		}
		Optional<BacSiModel> opBacSi = bacSiRepository.findById(lichKham.getBacSiId());
		if (!opBacSi.isPresent()) {
			throw new EntityNotFoundException("Khong tim thay id bac si!");
		}
		Optional<CaLamModel> opCaLam = caLamRepository.findById(lichKham.getCaLamId());
		if (!opCaLam.isPresent()) {
			throw new EntityNotFoundException("Khong tim thay id ca lam!");
		}
		res.setCaDangKy(opCaLam.get().getTen());
		res.setNgayDangKy(lichKham.getNgayKham());
		res.setSdtBacSy(opBacSi.get().getSdt());
		res.setTenBacSy(opBacSi.get().getTen());
		res.setTenThaiPhu(opThaiPhu.get().getTen());
		return res;
	};

	public ThaiPhuModel getByUsername(String username) {
		Optional<AccountModel> opAccount = accountRepositoty.findByUsername(username);
		if (!opAccount.isPresent()) {
			throw new EntityNotFoundException("Username not found!");
		}
		return thaiPhuRepository.findById(opAccount.get().getInfoId()).get();
	}

}
