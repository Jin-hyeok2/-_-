package com.zerobase.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		// Scraper scraper = new YahooFinanceScraper();
		// var result = scraper.scrapCompanyByTicker("MMM");
		// System.out.println(result);
	}

}
