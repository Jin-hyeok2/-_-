package com.zerobase.demo.scraper;

import com.zerobase.demo.model.Company;
import com.zerobase.demo.model.ScrapedResult;

public interface Scraper {
    Company scrapCompanyByTicker(String ticker);
    ScrapedResult scrap(Company company);
}
