package com.sanatkar.schoolerp.model.repository;

import com.sanatkar.schoolerp.model.entity.ClassRoom;
import com.sanatkar.schoolerp.model.entity.ClassTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by ashkan on 2019/06/15
 */
@Repository
public interface ClassRoomDao extends JpaRepository<ClassRoom, Long> {
}
