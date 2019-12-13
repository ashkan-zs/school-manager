package com.sanatkar.schoolerp.model.repository;

import com.sanatkar.schoolerp.model.entity.Employee;
import com.sanatkar.schoolerp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Create by ashkan on 2019/06/15
 */
@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {

    Employee findByUser(User user);
}
