package com.tsystems.logitest.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tsystems.logitest.entity.AuthEvents;
import com.tsystems.logitest.entity.Driver;

public interface AuthRepository extends JpaRepository<AuthEvents, Long> {

	@Query("select e from AuthEvents e where e.driver=:driver order by e.date desc")
	List<AuthEvents> getEventsByDriver(@Param("driver") Driver driver, Pageable pageable);

	@Query("select e from AuthEvents e join e.driver d where d.userName=:userName and e.date between :startPeriod and :endPeriod order by e.date asc")
	List<AuthEvents> getEventsForPeriod(@Param("userName") String userName, @Param("startPeriod") Date startPeriod,
			@Param("endPeriod") Date endPeriod);
}
