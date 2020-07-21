package com.danielbenitez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column
    private long userId;
    @Column
    private int rowsNumber;
    @Column
    private int columnsNumber;
    @Column
    private String cells;
    @Column
    private int mines;
    @Column
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getRowsNumber() {
        return rowsNumber;
    }

    public void setRowsNumber(int rowsNumber) {
        this.rowsNumber = rowsNumber;
    }

    public int getColumnsNumber() {
        return columnsNumber;
    }

    public void setColumnsNumber(int columnsNumber) {
        this.columnsNumber = columnsNumber;
    }

    public String getCells() {
        return cells;
    }

    public void setCells(String cells) {
        this.cells = cells;
    }

    public int getMines() {
        return mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
