package com.nicogmerz4.portfolio.repository;

import com.nicogmerz4.portfolio.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
}
