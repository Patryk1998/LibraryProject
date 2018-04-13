package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "TITLES")
public class Title {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "TITLE_ID")
    private int titleId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "SPEND_DATE")
    private String spendDate;

    @OneToMany(
            targetEntity = Piece.class,
            mappedBy = "title",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Piece> pieces = new ArrayList<>();

    public Title(String title, String author, String spendDate) {
        this.title = title;
        this.author = author;
        this.spendDate = spendDate;
    }
}
