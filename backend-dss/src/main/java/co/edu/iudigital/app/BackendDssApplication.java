package co.edu.iudigital.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BackendDssApplication implements CommandLineRunner{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BackendDssApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password = "123456";
		for(int i = 0; i < 2; i++) {
			String encoded = passwordEncoder.encode(password);
			System.out.println(encoded);
		}
	}

	
}
