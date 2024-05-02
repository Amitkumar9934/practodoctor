package com.practo.service.impl;

import com.practo.entity.Doctor;
import com.practo.entity.Patient;
import com.practo.entity.Review;
import com.practo.exception.EntityNotFoundException;
import com.practo.repository.DoctorRepository;
import com.practo.repository.PatientRepository;
import com.practo.repository.ReviewRepository;
import com.practo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public Review createReview(Review review,long doctorId,long patientId){
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with ID " + doctorId + " not found"));

        //review.setDoctor(doctor);
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient with ID " + patientId + " not found"));
        //review.setPatient(patient);

        Review savedReview=null;
        if(doctor!=null || patient!=null){
            review.setDoctorId(doctorId);
            review.setPatientId(patientId);
            savedReview = reviewRepository.save(review);
        }

        // Save the review
        return savedReview;
    }
    public List<Review> getReviewByDoctorId(long doctorId) {
        List<Review> reviews = reviewRepository.findByDoctorId(doctorId);
        if (reviews.isEmpty()) {
            throw new EntityNotFoundException("Doctor with ID " + doctorId + " not found");
        }
        return reviews;
    }

}
