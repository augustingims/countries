package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{

	@Query("Select a From Country a Where a.id =:id")
	Country getAbonneWithId(@Param("id") Integer id);
	
	@Query("Select a From Country a Where a.name =:name")
	Country getAbonneWithName(@Param("name") String name);
	
	@Query("Select a From Country a Where a.capital =:capital")
	Country getAbonneWithCapital(@Param("capital") String capital);
	
}
