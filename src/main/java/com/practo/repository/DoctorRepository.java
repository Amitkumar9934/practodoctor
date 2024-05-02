package com.practo.repository;

import com.practo.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("SELECT d FROM Doctor d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(d.specialization) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Doctor> searchByNameOrSpecialization(@Param("searchTerm") String searchTerm, Pageable pageable);
}
