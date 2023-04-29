package com.nicogmerz4.portfolio.repository;

import com.nicogmerz4.portfolio.model.Academy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademyRepository extends JpaRepository<Academy, Long>{
    
}
