package com.practo.service.impl;

import com.practo.entity.Patient;
import com.practo.repository.PatientRepository;
import com.practo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient createPatient(Patient patient){
        Patient savedPatient = patientRepository.save(patient);
        return savedPatient;
    }
}
