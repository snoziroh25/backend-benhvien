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

import com.benhvien.Khamthai.model.BacSiModel;
import com.benhvien.Khamthai.repository.BacSiRepository;
import com.benhvien.Khamthai.request.BacSiRequest;
import com.benhvien.Khamthai.request.LichKhamBacSiRequest;
import com.benhvien.Khamthai.service.BacSiService;

@CrossOrigin(origins = "http://localhost:2511")
@RestController
@RequestMapping("/api/bacsi")
@Controller
public class BacSiController {

	@Autowired
	BacSiRepository bacSiRepository;

	@Autowired
	BacSiService bacSiService;

	@RequestMapping(value = "getall", method = RequestMethod.GET)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> getAllProduct() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<BacSiModel> data = bacSiService.getAll();
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

	@RequestMapping(value = "gettocreatelichkham", method = RequestMethod.POST)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> getToCreateLichKham(@RequestBody LichKhamBacSiRequest request) {
		Map<String, Object> response = new HashMap<>();
		try {
			List<BacSiModel> data = bacSiService.getByDateAndCaLam(request.getDate(), request.getCaLamId(),
					request.getCsytId());
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

	@RequestMapping(value = "get", method = RequestMethod.GET)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> getBacSi(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Map<String, Object> response = new HashMap<>();
		try {
			Pageable paging = PageRequest.of(page, size);
			Page<BacSiModel> data = bacSiService.get(paging);
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

	@RequestMapping(value = "getPageByCsytId", method = RequestMethod.POST)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> getPageByCSYTId(@RequestBody BacSiRequest request) {
		Map<String, Object> response = new HashMap<>();
		try {
			PagedListHolder<BacSiModel> data = bacSiService.getPageByCSYTId(request.getCsytId(), request.getPage(),
					request.getSize());
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

	@RequestMapping(value = "getByCsytId", method = RequestMethod.GET)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> getByCsytId(@RequestParam String csytId) {
		Map<String, Object> response = new HashMap<>();
		try {
			List<BacSiModel> data = bacSiService.getByCSYTId(csytId);
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

	@RequestMapping(value = "create", method = RequestMethod.POST)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> createProduct(@RequestBody BacSiModel bacSy) {
		Map<String, Object> response = new HashMap<>();
		try {
			BacSiModel data = bacSiService.create(bacSy);
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
			Optional<BacSiModel> option = bacSiRepository.findById(id);
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
	public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			bacSiService.deleteById(id);
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
	public ResponseEntity<?> update(@RequestBody BacSiModel bacSy, @PathVariable(value = "id") String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			BacSiModel data = bacSiService.update(bacSy, id);
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
