package com.benhvien.Khamthai.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.benhvien.Khamthai.dto.LichKhamDto;
import com.benhvien.Khamthai.model.AccountModel;
import com.benhvien.Khamthai.model.BacSiModel;
import com.benhvien.Khamthai.model.CaLamModel;
import com.benhvien.Khamthai.model.CoSoYTeModel;
import com.benhvien.Khamthai.model.HoSoKhamModel;
import com.benhvien.Khamthai.model.HoSoKhamTreModel;
import com.benhvien.Khamthai.model.LichKhamModel;
import com.benhvien.Khamthai.model.SoKhamModel;
import com.benhvien.Khamthai.model.ThaiPhuModel;
import com.benhvien.Khamthai.model.TreSoSinhModel;
import com.benhvien.Khamthai.repository.AccountRepositoty;
import com.benhvien.Khamthai.repository.BacSiRepository;
import com.benhvien.Khamthai.repository.CaLamRepository;
import com.benhvien.Khamthai.repository.CoSoYTeRepository;
import com.benhvien.Khamthai.repository.HoSoKhamRepository;
import com.benhvien.Khamthai.repository.HoSoKhamTreRepository;
import com.benhvien.Khamthai.repository.LichKhamRepository;
import com.benhvien.Khamthai.repository.SoKhamRepository;
import com.benhvien.Khamthai.repository.ThaiPhuRepository;
import com.benhvien.Khamthai.repository.TreSoSinhRepository;

@Service
public class LichKhamService {

	@Autowired
	LichKhamRepository lichKhamRepository;

	@Autowired
	BacSiRepository bacSiRepository;

	@Autowired
	CaLamRepository caLamRepository;

	@Autowired
	CoSoYTeRepository coSoYTeRepository;

	@Autowired
	ThaiPhuRepository thaiPhuRepository;

	@Autowired
	SoKhamRepository soKhamRepository;

	@Autowired
	HoSoKhamRepository hoSoKhamRepository;

	@Autowired
	TreSoSinhRepository treSoSinhRepository;

	@Autowired
	HoSoKhamTreRepository hoSoKhamTreRepository;

	@Autowired
	AccountRepositoty accountRepositoty;

	public List<LichKhamModel> getAll() {
		return lichKhamRepository.findAll();
	};

	public PagedListHolder<LichKhamDto> get(int page, int size, String username) {
		Optional<AccountModel> opAccount = accountRepositoty.findByUsername(username);
		if (!opAccount.isPresent()) {
			throw new EntityNotFoundException("Username not found!");
		}
		List<LichKhamModel> listLichKham = lichKhamRepository.findByCsytId(opAccount.get().getInfoId());
		PagedListHolder<LichKhamDto> res = new PagedListHolder<>(getListLichKhamDto(listLichKham));
		res.setPage(page);
		res.setPageSize(size);

		return res;
	};

	public void deleteById(String id) {
		Optional<LichKhamModel> optional = lichKhamRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			lichKhamRepository.deleteById(id);
		}
	};

	public LichKhamModel create(LichKhamModel lichKham) {
		Optional<LichKhamModel> opLichKham = lichKhamRepository.findByLichKham(lichKham.getCoSoYTeId(),
				lichKham.getNgayKham(), lichKham.getBacSiId(), lichKham.getCaLamId());
		if (opLichKham.isPresent()) {
			throw new EntityNotFoundException("Lich kham da ton tai!");
		}
		Optional<BacSiModel> opBacSi = bacSiRepository.findById(lichKham.getBacSiId());
		if (!opBacSi.isPresent()) {
			throw new EntityNotFoundException("Khong tim thay Id bac si!");
		}
		Optional<CaLamModel> opCaLam = caLamRepository.findById(lichKham.getCaLamId());
		if (!opCaLam.isPresent()) {
			throw new EntityNotFoundException("Khong tim thay Id ca lam!");
		}
		return lichKhamRepository.save(lichKham);

	};

	public LichKhamModel update(LichKhamModel lichKham, String id) {
		Optional<LichKhamModel> optional = lichKhamRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			lichKham.setId(id);
			if (lichKham.getBacSiId() == null || lichKham.getBacSiId().isEmpty()) {
				lichKham.setBacSiId(optional.get().getBacSiId());
			} else {
				Optional<BacSiModel> opBacSi = bacSiRepository.findById(lichKham.getBacSiId());
				if (!opBacSi.isPresent()) {
					throw new EntityNotFoundException("Khong tim thay Id bac si!");
				}
			}
			if (lichKham.getCaLamId() == null || lichKham.getCaLamId().isEmpty()) {
				lichKham.setCaLamId(optional.get().getCaLamId());
			} else {
				Optional<CaLamModel> opCaLam = caLamRepository.findById(lichKham.getCaLamId());
				if (!opCaLam.isPresent()) {
					throw new EntityNotFoundException("Khong tim thay Id ca lam!");
				}
			}
			return lichKhamRepository.save(lichKham);
		}
	};

	public LichKhamModel set(LichKhamModel lichKham) {
		Optional<LichKhamModel> opLichKham = lichKhamRepository.findByLichKham(lichKham.getCoSoYTeId(),
				lichKham.getNgayKham(), lichKham.getBacSiId(), lichKham.getCaLamId());
		if (!opLichKham.isPresent()) {
			throw new EntityNotFoundException("Khong tim thay Id lich kham!");
		}
		lichKham.setId(opLichKham.get().getId());
		Optional<CoSoYTeModel> opCoSoYTe = coSoYTeRepository.findById(lichKham.getCoSoYTeId());
		if (!opCoSoYTe.isPresent()) {
			throw new EntityNotFoundException("Khong tim thay Id co so y te!");
		}
		Optional<BacSiModel> opBacSi = bacSiRepository.findById(lichKham.getBacSiId());
		if (!opBacSi.isPresent()) {
			throw new EntityNotFoundException("Khong tim thay Id bac si!");
		}
		Optional<CaLamModel> opCaLam = caLamRepository.findById(lichKham.getCaLamId());
		if (!opCaLam.isPresent()) {
			throw new EntityNotFoundException("Khong tim thay Id ca lam!");
		}
		switch (lichKham.getDoiTuong()) {
		case "thaiphu":
			SoKhamModel soKham = new SoKhamModel();
			Optional<SoKhamModel> opSoKham = soKhamRepository.findByThaiPhuIdAndTrangThaiMo(lichKham.getThaiPhuId());
			if (opSoKham.isPresent()) {
				soKham = opSoKham.get();
			} else {
				soKham.setThaiPhuId(lichKham.getThaiPhuId());
				soKham.setTrangThai(false);
				soKham.setCapNhat(false);

				Date date = Calendar.getInstance().getTime();
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String today = formatter.format(date);
				soKham.setNgayTao(today);

				soKham = soKhamRepository.save(soKham);
			}
			HoSoKhamModel hoSoKham = new HoSoKhamModel();
			hoSoKham.setBacSiId(lichKham.getBacSiId());
			hoSoKham.setCoSoYTeId(lichKham.getCoSoYTeId());
			hoSoKham.setSoKhamId(soKham.getId());
			hoSoKham.setTrangThai("NEW");
			hoSoKham.setNgayKham(lichKham.getNgayKham());
			hoSoKhamRepository.save(hoSoKham);
			break;
		case "tre":
			Optional<TreSoSinhModel> opTre = treSoSinhRepository.findById(lichKham.getThaiPhuId());
			if (!opTre.isPresent()) {
				throw new EntityNotFoundException("Khong tim thay Id tre!");
			}
			HoSoKhamTreModel hoSoKhamTre = new HoSoKhamTreModel();
			hoSoKhamTre.setTreSoSinhId(lichKham.getThaiPhuId());
			hoSoKhamTre.setBacSiId(lichKham.getBacSiId());
			hoSoKhamTre.setNgayKham(lichKham.getNgayKham());
			hoSoKhamTre.setCoSoYTeId(lichKham.getCoSoYTeId());
			hoSoKhamTre.setTrangThai("NEW");
			hoSoKhamTreRepository.save(hoSoKhamTre);
		}
		return lichKhamRepository.save(lichKham);
	}

	public List<LichKhamDto> getListLichKhamDto(List<LichKhamModel> listLichKham) {
		List<LichKhamDto> listDto = new ArrayList<>();
		for (LichKhamModel lichKham : listLichKham) {
			LichKhamDto dto = new LichKhamDto();
			Optional<CaLamModel> opCaLam = caLamRepository.findById(lichKham.getCaLamId());
			if (!opCaLam.isPresent()) {
				throw new EntityNotFoundException("Khong tim thay Id ca lam!");
			}
			Optional<BacSiModel> opBacSi = bacSiRepository.findById(lichKham.getBacSiId());
			if (!opBacSi.isPresent()) {
				throw new EntityNotFoundException("Khong tim thay Id bac si!");
			}
			Optional<CoSoYTeModel> opCoSoYTe = coSoYTeRepository.findById(lichKham.getCoSoYTeId());
			if (!opCoSoYTe.isPresent()) {
				throw new EntityNotFoundException("Khong tim thay Id co so y te!");
			}
			if (lichKham.getThaiPhuId() != null) {
				Optional<ThaiPhuModel> opThaiPhu = thaiPhuRepository.findById(lichKham.getThaiPhuId());
				if (!opThaiPhu.isPresent()) {
					throw new EntityNotFoundException("Khong tim thay Id thai phu!");
				} else {
					dto.setTenThaiPhu(opThaiPhu.get().getTen());
				}
			}
			dto.setNguoiKham(opBacSi.get().getTen());
			dto.setTenCa(opCaLam.get().getTen());
			dto.setTimeStart(opCaLam.get().getStartTime());
			dto.setTimeEnd(opCaLam.get().getEndTime());
			dto.setCoSoYTe(opCoSoYTe.get().getTen());
			BeanUtils.copyProperties(lichKham, dto);
			listDto.add(dto);
		}
		return listDto;
	}

	public List<LichKhamDto> getLichKhamByDateAndCSYT(String coSoYTeId, String ngayKham) {
		return getListLichKhamDto(lichKhamRepository.findByCoSoYTeAndNgayKham(coSoYTeId, ngayKham));
	}
}
