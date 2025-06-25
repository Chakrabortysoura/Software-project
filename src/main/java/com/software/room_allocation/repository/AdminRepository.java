package com.software.room_allocation.repository;

import com.software.room_allocation.model.Administration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Administration,Integer> {
}
