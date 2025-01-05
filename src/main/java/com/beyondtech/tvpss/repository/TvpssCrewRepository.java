package com.beyondtech.tvpss.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beyondtech.tvpss.model.TvpssCrew;

@Repository
public class TvpssCrewRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public void save(TvpssCrew tvpssCrew) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(tvpssCrew);
	}

	public List<TvpssCrew> findByIdentificationNumber(String identificationNumber) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from TvpssCrew where identificationNumber = :identificationNumber", TvpssCrew.class)
				.setParameter("identificationNumber", identificationNumber).getResultList();
	}

	public Optional<TvpssCrew> findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return Optional.ofNullable(session.get(TvpssCrew.class, id));
	}
}
