package com.software.room_allocation.repository;

import com.software.room_allocation.model.Room_allocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomAllocationRepository extends JpaRepository<Room_allocation, Integer> {
}
