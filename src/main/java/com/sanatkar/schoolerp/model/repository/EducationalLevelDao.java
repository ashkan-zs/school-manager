package com.sanatkar.schoolerp.model.repository;

import com.sanatkar.schoolerp.model.entity.EducationLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Create by ashkan on 2019/06/15
 */
@Repository
public interface EducationalLevelDao extends JpaRepository<EducationLevel, Long> {
}
