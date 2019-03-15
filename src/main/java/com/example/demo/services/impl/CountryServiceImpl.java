package com.example.demo.services.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Country;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.services.CountryService;
import com.example.demo.services.utils.UtilsMethods;

@Service
public class CountryServiceImpl implements CountryService{

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private EntityManager em;
	
	@Override
	public Country getCountryWithId(Integer id) {
		return countryRepository.getAbonneWithId(id);
	}

	@Override
	public Country getCountryWithName(String name) {
		return countryRepository.getAbonneWithName(name);
	}

	@Override
	public Country getCountryWithCapital(String capital) {
		return countryRepository.getAbonneWithCapital(capital);
	}

	@Override
	public Country save(Country country) {
		return countryRepository.save(country);
	}

	@Override
	public Page<Country> findAll(Pageable pageable) {
		return countryRepository.findAll(pageable);
	}

	@Override
	public Country findOne(Integer id) {
		return countryRepository.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		countryRepository.deleteById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Country> getListeCountry(String name,String capital, Long resultatmax) {
		Query q = null;
		try {

			String query = "Select a from Country a where 1=1 ";

			if (name != null) {

				query += " And a.name =:name ";

			}

			if (capital != null) {

				query += " And a.capital Like :capital";

			}

			query += " Order By a.name Asc";

			q = em.createQuery(query);

			if (name != null) {

				q.setParameter("name", name);
			}

			if (capital != null) {

				q.setParameter("capital",
						UtilsMethods.formatStringToFind(capital));
			}

			if (resultatmax != null) {

				q.setMaxResults(resultatmax.intValue());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return q.getResultList();
	}

	@Override
	public Country getCountry(String name) {
		

			Query q = null;

			Country result = null;
			try {

				String query = "Select a from Country a where 1=1 ";

				if (name != null) {

					query += " And a.name =:name ";

				}

				query += " Order By a.name Asc";

				q = em.createQuery(query);

				if (name != null) {

					q.setParameter("name", name);
				}

				result = (Country) q.setMaxResults(1).getSingleResult();

			} catch (Exception e) {
				// TODO: handle exception
			}

			return result;

	}

}
