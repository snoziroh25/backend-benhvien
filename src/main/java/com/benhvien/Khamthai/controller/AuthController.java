package com.benhvien.Khamthai.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.benhvien.Khamthai.dto.TaiKhoanThaiPhuDto;
import com.benhvien.Khamthai.request.LoginRequest;
import com.benhvien.Khamthai.request.ThaiPhuDangKyRequest;
import com.benhvien.Khamthai.response.JwtAuthenticationResponse;
import com.benhvien.Khamthai.security.JwtTokenProvider;
import com.benhvien.Khamthai.service.AccountService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	AccountService accountService;

	@PostMapping("login")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequest loginRequest) {
		Map<String, Object> response = new HashMap<>();
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginRequest.getUsername().toLowerCase(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtTokenProvider.generateToken(authentication);
			response.put("success", true);
			response.put("message", "Login successfuly !");
			response.put("data", new JwtAuthenticationResponse(jwt));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("error", e.getMessage());
			response.put("message", "Username or password not found!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	};

	@RequestMapping(value = "thaiphudangky", method = RequestMethod.POST)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> thaiPhuDangKy(@RequestBody ThaiPhuDangKyRequest request) {
		Map<String, Object> response = new HashMap<>();
		try {
			TaiKhoanThaiPhuDto data = accountService.dangKyTaiKhoan(request);
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
}
