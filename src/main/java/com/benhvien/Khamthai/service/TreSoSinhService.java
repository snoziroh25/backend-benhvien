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

import com.benhvien.Khamthai.dto.TreSoSinhDto;
import com.benhvien.Khamthai.model.AccountModel;
import com.benhvien.Khamthai.model.CoSoYTeModel;
import com.benhvien.Khamthai.model.RoleModel;
import com.benhvien.Khamthai.model.ThaiPhuModel;
import com.benhvien.Khamthai.model.TreSoSinhModel;
import com.benhvien.Khamthai.model.VacxinModel;
import com.benhvien.Khamthai.repository.AccountRepositoty;
import com.benhvien.Khamthai.repository.CoSoYTeRepository;
import com.benhvien.Khamthai.repository.RoleRepository;
import com.benhvien.Khamthai.repository.ThaiPhuRepository;
import com.benhvien.Khamthai.repository.TreSoSinhRepository;
import com.benhvien.Khamthai.repository.VacxinRepository;

@Service
public class TreSoSinhService {

	@Autowired
	TreSoSinhRepository treSoSinhRepository;

	@Autowired
	ThaiPhuRepository thaiPhuRepository;

	@Autowired
	AccountRepositoty accountRepositoty;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	CoSoYTeRepository coSoYTeRepository;

	@Autowired
	VacxinRepository vacxinRepository;

	public List<TreSoSinhModel> getAll() {
		return treSoSinhRepository.findAll();
	};

	public List<TreSoSinhDto> getDto(List<TreSoSinhModel> list) {
		List<TreSoSinhDto> listDto = new ArrayList<>();
		for (TreSoSinhModel tre : list) {
			TreSoSinhDto dto = new TreSoSinhDto();
			BeanUtils.copyProperties(tre, dto);
			Optional<ThaiPhuModel> opThaiPhu = thaiPhuRepository.findById(tre.getThaiPhuId());
			if (!opThaiPhu.isPresent()) {
				throw new EntityNotFoundException("Thai Phu Id not found!");
			}
			dto.setTenThaiPhu(opThaiPhu.get().getTen());
			Optional<CoSoYTeModel> opCSYT = coSoYTeRepository.findById(tre.getCoSoYTeId());
			if (!opCSYT.isPresent()) {
				throw new EntityNotFoundException("CSYT Id not found!");
			}
			dto.setTenCoSoYTe(opCSYT.get().getTen());
			if (tre.getVacxinId() != null) {
				for (String vacxinId : tre.getVacxinId()) {
					Optional<VacxinModel> opVacxin = vacxinRepository.findById(vacxinId);
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
			listDto.add(dto);
		}

		return listDto;
	}

	public PagedListHolder<TreSoSinhDto> getByUsername(String username, int page, int size) {
		Optional<AccountModel> opAccount = accountRepositoty.findByUsername(username);
		if (!opAccount.isPresent()) {
			throw new EntityNotFoundException("Account doesn't exist!");
		}
		Optional<RoleModel> opRole = roleRepository.findById(opAccount.get().getRoleId());
		if (!opRole.isPresent()) {
			throw new EntityNotFoundException("Role Id not found!");
		}
		List<TreSoSinhModel> listTreSoSinh = new ArrayList<>();
		switch (opRole.get().getTitle()) {

		case "ADMIN":
			listTreSoSinh = treSoSinhRepository.findAll();
			break;
		case "SOYTE":
			listTreSoSinh = getByRoleSoYTe(opAccount.get().getInfoId());
			break;
		case "COSOYTE":
			listTreSoSinh = treSoSinhRepository.findByCoSoYTeId(opAccount.get().getInfoId());
			break;
		case "THAIPHU":
			listTreSoSinh = treSoSinhRepository.findByThaiPhuId(opAccount.get().getInfoId());
		default:
			break;
		}
		PagedListHolder<TreSoSinhDto> res = new PagedListHolder<>(getDto(listTreSoSinh));
		res.setPage(page);
		res.setPageSize(size);
		return res;
	}

	public List<TreSoSinhModel> getByThaiPhuId(String username) {
		Optional<AccountModel> opAccount = accountRepositoty.findByUsername(username);
		if (!opAccount.isPresent()) {
			throw new EntityNotFoundException("Account doesn't exist!");
		}
		return treSoSinhRepository.findByThaiPhuId(opAccount.get().getInfoId());
	}

	public List<TreSoSinhModel> getByRoleSoYTe(String soYTeId) {
		List<CoSoYTeModel> listCSYT = coSoYTeRepository.findBySoYTeId(soYTeId);
		List<String> listCSYTId = new ArrayList<>();
		for (CoSoYTeModel csyt : listCSYT) {
			listCSYTId.add(csyt.getId());
		}
		return treSoSinhRepository.findByListCoSoYTeId(listCSYTId);
	}

	public Page<TreSoSinhModel> get(Pageable pageable) {
		return treSoSinhRepository.findAll(pageable);
	};

	public void deleteById(String id) {
		Optional<TreSoSinhModel> optional = treSoSinhRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Tre So Sinh Id not found!");
		} else {
			treSoSinhRepository.deleteById(id);
		}
	};

	public TreSoSinhModel create(TreSoSinhModel treSoSinh) {
		Optional<ThaiPhuModel> opThaiPhu = thaiPhuRepository.findById(treSoSinh.getThaiPhuId());
		if (!opThaiPhu.isPresent()) {
			throw new EntityNotFoundException("Thai Phu Id not found!");
		}
		Optional<CoSoYTeModel> opCSYT = coSoYTeRepository.findById(treSoSinh.getCoSoYTeId());
		if (!opCSYT.isPresent()) {
			throw new EntityNotFoundException("CSYT Id not found!");
		}
		return treSoSinhRepository.save(treSoSinh);
	};

	public TreSoSinhModel update(TreSoSinhModel treSoSinh, String id) {
		Optional<TreSoSinhModel> optional = treSoSinhRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Tre So Sinh Id not found!");
		} else {
			treSoSinh.setId(id);
			if (treSoSinh.getCanNangSinh() == null || treSoSinh.getCanNangSinh().isEmpty()) {
				treSoSinh.setCanNangSinh(optional.get().getCanNangSinh());
			}
			if (treSoSinh.getDiTat() == null || treSoSinh.getDiTat().isEmpty()) {
				treSoSinh.setDiTat(optional.get().getDiTat());
			}
			if (treSoSinh.getNgaySinh() == null || treSoSinh.getNgaySinh().isEmpty()) {
				treSoSinh.setNgaySinh(optional.get().getNgaySinh());
			}
			if (treSoSinh.getNote() == null || treSoSinh.getNote().isEmpty()) {
				treSoSinh.setNote(optional.get().getNote());
			}
			if (treSoSinh.getSinhNon() == null || treSoSinh.getSinhNon().isEmpty()) {
				treSoSinh.setSinhNon(optional.get().getSinhNon());
			}
			if (treSoSinh.getTen() == null || treSoSinh.getTen().isEmpty()) {
				treSoSinh.setTen(optional.get().getTen());
			}
			if (treSoSinh.getThaiPhuId() == null || treSoSinh.getThaiPhuId().isEmpty()) {
				treSoSinh.setThaiPhuId(optional.get().getThaiPhuId());
			} else {
				Optional<ThaiPhuModel> opThaiPhu = thaiPhuRepository.findById(treSoSinh.getThaiPhuId());
				if (!opThaiPhu.isPresent()) {
					throw new EntityNotFoundException("Thai Phu Id not found!");
				}
			}
			return treSoSinhRepository.save(treSoSinh);
		}
	};
}
