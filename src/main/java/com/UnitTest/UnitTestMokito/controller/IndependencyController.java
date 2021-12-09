package com.UnitTest.UnitTestMokito.controller;

import com.UnitTest.UnitTestMokito.models.Country;
import com.UnitTest.UnitTestMokito.models.CountryResponse;

import com.UnitTest.UnitTestMokito.models.User;
import com.UnitTest.UnitTestMokito.repositories.CountryRepository;
import com.UnitTest.UnitTestMokito.repositories.UserRepository;
import com.UnitTest.UnitTestMokito.util.DiferenciaEntreFechas;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Author: VIP
 */
@RestController()
@RequestMapping("/Coutry")
public class IndependencyController {

    CountryResponse countryResponse;
    Optional<Country> country;
    CountryRepository countryRepository;
    DiferenciaEntreFechas diferenciaEntreFechas;


    public IndependencyController(CountryRepository countryRepository,DiferenciaEntreFechas diferenciaEntreFechas) {
        this.countryRepository = countryRepository;
        this.diferenciaEntreFechas = diferenciaEntreFechas;
    }

    @GetMapping(path = "/{IsoCode}")
    public ResponseEntity<CountryResponse> getCountryDetails(@PathVariable("IsoCode") String IsoCode) {
        country = Optional.of(new Country());
        countryResponse = new CountryResponse();

        country = Optional.ofNullable(countryRepository.findCountryByIsoCode(IsoCode.toUpperCase()));

        if (country.isPresent()) {
            Period period = diferenciaEntreFechas.calculateYearsOfIndependency(country.get().getCountryIdependenceDate());
            countryResponse.setCountryName(country.get().getCountryName());
            countryResponse.setCapitalName(country.get().getCountryCapital());
            countryResponse.setIndependenceDate(country.get().getCountryIdependenceDate());
            countryResponse.setDayssOfIndependency(period.getDays());
            countryResponse.setMonthsOfIndependency(period.getMonths());
            countryResponse.setYearsOfIndependency(period.getYears());
        }

        return ResponseEntity.ok(countryResponse);
    }


}