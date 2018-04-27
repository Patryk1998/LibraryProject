package com.library.mapper;

import com.sun.org.apache.bcel.internal.classfile.AttributeReader;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

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
