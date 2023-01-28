package com.zerobase.demo.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zerobase.demo.model.Company;
import com.zerobase.demo.model.ScrapedResult;
import com.zerobase.demo.persist.CompanyRepository;
import com.zerobase.demo.persist.DividendRepository;
import com.zerobase.demo.persist.entity.CompanyEntity;
import com.zerobase.demo.persist.entity.DividendEntity;
import com.zerobase.demo.scraper.Scraper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class ScraperScheduler {
    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;

    private final Scraper yahooFinanceScraper;

    @Scheduled(cron = "${scheduler.scrap.yahoo}")
    public void yahooFianaceScheduling() {
        // log.info("scraping scheduler is started");

        List<CompanyEntity> companies = this.companyRepository.findAll();
        for (CompanyEntity company : companies) {
            log.info("scraping scheduler is started ->" + company.getName());
            ScrapedResult scrapedResult = this.yahooFinanceScraper.scrap(Company.builder()
                    .name(company.getName())
                    .ticker(company.getTicker())
                    .build());

            scrapedResult.getDividendEntities().stream()
                    .map(e -> new DividendEntity(company.getId(), e))
                    .forEach(e -> {
                        boolean exists = this.dividendRepository.existsByCompanyIdAndDate(e.getCompanyId(),
                                e.getDate());
                        if (!exists) {
                            this.dividendRepository.save(e);
                        }
                    });
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }
}
