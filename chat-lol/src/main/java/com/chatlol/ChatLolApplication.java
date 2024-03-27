package com.chatlol;

import com.chatlol.application.ListChampionsUseCase;
import com.chatlol.domain.ports.ChampionsRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatLolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatLolApplication.class, args);
	}
	@Bean
	public ListChampionsUseCase provideListChampionsUseCase(ChampionsRepository repository){
		return new ListChampionsUseCase(repository);
	}

}

