package com.practo.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private long doctorId;
    private long patientId;

    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate bDate;
    private String bookingTime;
}
