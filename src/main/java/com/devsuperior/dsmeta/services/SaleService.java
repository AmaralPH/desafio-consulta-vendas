package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<ReportDTO> searchReports(String minDate, String maxDate, String name, Pageable pageable) {

		List<LocalDate> dates = formatDates(minDate, maxDate);

		return repository.findSalesByFilter(dates.get(0), dates.get(1), name, pageable);

	}

	public List<SummaryDTO> searchSummary(String maxDate, String minDate) {
		List<LocalDate> dates = formatDates(maxDate, minDate);

		return repository.findSummaryByFilter(dates.get(0), dates.get(1));
	}

	private List<LocalDate> formatDates( String minDate, String maxDate) {
		List<LocalDate> dates = new ArrayList<>();
		LocalDate endDate;
		LocalDate startDate;

		if (maxDate != null) {
			endDate = LocalDate.parse(maxDate);
		} else {
			endDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}

		if (minDate != null) {
			startDate = LocalDate.parse(minDate);
		} else {
			startDate = endDate.minusYears(1);
		}

		dates.add(startDate);
		dates.add(endDate);

		return dates;
	}
}
