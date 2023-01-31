package com.zerobase.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zerobase.demo.model.Company;
import com.zerobase.demo.model.Dividend;
import com.zerobase.demo.model.ScrapedResult;
import com.zerobase.demo.persist.CompanyRepository;
import com.zerobase.demo.persist.DividendRepository;
import com.zerobase.demo.persist.entity.CompanyEntity;
import com.zerobase.demo.persist.entity.DividendEntity;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FinanceService {

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;

    @Cacheable(key = "#companyName", value = "finance")
    public ScrapedResult getDividendByCompanyName(String companyNmae) {
        CompanyEntity company = this.companyRepository.findByName(companyNmae)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회사명입니다."));

        List<DividendEntity> dividendEntities = this.dividendRepository.findAllByCompanyId(company.getId());

        List<Dividend> dividends = dividendEntities.stream()
                .map(e -> new Dividend(e.getDate(), e.getDividend()))
                .collect(Collectors.toList());

        return new ScrapedResult(new Company(company.getTicker(), company.getName()),
                dividends);
    }
}
