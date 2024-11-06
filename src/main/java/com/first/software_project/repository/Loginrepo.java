package com.first.software_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.first.software_project.model.Login_details;

@Repository
public interface Loginrepo extends JpaRepository<Login_details,Integer>{

}
