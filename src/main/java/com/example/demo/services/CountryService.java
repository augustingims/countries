package com.example.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entities.Country;

public interface CountryService {
	
	Country getCountryWithId(Integer id);
	
	Country getCountryWithName(String name);
	
	Country getCountryWithCapital(String capital);

	Country save(Country country);

	Page<Country> findAll(Pageable pageable);

	Country findOne(Integer id);

	void delete(Integer id);
	
	List<Country> getListeCountry(String name,String capital, Long resultatmax);
	
	Country getCountry(String name);


}
