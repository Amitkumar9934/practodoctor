package com.practo.service;

import com.practo.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DoctorService {

    Page<Doctor> getAllDoctors(Pageable pageable);

    Optional<Doctor> getDoctorById(Long id);

    Doctor addDoctor(Doctor doctor);

    Optional<Doctor> updateDoctor(Long id, Doctor updatedDoctor);

    void deleteDoctor(Long id);

    Page<Doctor> searchByNameOrSpecialization(String searchTerm, Pageable pageable);
}
