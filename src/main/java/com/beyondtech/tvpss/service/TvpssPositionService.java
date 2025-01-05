package com.beyondtech.tvpss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beyondtech.tvpss.model.TvpssPosition;
import com.beyondtech.tvpss.repository.TvpssPositionRepository;

@Service
@Transactional
public class TvpssPositionService {

	@Autowired
	private TvpssPositionRepository tvpssPositionRepository;

	// Get all position by school_code
	public List<TvpssPosition> getPositionsBySchoolCode(String schoolCode) {
		return tvpssPositionRepository.findBySchoolCode(schoolCode);
	}

}
