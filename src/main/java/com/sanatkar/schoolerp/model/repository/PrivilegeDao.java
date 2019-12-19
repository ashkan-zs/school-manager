package com.sanatkar.schoolerp.model.repository;

import com.sanatkar.schoolerp.model.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Create by ashkan on 2019/12/18
 */
@Repository
public interface PrivilegeDao extends JpaRepository<Privilege, Long> {
    Privilege findByName(String name);
}
