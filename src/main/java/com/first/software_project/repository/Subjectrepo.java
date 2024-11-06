package com.first.software_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.first.software_project.model.Subjects;

@Repository
public interface Subjectrepo extends JpaRepository<Subjects,Integer>{

}
