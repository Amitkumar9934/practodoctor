package com.practo.service.impl;

import com.practo.entity.Doctor;
import com.practo.repository.DoctorRepository;
import com.practo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Page<Doctor> getAllDoctors(Pageable pageable) {
        return doctorRepository.findAll(pageable);
    }

    @Override
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Optional<Doctor> updateDoctor(Long id, Doctor updatedDoctor) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            return Optional.empty();
        }

        Doctor doctor = optionalDoctor.get();
        doctor.setName(updatedDoctor.getName());
        doctor.setQualification(updatedDoctor.getQualification());
        doctor.setSpecialization(updatedDoctor.getSpecialization());
        doctor.setExperience(updatedDoctor.getExperience());
        doctor.setDescription(updatedDoctor.getDescription());
        doctor.setFee(updatedDoctor.getFee());

        Doctor savedDoctor = doctorRepository.save(doctor);
        return Optional.of(savedDoctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
    @Override
    public Page<Doctor> searchByNameOrSpecialization(String searchTerm, Pageable pageable) {
        return doctorRepository.searchByNameOrSpecialization(searchTerm, pageable);
    }
}
