package com.benhvien.Khamthai.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benhvien.Khamthai.model.CoSoYTeModel;
import com.benhvien.Khamthai.model.ThaiPhuModel;
import com.benhvien.Khamthai.model.ThongBaoModel;
import com.benhvien.Khamthai.repository.CoSoYTeRepository;
import com.benhvien.Khamthai.repository.ThaiPhuRepository;
import com.benhvien.Khamthai.repository.ThongBaoRepository;

@Service
public class ThongBaoService {

	@Autowired
	ThongBaoRepository thongBaoRepository;

	@Autowired
	ThaiPhuRepository thaiPhuRepository;

	@Autowired
	CoSoYTeRepository coSoYTeRepository;

	public List<ThongBaoModel> getAll() {
		return thongBaoRepository.findAll();
	};

	public void deleteById(String id) {
		Optional<ThongBaoModel> option = thongBaoRepository.findById(id);
		if (option.isEmpty()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			thongBaoRepository.deleteById(id);
		}
	};

	public ThongBaoModel create(ThongBaoModel thongBao) {
		thongBao.setStatus("NEW");
		Optional<ThaiPhuModel> opThaiPhu = thaiPhuRepository.findById(thongBao.getThaiPhuId());
		if (!opThaiPhu.isPresent()) {
			Optional<CoSoYTeModel> opCSYT = coSoYTeRepository.findById(thongBao.getThaiPhuId());
			if (opCSYT.isEmpty()) {
				throw new EntityNotFoundException("User Id not found!");
			}
		}
		return thongBaoRepository.save(thongBao);
	};

	public List<ThongBaoModel> getByThaiPhuId(String thaiPhuId) {
		List<ThongBaoModel> res = thongBaoRepository.findByThaiPhuId(thaiPhuId);
		Collections.reverse(res);
		return res;
	}

	public ThongBaoModel update(ThongBaoModel thongBao, String id) {
		Optional<ThongBaoModel> option = thongBaoRepository.findById(id);
		if (option.isEmpty()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			thongBao.setId(id);
			if (thongBao.getContent() == null || thongBao.getContent().isEmpty()) {
				thongBao.setContent(option.get().getContent().trim());
			}
			return thongBaoRepository.save(thongBao);
		}
	};
}
