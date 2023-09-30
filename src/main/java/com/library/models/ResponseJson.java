package com.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@Value
public class ResponseJson {

    static DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static LocalDateTime date = LocalDateTime.now();
    static String dateRented = date.format(formatters);
    static String dateReturn = date.plusDays(10).format(formatters);

    public static ResponseEntity<Object> json(String message, HttpStatus status, Object responseObj){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);

        return new ResponseEntity<Object>(map,status);
    }

    public static ResponseEntity<Object> message(String message, HttpStatus status){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());

        return new ResponseEntity<Object>(map,status);
    }

    public static ResponseEntity<Object> rented(String message, HttpStatus status){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("rented", dateRented);
        map.put("term", dateReturn);
        map.put("status", status.value());

        return new ResponseEntity<Object>(map,status);
    }
}
