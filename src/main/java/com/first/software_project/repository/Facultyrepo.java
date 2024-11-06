package com.first.software_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.first.software_project.model.Faculty;

@Repository
public interface Facultyrepo extends JpaRepository<Faculty,Integer>{
    
}
