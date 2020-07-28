package schoolsys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan({"schoolsys.**.mapper"}) 
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SchoolsysApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolsysApplication.class, args);
	}

}
