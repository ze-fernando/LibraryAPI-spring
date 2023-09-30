package com.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Data
@AllArgsConstructor
public class RentJson {
    String message;
    LocalDateTime rentDate;
    LocalDateTime returnDate;
}
