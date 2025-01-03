package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.ReportDTO(s.id, s.date, s.amount, se.name) " +
            "FROM Sale s " +
            "JOIN s.seller se " +
            "WHERE s.date BETWEEN :startDate AND :endDate " +
            "AND UPPER(se.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<ReportDTO> findSalesByFilter(@Param("endDate") LocalDate endDate,
                                      @Param("startDate") LocalDate startDate,
                                      @Param("name") String name,
                                      Pageable pageable);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SummaryDTO(se.name, SUM(s.amount)) " +
            "FROM Seller se " +
            "JOIN se.sales s " +
            "WHERE s.date BETWEEN :startDate AND :endDate " +
            "GROUP BY se.name")
    List<SummaryDTO> findSummaryByFilter(@Param("endDate") LocalDate endDate,
                                         @Param("startDate") LocalDate startDate);
}
