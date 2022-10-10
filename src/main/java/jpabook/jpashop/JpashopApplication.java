package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.hibernate.Hibernate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpashopApplication.class, args);
	}


//	/**
//	 * LAZY 로딩 무시 하고 진행하는 하이버네이트 연관관계의 값들 null로 나옴
//	 * @return
//	 */
//	@Bean
//	Hibernate5Module hibernate5Module() {
//		return new Hibernate5Module();
//	}

	/**
	 * LAZY 로딩을 강제로 실행해서 연관관계의 값들까지 불러오기
	 * @return
	 */
	@Bean
	Hibernate5Module hibernate5Module() {
		Hibernate5Module hibernate5Module = new Hibernate5Module();
//		hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
		return hibernate5Module;
	}


}
