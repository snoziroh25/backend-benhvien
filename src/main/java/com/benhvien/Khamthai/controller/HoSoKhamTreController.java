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

import com.benhvien.Khamthai.dto.HoSoKhamTreDto;
import com.benhvien.Khamthai.model.HoSoKhamTreModel;
import com.benhvien.Khamthai.repository.HoSoKhamTreRepository;
import com.benhvien.Khamthai.service.HoSoKhamTreService;

@CrossOrigin(origins = "http://localhost:2511")
@RestController
@RequestMapping("/api/hosokhamtre")
@Controller
public class HoSoKhamTreController {

	@Autowired
	HoSoKhamTreService hoSoKhamTreService;

	@Autowired
	HoSoKhamTreRepository hoSoKhamTreRepository;

	@RequestMapping(value = "getall", method = RequestMethod.GET)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> getAll() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<HoSoKhamTreModel> data = hoSoKhamTreService.getAll();
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

	@GetMapping("getbytreid")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> getbytreid(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			PagedListHolder<HoSoKhamTreDto> data = hoSoKhamTreService.getByTreId(page, size, id);
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
	public ResponseEntity<?> create(@RequestBody HoSoKhamTreModel hoSo) {
		Map<String, Object> response = new HashMap<>();
		try {
			HoSoKhamTreModel data = hoSoKhamTreService.create(hoSo);
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
			Optional<HoSoKhamTreModel> option = hoSoKhamTreRepository.findById(id);
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
			hoSoKhamTreService.deleteById(id);
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
	public ResponseEntity<?> update(@RequestBody HoSoKhamTreModel hoSo, @PathVariable(value = "id") String id) {
		Map<String, Object> response = new HashMap<>();
		try {
			HoSoKhamTreModel data = hoSoKhamTreService.update(hoSo, id);
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
