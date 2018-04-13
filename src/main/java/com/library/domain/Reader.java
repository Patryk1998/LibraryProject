package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "READERS")
public class Reader {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "READER_ID")
    private int readerId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "REGISTRATION_DATE")
    private String regDate;
}
