package com.beyondtech.studenttvpss.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beyondtech.studenttvpss.model.TvpssPosition;

@Repository
public class TvpssPositionRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public List<TvpssPosition> findBySchoolCode(String schoolCode) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from TvpssPosition where schoolCode = :schoolCode", TvpssPosition.class)
				.setParameter("schoolCode", schoolCode).list();
	}
}