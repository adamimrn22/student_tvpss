package com.beyondtech.studenttvpss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beyondtech.studenttvpss.model.TvpssCrew;
import com.beyondtech.studenttvpss.repository.TvpssCrewRepository;

@Service
@Transactional
public class TvpssCrewService {

	@Autowired
	private TvpssCrewRepository tvpssCrewRepository;

	public void saveCrew(TvpssCrew tvpssCrew) {
		tvpssCrewRepository.save(tvpssCrew);
	}

	public List<TvpssCrew> getCrewApplicationByIdentificationNumber(String identificationNumber) {
		return tvpssCrewRepository.findByIdentificationNumber(identificationNumber);
	}

	public TvpssCrew getApplicationById(Long id) {
		return tvpssCrewRepository.findById(id).orElse(null);
	}
}
