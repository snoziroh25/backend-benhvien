package com.benhvien.Khamthai.controller;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.benhvien.Khamthai.service.ReportService;

@CrossOrigin(origins = "http://localhost:2511")
@RestController
@RequestMapping("/api/report")
@Controller
public class ReportController {

	@Autowired
	ReportService reportService;

	@GetMapping("tresosinh")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> get(@RequestParam String username, @RequestParam(defaultValue = "2022") String year) {
		Map<String, Object> response = new HashMap<>();
		try {
			Object data = reportService.getReportByUsername(username, year);
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

	@RequestMapping(value = "excel", method = RequestMethod.POST)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> downloadsReport(@RequestBody List<String> request) throws Exception {
		ByteArrayInputStream in = reportService.exportExcel(request.get(0), request.get(1));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + "report" + ".xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
}
