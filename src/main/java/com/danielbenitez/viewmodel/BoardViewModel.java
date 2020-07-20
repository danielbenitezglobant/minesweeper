package com.danielbenitez.viewmodel;

public class BoardViewModel {
    CellViewModel[][] board;
    String status;
    // anything else attribute necessary to represent the board


    public CellViewModel[][] getBoard() {
        return board;
    }

    public void setBoard(CellViewModel[][] board) {
        this.board = board;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
