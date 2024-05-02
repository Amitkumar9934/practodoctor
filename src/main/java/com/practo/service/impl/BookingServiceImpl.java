package com.practo.service.impl;

import com.practo.entity.Booking;
import com.practo.payload.BookingDto;
import com.practo.repository.BookingRepository;
import com.practo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    private Set<String> bookedTimeSlots = new HashSet<>();

    public void bookAnAppointment(BookingDto dto) {
        // Available time slots
        Set<String> availableTimeSlots = new HashSet<>();
        availableTimeSlots.add("10:15 AM");
        availableTimeSlots.add("11:15 AM");
        availableTimeSlots.add("12:15 PM");

        // Create a new Booking object
        Booking booking = new Booking();

        // Set the booking date
        booking.setBDate(dto.getBDate());

        // Flag to check if booking is successful
        boolean isBookingSuccessful = false;

        // Check if the provided time slot is available
        if (booking.getBDate().isAfter(LocalDate.now()) && availableTimeSlots.contains(dto.getBookingTime())) {
            // Set the booking time
            booking.setBookingTime(dto.getBookingTime());
            // Add the booked slot to the set of bookedTimeSlots
            bookedTimeSlots.add(dto.getBookingTime());
            // Set flag to true
            isBookingSuccessful = true;
        }

        //Schedule the task to run every 24 hours
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            System.out.println("Executing code every 24 hours....");
            // Reset available time slots
            availableTimeSlots.clear();
            availableTimeSlots.add("10:15 AM");
            availableTimeSlots.add("11:15 AM");
            availableTimeSlots.add("12:15 PM");
        }, 0, 24, TimeUnit.HOURS);

        // Set doctor and patient IDs
        booking.setDoctorId(dto.getDoctorId());
        booking.setPatientId(dto.getPatientId());

        // Check if booking time is set before saving
        if (isBookingSuccessful) {
            bookingRepository.save(booking);
            System.out.println("Booking confirmed for Doctor ID: " + dto.getDoctorId() +
                    ", Patient ID: " + dto.getPatientId() +
                    " at " + booking.getBookingTime());
        } else {
            System.out.println("Time Slot is not available");
        }
    }
}
