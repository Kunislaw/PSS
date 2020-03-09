package com.pss.pssapp.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * Delegation
 */
@Entity
@Data
public class Delegation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    @NotEmpty
    private Date dateTimeStart;

    @NotEmpty
    private Date dateTimeStop;

    private Double travelDietAmount;

    private Integer breakfastNumber;

    private Integer dinnerNumber;

    private Integer supperNumber;

    private enum transportType {
        CAR, TRAIN, BUS
    }

    private Double ticketPrice;

    private enum autoCapacity {
        above900, below900
    }

    private Double km;
    private Double accomodationPrice;
    private Double otherTicketsPrice;
    private Double otherOutlayDesc;
    private Double otherOutlayPrice;

    public Delegation() {
        breakfastNumber = new Integer(0);
        dinnerNumber = new Integer(0);
        supperNumber = new Integer(0);
        travelDietAmount = new Double(30);
        ticketPrice = null;
        km = null;
    }
}