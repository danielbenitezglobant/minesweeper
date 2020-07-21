package com.danielbenitez.controller;

import com.danielbenitez.model.Board;
import com.danielbenitez.service.MineSweeperService;
import com.danielbenitez.service.UserService;
import com.danielbenitez.viewmodel.BoardViewModel;
import com.danielbenitez.viewmodel.CellXYViewModel;
import com.danielbenitez.viewmodel.XYViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/minesweeper")
public class MineSweeperController {

    @Autowired
    private MineSweeperService mineSweeperService;
    @Autowired
    private UserService userService;

    @RequestMapping(value="/uncover-cell", method = RequestMethod.POST)
    public boolean uncoverCell(@RequestBody XYViewModel xYViewModel){
        final String currentUser = userService.getCurrentUser();

        return mineSweeperService.uncoverCell(currentUser, xYViewModel.getX(), xYViewModel.getY());
    }

    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public BoardViewModel board(){
        final String currentUser = userService.getCurrentUser();
        return mineSweeperService.getCurrentBoard(currentUser);
    }

    @RequestMapping(value="/mark-cell-question", method = RequestMethod.POST)
    public boolean markCellQuestion(@RequestBody int x, @RequestParam int y){
        final String currentUser = userService.getCurrentUser();
        return mineSweeperService.markCellQuestion(currentUser, x, y);
    }

    @RequestMapping(value="/mark-cell-red-flag", method = RequestMethod.POST)
    public boolean markCellRedFlag(@RequestBody int x, @RequestParam int y){
        final String currentUser = userService.getCurrentUser();
        return mineSweeperService.markCellRedFlag(currentUser, x, y);
    }

    @RequestMapping(value="/create-new-game", method = RequestMethod.POST)
    public boolean createNewGame(@RequestBody Board board){
        final String currentUser = userService.getCurrentUser();
        return mineSweeperService.createNewGame(currentUser, board.getRowsNumber(), board.getColumnsNumber(), board.getMines());
    }

    @RequestMapping(value="/resume-game", method = RequestMethod.POST)
    public boolean resumeGame(@RequestBody String saveGameId){
        final String currentUser = userService.getCurrentUser();
        return mineSweeperService.resumeGame(currentUser, saveGameId);
    }

    @RequestMapping(value = "/saved-games", method = RequestMethod.GET)
    public List<String> savedGames(){
        final String currentUser = userService.getCurrentUser();
        return mineSweeperService.getSavedGamesList(currentUser);
    }

}
