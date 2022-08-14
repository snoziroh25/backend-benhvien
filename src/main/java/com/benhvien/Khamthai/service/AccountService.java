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

import com.benhvien.Khamthai.dto.TaiKhoanDto;
import com.benhvien.Khamthai.dto.TaiKhoanThaiPhuDto;
import com.benhvien.Khamthai.model.AccountModel;
import com.benhvien.Khamthai.model.CoSoYTeModel;
import com.benhvien.Khamthai.model.RoleModel;
import com.benhvien.Khamthai.model.SoYTeModel;
import com.benhvien.Khamthai.model.ThaiPhuModel;
import com.benhvien.Khamthai.repository.AccountRepositoty;
import com.benhvien.Khamthai.repository.CoSoYTeRepository;
import com.benhvien.Khamthai.repository.RoleRepository;
import com.benhvien.Khamthai.repository.SoYTeRepository;
import com.benhvien.Khamthai.repository.ThaiPhuRepository;
import com.benhvien.Khamthai.request.ChangePasswordRequest;
import com.benhvien.Khamthai.request.ThaiPhuDangKyRequest;

@Service
public class AccountService {

	@Autowired
	AccountRepositoty accountRepositoty;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	ThaiPhuRepository thaiPhuRepository;

	@Autowired
	CoSoYTeRepository coSoYTeRepository;

	@Autowired
	SoYTeRepository soYTeRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public List<AccountModel> getAll() {
		return accountRepositoty.findAll();
	};

	public List<AccountModel> getAllByIds(List<String> ids) {
		if (ids.size() > 0) {
			return (List<AccountModel>) accountRepositoty.findAllById(ids);
		}
		return accountRepositoty.findAll();
	};

	public void deleteById(String id) {
		Optional<AccountModel> optional = accountRepositoty.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Usb Id not found!");
		} else {
			accountRepositoty.deleteById(id);
		}
	};

	public AccountModel create(AccountModel account) {
		Optional<AccountModel> optional = accountRepositoty.findByUsername(account.getUsername());
		if (optional.isPresent()) {
			throw new EntityNotFoundException("Account already exist!");
		} else {
			Optional<RoleModel> opRole = roleRepository.findById(account.getRoleId());
			if (opRole.isEmpty()) {
				throw new EntityNotFoundException("Role Id not found!");
			}
			switch (opRole.get().getTitle()) {
			case "THAIPHU":
				Optional<ThaiPhuModel> opThaiPhu = thaiPhuRepository.findById(account.getInfoId());
				if (!opThaiPhu.isPresent()) {
					throw new EntityNotFoundException("Thai Phu Id not found!");
				}
				break;
			case "COSOYTE":
				Optional<CoSoYTeModel> opCSYT = coSoYTeRepository.findById(account.getInfoId());
				if (!opCSYT.isPresent()) {
					throw new EntityNotFoundException("Co So Y Te Id not found!");
				}
				break;
			case "SOYTE":
				Optional<SoYTeModel> opSoYTe = soYTeRepository.findById(account.getInfoId());
				if (!opSoYTe.isPresent()) {
					throw new EntityNotFoundException("So Y Te Id not found!");
				}
				break;
			default:
				account.setInfoId(null);
				break;
			}
			account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
			account.setUsername(account.getUsername().trim().toLowerCase());
			return accountRepositoty.save(account);
		}
	};

	public AccountModel update(AccountModel account, String id) {
		Optional<AccountModel> optional = accountRepositoty.findById(id);
		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Account Id not found!");
		} else {
			account.setId(id);
			if (account.getRoleId() == null || account.getRoleId().isEmpty()) {
				account.setRoleId(optional.get().getId());
			} else {
				Optional<RoleModel> opRole = roleRepository.findById(account.getRoleId());
				if (opRole.isEmpty()) {
					throw new EntityNotFoundException("Role Id not found!");
				}
			}
			if (account.getUsername() == null || account.getUsername().isEmpty()) {
				account.setUsername(optional.get().getUsername());
			}
			if (account.getPassword() == null || account.getPassword().isEmpty()) {
				account.setPassword(optional.get().getPassword());
			} else {
				account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
			}
			account.setUsername(account.getUsername().trim().toLowerCase());
			return accountRepositoty.save(account);
		}
	};

	public TaiKhoanThaiPhuDto getDto(ThaiPhuModel thaiPhu, AccountModel account) {
		TaiKhoanThaiPhuDto dto = new TaiKhoanThaiPhuDto();
		dto.setAccountId(account.getId());
		dto.setCccd(thaiPhu.getCccd());
		dto.setDiaChi(thaiPhu.getDiaChi());
		dto.setEmail(thaiPhu.getEmail());
		dto.setNgaySinh(thaiPhu.getNgaySinh());
		dto.setPassword(account.getPassword());
		dto.setSdt(thaiPhu.getSdt());
		dto.setTen(thaiPhu.getTen());
		dto.setThaiPhuId(thaiPhu.getId());
		dto.setUsername(account.getUsername());
		return dto;
	}

	public ThaiPhuModel getThaiPhu(ThaiPhuDangKyRequest request) {
		ThaiPhuModel thaiPhu = new ThaiPhuModel();
		thaiPhu.setCccd(request.getCccd());
		thaiPhu.setDiaChi(request.getDiaChi());
		thaiPhu.setEmail(request.getEmail());
		thaiPhu.setNgaySinh(request.getNgaySinh());
		thaiPhu.setSdt(request.getSdt());
		thaiPhu.setTen(request.getTen());
		return thaiPhu;
	}

	public TaiKhoanThaiPhuDto dangKyTaiKhoan(ThaiPhuDangKyRequest request) {
		Optional<ThaiPhuModel> opThaiPhu = thaiPhuRepository.findByCCCD(request.getCccd());
		if (opThaiPhu.isPresent()) {
			throw new EntityNotFoundException("Thai Phu already exist!");
		}
		Optional<AccountModel> opAccount = accountRepositoty.findByUsername(request.getUsername());
		if (opAccount.isPresent()) {
			throw new EntityNotFoundException("Account already exist!");
		}
		ThaiPhuModel thaiPhu = thaiPhuRepository.save(getThaiPhu(request));

		AccountModel account = new AccountModel();
		account.setInfoId(thaiPhu.getId());
		account.setRoleId(roleRepository.findByRoleTitle("THAIPHU").getId());
		account.setUsername(request.getUsername().trim().toLowerCase());
		account.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

		accountRepositoty.save(account);

		return getDto(thaiPhu, account);
	}

	public List<String> getListIdCSYT(List<CoSoYTeModel> listCSYT) {
		List<String> res = new ArrayList<>();
		for (CoSoYTeModel csyt : listCSYT) {
			res.add(csyt.getId());
		}
		return res;
	}

	public List<TaiKhoanDto> getAccountDto(List<AccountModel> list) {
		List<TaiKhoanDto> res = new ArrayList<>();
		for (AccountModel account : list) {
			TaiKhoanDto dto = new TaiKhoanDto();
			Optional<RoleModel> role = roleRepository.findById(account.getRoleId());
			if (role.isEmpty()) {
				throw new EntityNotFoundException("Role Id doesn't exist!");
			}
			dto.setRoleTitle(role.get().getTitle());
			BeanUtils.copyProperties(account, dto);
			res.add(dto);
		}
		return res;
	}

	public PagedListHolder<TaiKhoanDto> getAccount(int page, int size, String username) {
		List<AccountModel> listAccount = new ArrayList<>();
		Optional<AccountModel> opAccount = accountRepositoty.findByUsername(username);
		if (!opAccount.isPresent()) {
			throw new EntityNotFoundException("Account doesn't exist!");
		}

		switch (getRoleByAccountId(username)) {
		case "ADMIN":
			listAccount = accountRepositoty.findAll();
			break;
		case "SOYTE":
			List<CoSoYTeModel> listCSYT = coSoYTeRepository.findBySoYTeId(opAccount.get().getInfoId());
			List<String> listIdCSYT = getListIdCSYT(listCSYT);
			listAccount = accountRepositoty.findByListId(listIdCSYT);
			break;
		case "COSOYTE":
			RoleModel role = roleRepository.findByRoleTitle("THAIPHU");
			listAccount = accountRepositoty.findListByRoleId(role.getId());
			break;
		default:
			throw new EntityNotFoundException("Permission denied!");
		}
		PagedListHolder<TaiKhoanDto> res = new PagedListHolder<>(getAccountDto(listAccount));
		res.setPage(page);
		res.setPageSize(size);
		return res;
	}

	public String getRoleByAccountId(String username) {
		Optional<AccountModel> opAccount = accountRepositoty.findByUsername(username);
		if (!opAccount.isPresent()) {
			throw new EntityNotFoundException("Account doesn't exist!");
		}
		Optional<RoleModel> opRole = roleRepository.findById(opAccount.get().getRoleId());
		if (opRole.isEmpty()) {
			throw new EntityNotFoundException("Role Id not found!");
		}
		return opRole.get().getTitle();
	}

	public String getIdByUsername(String username) {
		Optional<AccountModel> opAccount = accountRepositoty.findByUsername(username);
		if (!opAccount.isPresent()) {
			throw new EntityNotFoundException("Account doesn't exist!");
		}
		return opAccount.get().getId();
	}

	public String getInfoIdByUsername(String username) {
		Optional<AccountModel> opAccount = accountRepositoty.findByUsername(username);
		if (!opAccount.isPresent()) {
			throw new EntityNotFoundException("Account doesn't exist!");
		}
		Optional<RoleModel> opRole = roleRepository.findById(opAccount.get().getRoleId());
		if (opRole.isEmpty()) {
			throw new EntityNotFoundException("Role Id not found!");
		}
		String res;
		switch (opRole.get().getTitle()) {
		case "SOYTE":
			Optional<SoYTeModel> opSoYTe = soYTeRepository.findById(opAccount.get().getInfoId());
			if (!opSoYTe.isPresent()) {
				throw new EntityNotFoundException("So Y Te Id not found!");
			}
			res = opSoYTe.get().getId();
			break;
		case "COSOYTE":
			Optional<CoSoYTeModel> opCoSoYTe = coSoYTeRepository.findById(opAccount.get().getInfoId());
			if (!opCoSoYTe.isPresent()) {
				throw new EntityNotFoundException("So Y Te Id not found!");
			}
			res = opCoSoYTe.get().getId();
			break;
		case "THAIPHU":
			Optional<ThaiPhuModel> opThaiPhu = thaiPhuRepository.findById(opAccount.get().getInfoId());
			if (!opThaiPhu.isPresent()) {
				throw new EntityNotFoundException("Thai Phu Id not found!");
			}
			res = opThaiPhu.get().getId();
			break;
		default:
			throw new EntityNotFoundException("This is ADMIN account!");
		}
		return res;
	}

	public AccountModel changePassword(ChangePasswordRequest request) {
		Optional<AccountModel> account = accountRepositoty.findByUsername(request.getUsername());
		if (account.isEmpty()) {
			throw new EntityNotFoundException("Username not found!");
		}
		String oldPass = bCryptPasswordEncoder.encode(request.getOldPassword());
		if (account.get().getPassword() != oldPass) {
			throw new EntityNotFoundException("Old Password does not correct!");
		}
		AccountModel newAccount = account.get();
		newAccount.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
		return accountRepositoty.save(newAccount);
	}

}
