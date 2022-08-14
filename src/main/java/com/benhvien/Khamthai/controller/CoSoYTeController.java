package com.benhvien.Khamthai.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.benhvien.Khamthai.dto.CoSoYTeDto;
import com.benhvien.Khamthai.model.CoSoYTeModel;
import com.benhvien.Khamthai.repository.CoSoYTeRepository;
import com.benhvien.Khamthai.request.CoSoYTeRequest;
import com.benhvien.Khamthai.service.CoSoYTeService;

@CrossOrigin(origins = "http://localhost:2511")
@RestController
@RequestMapping("/api/cosoyte")
@Controller
public class CoSoYTeController {

	@Autowired
	CoSoYTeRepository coSoYTeRepository;

	@Autowired
	CoSoYTeService coSoYTeService;

	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> getAll() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<CoSoYTeModel> data = coSoYTeService.getAll();
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

	@GetMapping("getbyxaphuong")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> get(@RequestParam String xaPhuongId) {
		Map<String, Object> response = new HashMap<>();
		try {
			List<CoSoYTeModel> data = coSoYTeService.getByXaPhuongId(xaPhuongId);
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

	@GetMapping("getbyusername")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> getByUsername(@RequestParam(defaultValue = "admin") String username,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Map<String, Object> response = new HashMap<>();
		try {
			PagedListHolder<CoSoYTeDto> data = coSoYTeService.getByUsername(username, page, size);
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

	@GetMapping("get")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> get(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Map<String, Object> response = new HashMap<>();
		try {
			Pageable paging = PageRequest.of(page, size);
			Page<CoSoYTeDto> data = coSoYTeService.get(paging);
			response.put("data", data.getContent());
			response.put("totalRecord", data.getTotalElements());
			response.put("totalPage", data.getTotalPages());
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
	public ResponseEntity<?> create(@RequestBody CoSoYTeRequest coSoYTeRequest) {
		Map<String, Object> response = new HashMap<>();
		try {
			CoSoYTeModel coSoYTe = coSoYTeService.create(coSoYTeRequest);
			response.put("data", coSoYTe);
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
			Optional<CoSoYTeModel> option = coSoYTeRepository.findById(id);
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
			coSoYTeService.deleteById(id);
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
	public ResponseEntity<?> update(@RequestBody CoSoYTeRequest request, @PathVariable(value = "id") String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			CoSoYTeModel data = coSoYTeService.update(request, id);
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
}
