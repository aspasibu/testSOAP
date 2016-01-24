package com.tsystems.logitest.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

import com.tsystems.logitest.entity.enums.EventType;

@Entity
@TableGenerator(name = "events_gen", initialValue = 2, allocationSize = 100)
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "events_gen")
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @Enumerated(EnumType.STRING)
    private EventType type;

    private Date date;

    public Events() {
    }

}
