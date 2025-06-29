package com.software.room_allocation.repository;

import com.software.room_allocation.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    public Faculty findFacultyByDepartmentAndHod(String dept, boolean i);

    @Modifying
    @Query("update Faculty set hod=true where faculty_id=?1")
    public void assignHod(int id);

    @Modifying
    @Query("update Faculty set hod=false where department=?1")
    public void removeHod(String dept);
}
