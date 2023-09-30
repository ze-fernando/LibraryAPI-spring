package com.library.domain;

import com.library.Enums.Type;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class People {
    String name;
    @Enumerated(EnumType.STRING)
    Type type;
}
