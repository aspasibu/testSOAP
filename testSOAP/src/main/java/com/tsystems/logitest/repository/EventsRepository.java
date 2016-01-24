package com.tsystems.logitest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsystems.logitest.entity.Events;

public interface EventsRepository extends JpaRepository<Events, Long> {

}
