package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleDTO(s.id, s.date, s.amount, se.name) " +
            "FROM Sale s " +
            "JOIN s.seller se " +
            "WHERE s.date BETWEEN :startDate AND :endDate " +
            "AND UPPER(se.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<SaleDTO> findSalesByFilter(@Param("endDate") LocalDate endDate,
                                    @Param("startDate") LocalDate startDate,
                                    @Param("name") String name,
                                    Pageable pageable);
}
