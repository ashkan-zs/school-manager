package com.sanatkar.schoolerp.model.repository;

import com.sanatkar.schoolerp.model.entity.CourseLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Create by ashkan on 2020/01/05
 */
@Repository
public interface CourseLevelDao extends JpaRepository<CourseLevel, Long> {
}
