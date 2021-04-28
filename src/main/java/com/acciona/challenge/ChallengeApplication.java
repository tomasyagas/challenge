package com.acciona.challenge;

import com.acciona.challenge.api.TwitterStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ChallengeApplication {

	@Autowired
	private TwitterStream twitterStream;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void afterStartup() {
		twitterStream.stream();
	}

}
