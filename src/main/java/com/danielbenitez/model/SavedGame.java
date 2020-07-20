package com.danielbenitez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class SavedGame {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column
    private long userId;
    @Column
    private String savedGameId;
    @Column
    private Timestamp startedDateTime;

}
