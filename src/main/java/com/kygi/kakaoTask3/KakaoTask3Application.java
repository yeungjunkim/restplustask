package com.kygi.kakaoTask3;

import com.kygi.kakaoTask3.repository.GiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KakaoTask3Application {

	@Autowired
	private GiverRepository giverRepository;
	public static void main(String[] args) {

		SpringApplication.run(KakaoTask3Application.class, args);
	}

}
