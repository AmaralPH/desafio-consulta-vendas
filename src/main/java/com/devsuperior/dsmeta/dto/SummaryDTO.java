package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Seller;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SummaryDTO {
    private String sellerName;
    private Double total;

    public SummaryDTO() {
    }

    public SummaryDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
