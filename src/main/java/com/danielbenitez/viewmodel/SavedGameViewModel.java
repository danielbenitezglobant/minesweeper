package com.danielbenitez.viewmodel;

public class SavedGameViewModel {
    long id;
    String savedGameId;

    public SavedGameViewModel(long id, String savedGameId) {
        this.id = id;
        this.savedGameId = savedGameId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSavedGameId() {
        return savedGameId;
    }

    public void setSavedGameId(String savedGameId) {
        this.savedGameId = savedGameId;
    }
}
