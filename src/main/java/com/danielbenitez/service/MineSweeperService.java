package com.danielbenitez.service;

import com.danielbenitez.viewmodel.BoardViewModel;
import com.danielbenitez.viewmodel.SavedGameViewModel;

import java.util.List;

public interface MineSweeperService {


    boolean uncoverCell(String currentUser, int x, int y);

    BoardViewModel getCurrentBoard(String currentUser);

    boolean markCellQuestion(String currentUser, int x, int y);

    boolean markCellRedFlag(String currentUser, int x, int y);

    boolean createNewGame(String currentUser, int rows, int columns, int mines);

    boolean resumeGame(String currentUser, long saveGameId);

    List<SavedGameViewModel> getSavedGamesList(String currentUser);

    boolean saveGame(String currentUser, String saveGameId);
}
