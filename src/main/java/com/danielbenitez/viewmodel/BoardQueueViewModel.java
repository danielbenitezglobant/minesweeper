package com.danielbenitez.viewmodel;

import java.util.Queue;

public class BoardQueueViewModel {
    Queue<CellXYViewModel> q;
    int numberOfMinesAround;

    public Queue<CellXYViewModel> getQ() {
        return q;
    }

    public void setQ(Queue<CellXYViewModel> q) {
        this.q = q;
    }

    public int getNumberOfMinesAround() {
        return numberOfMinesAround;
    }

    public void setNumberOfMinesAround(int numberOfMinesAround) {
        this.numberOfMinesAround = numberOfMinesAround;
    }
}
