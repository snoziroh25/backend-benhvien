package com.benhvien.Khamthai.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.benhvien.Khamthai.dto.DangKyKhamDto;
import com.benhvien.Khamthai.dto.DtoThaiPhu;
import com.benhvien.Khamthai.model.LichKhamModel;
import com.benhvien.Khamthai.model.ThaiPhuModel;
import com.benhvien.Khamthai.repository.ThaiPhuRepository;
import com.benhvien.Khamthai.request.ThaiPhuRequest;
import com.benhvien.Khamthai.service.ThaiPhuService;

@CrossOrigin(origins = "http://localhost:2511")
@RestController
@RequestMapping("/api/thaiphu")
@Controller
public class ThaiPhuController {

	@Autowired
	ThaiPhuRepository thaiPhuRepository;

	@Autowired
	ThaiPhuService thaiPhuService;

	@RequestMapping(value = "getall", method = RequestMethod.GET)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> getAll() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<ThaiPhuModel> data = thaiPhuService.getAll();
			response.put("data", data);
			response.put("success", true);
			response.put("message", "Ok");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	};

	@GetMapping("get")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> get(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Map<String, Object> response = new HashMap<>();
		try {
			PagedListHolder<DtoThaiPhu> data = thaiPhuService.get(page, size);
			response.put("data", data.getPageList());
			response.put("totalRecord", data.getSource().size());
			response.put("totalPage", data.getPageCount());
			response.put("success", true);
			response.put("message", "Ok");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	};

	@RequestMapping(value = "create", method = RequestMethod.POST)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> create(@RequestBody ThaiPhuRequest request) {
		Map<String, Object> response = new HashMap<>();
		try {
			ThaiPhuModel data = thaiPhuService.create(request);
			response.put("data", data);
			response.put("success", true);
			response.put("message", "Ok");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	};

	@GetMapping("getbyid/{id}")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> getById(@PathVariable(value = "id") String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Optional<ThaiPhuModel> option = thaiPhuRepository.findById(id);
			if (!option.isPresent()) {
				response.put("success", false);
				response.put("message", "Id not found !");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			response.put("data", option.get());
			response.put("success", true);
			response.put("message", "Get info successfully !");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	};

	@DeleteMapping("delete/{id}")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			thaiPhuService.deleteById(id);
			response.put("success", true);
			response.put("message", "Delete successfully !");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	};

	@PutMapping("update/{id}")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> update(@RequestBody ThaiPhuModel thaiPhu, @PathVariable(value = "id") String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			ThaiPhuModel data = thaiPhuService.update(thaiPhu, id);
			response.put("data", data);
			response.put("success", true);
			response.put("message", "Update successfully !");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	};

	@GetMapping("getbyusername/{username}")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> dangKy(@PathVariable(value = "username") String username) {
		Map<String, Object> response = new HashMap<>();
		try {
			ThaiPhuModel data = thaiPhuService.getByUsername(username);
			response.put("data", data);
			response.put("success", true);
			response.put("message", "Success !");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	};

	@GetMapping("dangkykham")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> dangKy(@RequestBody LichKhamModel lichKham) {
		Map<String, Object> response = new HashMap<>();
		try {
			DangKyKhamDto data = thaiPhuService.dangKyKham(lichKham);
			response.put("data", data);
			response.put("success", true);
			response.put("message", "Dang ky thanh cong !");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	};
}
