package com.first.software_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.first.software_project.model.Faculty;
import com.first.software_project.model.Scheduled_class;
import java.util.List;


@Repository
public interface Scheduled_classrepo extends JpaRepository<Scheduled_class,Integer>{
    public List<Scheduled_class> findByDay(String day);
    public List<Scheduled_class> findByTeacher(Faculty teacher);
}
