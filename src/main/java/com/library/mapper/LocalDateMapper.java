package com.library.mapper;

import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.sql.Date;
import java.time.LocalDate;

@Component
public class LocalDateMapper implements AttributeConverter<LocalDate, Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDate attribute) {
        return new Date(Date.valueOf(attribute).getTime());
    }

    @Override
    public LocalDate convertToEntityAttribute(Date dbData) {
        return dbData.toLocalDate();
    }
}
