package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "PIECES")
public class Piece {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "PIECE_ID")
    private int pieceId;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TITLE_ID")
    private Title title;

    public Piece(String status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}
