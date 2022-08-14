package com.benhvien.Khamthai;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.benhvien.Khamthai.model.AccountModel;
import com.benhvien.Khamthai.model.RoleModel;
import com.benhvien.Khamthai.repository.AccountRepositoty;
import com.benhvien.Khamthai.repository.RoleRepository;

@SpringBootApplication
public class KhamthaiApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(KhamthaiApplication.class);
	};

	@Bean
	public ValidatingMongoEventListener validatingMongoEventListener() {
		return new ValidatingMongoEventListener(validator());
	};

	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	};
	
	public static void main(String[] args) {
		SpringApplication.run(KhamthaiApplication.class, args);
	};

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	};
	
	@Bean
	CommandLineRunner init(RoleRepository roleRepository, AccountRepositoty accountRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		return args -> {
			RoleModel adminRole = roleRepository.findByRoleTitle("ADMIN");
			if (adminRole == null) {
				RoleModel newAdmin = new RoleModel("ADMIN");
				roleRepository.save(newAdmin);
			}
			RoleModel soYTeRole = roleRepository.findByRoleTitle("SOYTE");
			if (soYTeRole == null) {
				RoleModel newSoYTe = new RoleModel("SOYTE");
				roleRepository.save(newSoYTe);
			}
			RoleModel coSoYTeRole = roleRepository.findByRoleTitle("COSOYTE");
			if (coSoYTeRole == null) {
				RoleModel newCoSoYTe = new RoleModel("COSOYTE");
				roleRepository.save(newCoSoYTe);
			}
			RoleModel thaiPhuRole = roleRepository.findByRoleTitle("THAIPHU");
			if (thaiPhuRole == null) {
				RoleModel newThaiPhu = new RoleModel("THAIPHU");
				roleRepository.save(newThaiPhu);
			}
			Optional<AccountModel> adminAccount = accountRepository.findByUsername("admin");
			if (!adminAccount.isPresent()) {
				AccountModel newAdminAccount = new AccountModel();
				newAdminAccount.setUsername("admin");
				newAdminAccount.setPassword(bCryptPasswordEncoder.encode("123456"));
				newAdminAccount.setRoleId(roleRepository.findByRoleTitle("ADMIN").getId());
				accountRepository.save(newAdminAccount);
			}
		};
	}

}
