package com.benhvien.Khamthai.service;

import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.benhvien.Khamthai.model.BacSiModel;
import com.benhvien.Khamthai.model.CoSoYTeModel;
import com.benhvien.Khamthai.model.LichSuTiemModel;
import com.benhvien.Khamthai.model.SoKhamModel;
import com.benhvien.Khamthai.model.ThaiPhuModel;
import com.benhvien.Khamthai.model.TreSoSinhModel;
import com.benhvien.Khamthai.model.VacxinModel;
import com.benhvien.Khamthai.repository.BacSiRepository;
import com.benhvien.Khamthai.repository.CoSoYTeRepository;
import com.benhvien.Khamthai.repository.LichSuTiemRepository;
import com.benhvien.Khamthai.repository.SoKhamRepository;
import com.benhvien.Khamthai.repository.ThaiPhuRepository;
import com.benhvien.Khamthai.repository.TreSoSinhRepository;
import com.benhvien.Khamthai.repository.VacxinRepository;

@Service
public class LichSuTiemService {

	@Autowired
	LichSuTiemRepository lichSuTiemRepository;

	@Autowired
	SoKhamRepository soKhamRepository;

	@Autowired
	CoSoYTeRepository coSoYTeRepository;

	@Autowired
	BacSiRepository bacSiRepository;

	@Autowired
	VacxinRepository vacxinRepository;

	@Autowired
	ThaiPhuRepository thaiPhuRepository;

	@Autowired
	TreSoSinhRepository treSoSinhRepository;

	public LichSuTiemModel create(LichSuTiemModel lichSuTiem) {
		Optional<CoSoYTeModel> opCSYT = coSoYTeRepository.findById(lichSuTiem.getCoSoYTeId());
		if (!opCSYT.isPresent()) {
			throw new EntityNotFoundException("CSYT Id not found!");
		}
		Optional<BacSiModel> opBacSi = bacSiRepository.findById(lichSuTiem.getBacSiId());
		if (!opBacSi.isPresent()) {
			throw new EntityNotFoundException("Bac Si Id not found!");
		}
		Optional<VacxinModel> opVacxin = vacxinRepository.findById(lichSuTiem.getVacxinId());
		if (!opVacxin.isPresent()) {
			throw new EntityNotFoundException("Vacxin Id not found!");
		}
		if (lichSuTiem.getTarget().equals("THAIPHU")) {
			Optional<ThaiPhuModel> opThaiPhu = thaiPhuRepository.findById(lichSuTiem.getTargetId());
			if (!opThaiPhu.isPresent()) {
				throw new EntityNotFoundException("Thai phu Id not found!");
			}

			Optional<SoKhamModel> opSoKham = soKhamRepository.findByThaiPhuIdAndTrangThaiMo(lichSuTiem.getTargetId());
			if (!opSoKham.isPresent()) {
				throw new EntityNotFoundException("So kham Id not found!");
			}

			SoKhamModel soKham = opSoKham.get();
			if (soKham.getVacxinId() == null) {
				soKham.setVacxinId(new ArrayList<>());
			}
			soKham.addVacxinId(lichSuTiem.getVacxinId());
			soKhamRepository.save(soKham);
		} else if (lichSuTiem.getTarget().equals("TRE")) {
			Optional<TreSoSinhModel> opTre = treSoSinhRepository.findById(lichSuTiem.getTargetId());
			if (!opTre.isPresent()) {
				throw new EntityNotFoundException("Tre So Sinh Id not found!");
			}
			TreSoSinhModel tre = opTre.get();
			if (tre.getVacxinId() == null) {
				tre.setVacxinId(new ArrayList<>());
			}
			tre.addVacxinId(lichSuTiem.getVacxinId());
			treSoSinhRepository.save(tre);
		} else {
			throw new EntityNotFoundException("Couldn't found target!");
		}
		return lichSuTiemRepository.save(lichSuTiem);
	}

	public LichSuTiemModel update(String id, LichSuTiemModel lichSuTiem) {
		Optional<LichSuTiemModel> op = lichSuTiemRepository.findById(id);
		if (!op.isPresent()) {
			throw new EntityNotFoundException("Lich Su Tiem Id not found!");
		}
		if (lichSuTiem.getTarget().equals("THAIPHU")) {
			Optional<ThaiPhuModel> opThaiPhu = thaiPhuRepository.findById(lichSuTiem.getTargetId());
			if (!opThaiPhu.isPresent()) {
				throw new EntityNotFoundException("Thai phu Id not found!");
			}
		} else if (lichSuTiem.getTarget().equals("TRE")) {
			Optional<TreSoSinhModel> opTre = treSoSinhRepository.findById(lichSuTiem.getTargetId());
			if (!opTre.isPresent()) {
				throw new EntityNotFoundException("Tre So Sinh Id not found!");
			}
		} else {
			throw new EntityNotFoundException("Target must not be null!");
		}
		Optional<CoSoYTeModel> opCSYT = coSoYTeRepository.findById(lichSuTiem.getCoSoYTeId());
		if (!opCSYT.isPresent()) {
			throw new EntityNotFoundException("CSYT Id not found!");
		}
		Optional<BacSiModel> opBacSi = bacSiRepository.findById(lichSuTiem.getBacSiId());
		if (!opBacSi.isPresent()) {
			throw new EntityNotFoundException("Bac Si Id not found!");
		}
		Optional<VacxinModel> opVacxin = vacxinRepository.findById(lichSuTiem.getVacxinId());
		if (!opVacxin.isPresent()) {
			throw new EntityNotFoundException("Vacxin Id not found!");
		}
		return lichSuTiemRepository.save(lichSuTiem);
	}

	public Page<LichSuTiemModel> get(Pageable pageable) {
		return lichSuTiemRepository.findAll(pageable);
	}

}
