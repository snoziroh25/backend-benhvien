package com.benhvien.Khamthai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.benhvien.Khamthai.dto.SoKhamDto;
import com.benhvien.Khamthai.model.AccountModel;
import com.benhvien.Khamthai.model.RoleModel;
import com.benhvien.Khamthai.model.SoKhamModel;
import com.benhvien.Khamthai.model.ThaiPhuModel;
import com.benhvien.Khamthai.model.VacxinModel;
import com.benhvien.Khamthai.repository.AccountRepositoty;
import com.benhvien.Khamthai.repository.RoleRepository;
import com.benhvien.Khamthai.repository.SoKhamRepository;
import com.benhvien.Khamthai.repository.ThaiPhuRepository;
import com.benhvien.Khamthai.repository.VacxinRepository;

@Service
public class SoKhamService {

	@Autowired
	SoKhamRepository soKhamRepository;

	@Autowired
	ThaiPhuRepository thaiPhuRepository;

	@Autowired
	AccountRepositoty accountRepositoty;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	VacxinRepository vacxinRepository;

	public List<SoKhamModel> getAll() {
		return soKhamRepository.findAll();
	};

	public Page<SoKhamModel> get(Pageable pageable) {
		return soKhamRepository.findAll(pageable);
	};

	public List<SoKhamDto> getListSoKhamDto(List<SoKhamModel> listSoKham) {
		List<SoKhamDto> listDto = new ArrayList<>();
		for (SoKhamModel soKham : listSoKham) {
			SoKhamDto dto = new SoKhamDto();
			Optional<ThaiPhuModel> opThaiPhu = thaiPhuRepository.findById(soKham.getThaiPhuId());
			if (!opThaiPhu.isPresent()) {
				throw new EntityNotFoundException("Thai Phu Id not found!");
			}
			BeanUtils.copyProperties(soKham, dto);
			dto.setTenThaiPhu(opThaiPhu.get().getTen());
			if (soKham.getVacxinId() != null) {
				for (String vacxin : soKham.getVacxinId()) {
					Optional<VacxinModel> opVacxin = vacxinRepository.findById(vacxin);
					if (!opVacxin.isPresent()) {
						throw new EntityNotFoundException("Vacxin Id not found!");
					} else {
						if (dto.getVacxin() == "Chưa tiêm") {
							dto.setVacxin(opVacxin.get().getTen());
						} else {
							dto.setVacxin(String.join(", ", dto.getVacxin(), opVacxin.get().getTen()));
						}
					}
				}
			}
			if (soKham.isCapNhat()) {
				dto.setStatus("UPDATED");
			} else {
				dto.setStatus("NEW");
			}
			if (soKham.isTrangThai()) {
				dto.setDongMo("CLOSED");
			} else {
				dto.setDongMo("OPEN");
			}
			listDto.add(dto);
		}
		return listDto;
	}

	public PagedListHolder<SoKhamDto> getByRole(int page, int size, String username) {
		Optional<AccountModel> opAccount = accountRepositoty.findByUsername(username);
		if (!opAccount.isPresent()) {
			throw new EntityNotFoundException("Account Id not found!");
		}
		Optional<RoleModel> opRole = roleRepository.findById(opAccount.get().getRoleId());
		if (!opRole.isPresent()) {
			throw new EntityNotFoundException("Role Id not found!");
		}
		List<SoKhamModel> listSoKham = new ArrayList<>();
		switch (opRole.get().getTitle()) {
		case "THAIPHU":
			listSoKham = soKhamRepository.findByThaiPhuId(opAccount.get().getInfoId());
			break;
		default:
			listSoKham = soKhamRepository.findAll();
			break;
		}
		PagedListHolder<SoKhamDto> res = new PagedListHolder<>(getListSoKhamDto(listSoKham));
		res.setPage(page);
		res.setPageSize(size);
		return res;
	}

	public void deleteById(String id) {
		Optional<SoKhamModel> optional = soKhamRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			soKhamRepository.deleteById(id);
		}
	};

	public SoKhamModel create(SoKhamModel soKham) {
		Optional<ThaiPhuModel> opThaiPhu = thaiPhuRepository.findById(soKham.getThaiPhuId());
		if (!opThaiPhu.isPresent()) {
			throw new EntityNotFoundException("Id Thai Phu not found!");
		}
		return soKhamRepository.save(soKham);
	};

	public SoKhamModel update(SoKhamModel soKham, String id) {
		Optional<SoKhamModel> optional = soKhamRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			soKham.setId(id);
			if (soKham.getLanCoThaiThu() == null || soKham.getLanCoThaiThu().isEmpty()) {
				soKham.setLanCoThaiThu(optional.get().getLanCoThaiThu());
			}
			if (soKham.getNgayKinhCuoi() == null || soKham.getNgayKinhCuoi().isEmpty()) {
				soKham.setNgayKinhCuoi(optional.get().getNgayKinhCuoi());
			}
			if (soKham.getNgaySinhDuKien() == null || soKham.getNgaySinhDuKien().isEmpty()) {
				soKham.setNgaySinhDuKien(optional.get().getNgaySinhDuKien());
			}
			if (soKham.getThaiPhuId() == null || soKham.getThaiPhuId().isEmpty()) {
				soKham.setThaiPhuId(optional.get().getThaiPhuId());
			} else {
				Optional<ThaiPhuModel> opThaiPhu = thaiPhuRepository.findById(soKham.getThaiPhuId());
				if (!opThaiPhu.isPresent()) {
					throw new EntityNotFoundException("Id Thai Phu not found!");
				}
			}
			if (!soKham.isCapNhat()) {
				soKham.setCapNhat(true);
			}
			return soKhamRepository.save(soKham);
		}
	};
}
