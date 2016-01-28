package com.tsystems.logitest.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tsystems.logitest.entity.Driver;
import com.tsystems.logitest.entity.DutyEvents;

public interface DutyRepository extends JpaRepository<DutyEvents, Long> {

	@Query("select e from DutyEvents e where e.driver=:driver order by e.date desc")
	List<DutyEvents> getEventsByDriver(@Param("driver") Driver driver, Pageable pageable);

	@Query("select e from DutyEvents e join e.driver d where d.userName=:userName and e.date between :startPeriod and :endPeriod order by e.date asc")
	List<DutyEvents> getEventsForPeriod(@Param("userName") String userName, @Param("startPeriod") Date startPeriod,
			@Param("endPeriod") Date endPeriod);
}
