package org.aspasibu.logitest.repository;

import org.aspasibu.logitest.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Query("select d from Driver d where d.name = :name")
    Driver findByName(@Param("name")String name);

    @Query("select d from Driver d where d.userName = :userName and d.password=:password")
    Driver checkNameAndPass(@Param("userName")String userName, @Param("password")String password);
    
    @Query("select d from Driver d where d.userName = :userName")
    Driver findByUserName(@Param("userName")String userName);
}
