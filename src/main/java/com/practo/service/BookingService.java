package com.practo.service;

import com.practo.payload.BookingDto;

public interface BookingService {
    void bookAnAppointment(BookingDto bookingDto);
}
