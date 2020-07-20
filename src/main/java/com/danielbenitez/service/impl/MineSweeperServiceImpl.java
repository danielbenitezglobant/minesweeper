package com.danielbenitez.service.impl;

import com.danielbenitez.model.Board;
import com.danielbenitez.repository.BoardRepository;
import com.danielbenitez.repository.UserRepository;
import com.danielbenitez.service.MineSweeperService;
import com.danielbenitez.service.UserService;
import com.danielbenitez.viewmodel.BoardViewModel;
import com.danielbenitez.viewmodel.CellViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


@Service(value = "mineSweeperService")
public class MineSweeperServiceImpl implements MineSweeperService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BoardRepository boardRepository;

	@Override
	public boolean uncoverCell(String currentUser, int x, int y) {
		//To be implemented...
		return true;
	}

	@Override
	public BoardViewModel getCurrentBoard(String currentUser) {
		//To be implemented...
		return new BoardViewModel();
	}

	@Override
	public boolean markCellQuestion(String currentUser, int x, int y){
		//To be implemented...
		return true;
	}

	@Override
	public boolean markCellRedFlag(String currentUser, int x, int y) {
		//To be implemented...
		return true;
	}

	@Override
	public boolean createNewGame(String currentUser, int rows, int columns, int mines) {
		// C is covered
		// U is uncovered
		// M is mine
		// E is empty
		// ? is mark of question sign
		// F is mark if red flag
		// - is no mark currently
		// all Cells on String[][] are represented for a two-letter-string
		// first letter it's about Covered/Uncovered/Mine/Empty
		// second letter it's about mark: Question/Red Flag/No mark

		long userId = this.userService.getUserId();
		BoardViewModel board = new BoardViewModel();
		CellViewModel[][] cells = new CellViewModel[rows][columns];
		board.setBoard(this.initCells(cells, mines, rows, columns));
		String flattedCells = this.flatCells(board.getBoard());
		Board boardModel = new Board();
		boardModel.setCells(flattedCells);
		boardModel.setRowsNumber(rows);
		boardModel.setColumnsNumber(columns);
		boardModel.setMines(mines);
		boardModel.setUserId(userId);
		try {
			boardRepository.save(boardModel);
		}catch (Exception e) {
			//logging and exception throwing to be implemented...
		}

		return true;
	}

	private String flatCells(CellViewModel[][] board) {
		String flatRow = "";
		String flatCell = "";
		String flatBoard = "";

		for (CellViewModel[] row : board) {
			flatRow = "";
			for (CellViewModel cell : row) {
				flatCell = cell.getContent() + cell.getMark();
				flatRow += flatCell;
			}
			flatBoard += flatRow;
		}
		return flatBoard;
	}

	private CellViewModel[][] initCells(CellViewModel[][] cells, int mines, int rows, int columns) {
		Set<String> cellSet = new HashSet<String>();
		String stringCell;
		CellViewModel cell = new CellViewModel();
		cell.setContent("C");
		cell.setMark("-");
		for (CellViewModel[] row: cells)
			Arrays.fill(row, cell);
		while (mines > 0) {
			int randomRow = ThreadLocalRandom.current().nextInt(0, rows);
			int randomColumn = ThreadLocalRandom.current().nextInt(0, columns);
			stringCell = String.valueOf(randomRow) + String.valueOf(randomColumn);
			if(!cellSet.contains(stringCell)) {
				cellSet.add(stringCell);
				cell = new CellViewModel();
				cell.setContent("M");
				cell.setMark("-");
				cells[randomRow][randomColumn] = cell;
				mines--;
			}
		}
		return cells;
	}

	@Override
	public boolean resumeGame(String currentUser, String saveGameId) {
		//To be implemented...
		return true;
	}

	@Override
	public List<String> getSavedGamesList(String currentUser) {
		//To be implemented...
		return new ArrayList<String>();
	}
}
