package com.software.room_allocation.service;

import com.software.room_allocation.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultyService {
    @Autowired
    FacultyRepository facultyRepo;

}
