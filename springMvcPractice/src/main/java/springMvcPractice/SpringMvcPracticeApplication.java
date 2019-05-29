package springMvcPractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springMvcPractice.config.PictureUploadProperties;

@SpringBootApplication
@EnableConfigurationProperties({PictureUploadProperties.class})
public class SpringMvcPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcPracticeApplication.class, args);
	}

}
