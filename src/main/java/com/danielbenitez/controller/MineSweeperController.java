package com.danielbenitez.controller;

import com.danielbenitez.model.Board;
import com.danielbenitez.service.MineSweeperService;
import com.danielbenitez.service.UserService;
import com.danielbenitez.viewmodel.BoardViewModel;
import com.danielbenitez.viewmodel.SavedGameViewModel;
import com.danielbenitez.viewmodel.XYViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.danielbenitez.exception.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;

@RestController
@RequestMapping("/minesweeper")
public class MineSweeperController {

    @Autowired
    private MineSweeperService mineSweeperService;
    @Autowired
    private UserService userService;

    Logger logger = LogManager.getLogger(LoggingController.class);

    @RequestMapping(value="/uncover-cell", method = RequestMethod.POST)
    public ResponseEntity<String> uncoverCell(@RequestBody XYViewModel xYViewModel){

        try {
            final String currentUser = userService.getCurrentUser();
            this.mineSweeperService.uncoverCell(currentUser, xYViewModel.getX(), xYViewModel.getY());
            return ResponseEntity.ok("Cell "+ xYViewModel.getX()  +" " + xYViewModel.getY() + " uncovered");
        }
        catch (Exception exc) {
            throw new MyResourceNotFoundException(
                     "Cell Not Found");
        }
    }

    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public BoardViewModel board(){
        try {
            final String currentUser = userService.getCurrentUser();
            return mineSweeperService.getCurrentBoard(currentUser);
        }
        catch (Exception exc) {
        throw new MyResourceNotFoundException(
                "Board Not Found");
        }
    }

    @RequestMapping(value="/mark-cell-question", method = RequestMethod.POST)
    public ResponseEntity<String> markCellQuestion(@RequestBody XYViewModel xYViewModel){
        try {
            final String currentUser = userService.getCurrentUser();
            mineSweeperService.markCellQuestion(currentUser, xYViewModel.getX(), xYViewModel.getY());
            return ResponseEntity.ok("Cell " + xYViewModel.getX() + " " + xYViewModel.getY() + " question marked");
        }
        catch (Exception exc) {
            throw new MyResourceNotFoundException(
                 "Cell Not Found");
        }
    }

    @RequestMapping(value="/mark-cell-red-flag", method = RequestMethod.POST)
    public ResponseEntity<String> markCellRedFlag(@RequestBody XYViewModel xYViewModel){
        try {
            final String currentUser = userService.getCurrentUser();
            mineSweeperService.markCellRedFlag(currentUser, xYViewModel.getX(), xYViewModel.getY());
            return ResponseEntity.ok("Cell " + xYViewModel.getX() + " " + xYViewModel.getY() + " red flag marked");
        }
        catch (Exception exc) {
            throw new MyResourceNotFoundException(
                "Cell Not Found");
        }
    }

    @RequestMapping(value="/create-new-game", method = RequestMethod.POST)
    public ResponseEntity<String> createNewGame(@RequestBody Board board){
        try {
            final String currentUser = userService.getCurrentUser();
            mineSweeperService.createNewGame(currentUser, board.getRowsNumber(), board.getColumnsNumber(), board.getMines());
            return ResponseEntity.ok("New game created.");
        }
        catch (MyResourceNotFoundException exc) {
            throw new MyResourceNotFoundException(
                "Error creating new game");
        }
    }

    @RequestMapping(value="/resume-game", method = RequestMethod.POST)
    public ResponseEntity<String> resumeGame(@RequestBody Long id){
        try {
            final String currentUser = userService.getCurrentUser();
            mineSweeperService.resumeGame(currentUser, id);
            return ResponseEntity.ok("Game with id: " + id + " resumed" );
        }
        catch (MyResourceNotFoundException exc) {
            throw new MyResourceNotFoundException(
                 "Saved Game Not Found");
        }
    }

    @RequestMapping(value = "/saved-games", method = RequestMethod.GET)
    public List<SavedGameViewModel> savedGames(){
        try {
            final String currentUser = userService.getCurrentUser();
            return mineSweeperService.getSavedGamesList(currentUser);
        }
        catch (Exception exc) {
            throw new MyResourceNotFoundException(
                "Something goes wrong. Please contact to some Deviget guru");
        }
    }

    @RequestMapping(value="/game", method = RequestMethod.PUT)
    public ResponseEntity<String> saveGame(@RequestBody String saveGameId){
        try {
            final String currentUser = userService.getCurrentUser();
            mineSweeperService.saveGame(currentUser, saveGameId);
            return ResponseEntity.ok("Game " + saveGameId + " saved");
        }
        catch (Exception exc) {
            throw new MyResourceNotFoundException(
                 "Error to persist current game. Please cross your fingers and try again");
        }
    }

}
