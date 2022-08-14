package com.benhvien.Khamthai.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benhvien.Khamthai.model.QuanHuyenModel;
import com.benhvien.Khamthai.model.TinhThanhModel;
import com.benhvien.Khamthai.model.XaPhuongModel;
import com.benhvien.Khamthai.repository.QuanHuyenRepository;
import com.benhvien.Khamthai.repository.TinhThanhRepository;
import com.benhvien.Khamthai.repository.XaPhuongRepository;

@Service
public class XaPhuongService {

	@Autowired
	XaPhuongRepository xaPhuongRepository;

	@Autowired
	QuanHuyenRepository quanHuyenRepository;

	@Autowired
	TinhThanhRepository tinhThanhRepository;

	public String getDetail(String xaPhuongId) {
		Optional<XaPhuongModel> opXaPhuong = xaPhuongRepository.findById(xaPhuongId);
		if (opXaPhuong.isEmpty()) {
			throw new EntityNotFoundException("Id xa phuong not found!");
		}
		Optional<QuanHuyenModel> opQuanHuyen = quanHuyenRepository.findById(opXaPhuong.get().getQuanHuyenId());
		if (opQuanHuyen.isEmpty()) {
			throw new EntityNotFoundException("Id quan huyen not found!");
		}
		Optional<TinhThanhModel> opTinhThanh = tinhThanhRepository.findById(opQuanHuyen.get().getTinhThanhId());
		if (opTinhThanh.isEmpty()) {
			throw new EntityNotFoundException("Id tinh thanh not found!");
		}
		return opXaPhuong.get().getTen() + " - " + opQuanHuyen.get().getTen() + " - " + opTinhThanh.get().getTen();
	}

	public List<XaPhuongModel> getAll() {
		return xaPhuongRepository.findAll();
	};

	public List<XaPhuongModel> getByQuanHuyenId(String quanHuyenId) {
		Optional<QuanHuyenModel> optional = quanHuyenRepository.findById(quanHuyenId);
		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Id not found!");
		}
		return xaPhuongRepository.findByQuanHuyenId(quanHuyenId);
	}

	public HashMap<String, Object> getList(String xaPhuongId) {
		Map<String, Object> response = new HashMap<>();
		Optional<XaPhuongModel> optional = xaPhuongRepository.findById(xaPhuongId);
		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Xa phuong Id not found!");
		}
		String quanHuyenId = optional.get().getQuanHuyenId();
		Optional<QuanHuyenModel> opQuanHuyen = quanHuyenRepository.findById(quanHuyenId);
		if (opQuanHuyen.isEmpty()) {
			throw new EntityNotFoundException("Id not found!");
		}
		response.put("xaPhuongs", xaPhuongRepository.findByQuanHuyenId(optional.get().getQuanHuyenId()));
		response.put("quanHuyens", quanHuyenRepository.findByTinhThanhId(opQuanHuyen.get().getTinhThanhId()));
		response.put("quanHuyenSelected",opQuanHuyen.get());
		return (HashMap<String, Object>) response;
	}

	public void deleteById(String id) {
		Optional<XaPhuongModel> optional = xaPhuongRepository.findById(id);
		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			xaPhuongRepository.deleteById(id);
		}
	};

	public XaPhuongModel create(XaPhuongModel xaPhuong) {
		return xaPhuongRepository.save(xaPhuong);
	};

	public List<XaPhuongModel> getByXaPhuongId(String xaPhuongId) {
		Optional<XaPhuongModel> optional = xaPhuongRepository.findById(xaPhuongId);
		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Xa phuong Id not found!");
		}
		return xaPhuongRepository.findByQuanHuyenId(optional.get().getQuanHuyenId());
	}

	public XaPhuongModel update(XaPhuongModel xaPhuong, String id) {
		Optional<XaPhuongModel> optional = xaPhuongRepository.findById(id);
		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Id not found!");
		} else {
			xaPhuong.setId(id);
			if (xaPhuong.getTen() == null || xaPhuong.getTen().isEmpty()) {
				xaPhuong.setTen(optional.get().getTen());
			}
			if (xaPhuong.getQuanHuyenId() == null || xaPhuong.getQuanHuyenId().isEmpty()) {
				xaPhuong.setQuanHuyenId(optional.get().getQuanHuyenId());
			} else {
				Optional<QuanHuyenModel> opQuanHuyen = quanHuyenRepository.findById(xaPhuong.getQuanHuyenId());
				if (!opQuanHuyen.isPresent()) {
					throw new EntityNotFoundException("Id quan huyen not found!");
				}
			}
			return xaPhuongRepository.save(xaPhuong);
		}
	};
}
