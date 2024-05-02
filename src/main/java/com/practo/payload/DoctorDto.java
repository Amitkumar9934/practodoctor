package com.practo.payload;

import com.practo.entity.Doctor;
import com.practo.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private Doctor doctor;
    private List<Review> reviews;
    private double ratingPercentage;
}
