package com.practo.service;

import com.practo.entity.Review;

import java.util.List;

public interface ReviewService {
    Review createReview(Review review, long doctorId, long patientId);

    List<Review> getReviewByDoctorId(long doctorId);
}
