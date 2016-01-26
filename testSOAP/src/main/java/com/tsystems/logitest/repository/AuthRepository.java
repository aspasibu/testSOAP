package com.tsystems.logitest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tsystems.logitest.entity.AuthEvents;
import com.tsystems.logitest.entity.Driver;

public interface AuthRepository extends JpaRepository<AuthEvents, Long> {
    
    @Query("select e from AuthEvents e where e.driver=:driver")    
    List<AuthEvents> getLastEvent(@Param("driver")Driver driver);
}
