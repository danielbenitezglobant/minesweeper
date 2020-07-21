package com.danielbenitez.service.impl;

import com.danielbenitez.model.Board;
import com.danielbenitez.repository.BoardRepository;
import com.danielbenitez.service.MineSweeperService;
import com.danielbenitez.service.UserService;
import com.danielbenitez.viewmodel.BoardQueueViewModel;
import com.danielbenitez.viewmodel.BoardViewModel;
import com.danielbenitez.viewmodel.CellViewModel;
import com.danielbenitez.viewmodel.CellXYViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


@Service(value = "mineSweeperService")
public class MineSweeperServiceImpl implements MineSweeperService {

	private static final String SEPARATOR_CHAR = "\\*";
	CellViewModel[][] cells;
	@Autowired
	private UserService userService;
	@Autowired
	private BoardRepository boardRepository;

	@Override
	public boolean uncoverCell(String currentUser, int x, int y) {
		Queue<CellXYViewModel> auxQ;
		long userId = this.userService.getUserId();
		Board board = boardRepository.findByUserId(userId);
		cells = this.parseCells(board.getCells(), board.getRowsNumber(), board.getColumnsNumber());
		if(cells[x][y].getContent().equals("M")) {
			//game over
			return false;
		}
		CellXYViewModel cellToUncover = new CellXYViewModel(x,y);
		Queue<CellXYViewModel> q = new LinkedList<CellXYViewModel>();
		q.add(cellToUncover);
		while(!q.isEmpty()) {
			cellToUncover = q.element();
			q.remove();
			BoardQueueViewModel boardQueueViewModel = this.getCellsWithoutMines(cellToUncover, board.getRowsNumber(), board.getColumnsNumber());
			cells[cellToUncover.getX()][cellToUncover.getY()].setContent(String.valueOf(boardQueueViewModel.getNumberOfMinesAround()));
			if(boardQueueViewModel.getNumberOfMinesAround() == 0) {
				while (!boardQueueViewModel.getQ().isEmpty()) {
					q.add(boardQueueViewModel.getQ().element());
					boardQueueViewModel.getQ().remove();
				}
			}
		}
		String flattedCells = this.flatCells(cells);
		board.setCells(flattedCells);
		try {
			boardRepository.save(board);
		} catch (Exception e) {
			//logging and exception throwing to be implemented...
		}
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
		boardModel.setStatus("active");
		try {
			boardRepository.save(boardModel);
		} catch (Exception e) {
			//logging and exception throwing to be implemented...
		}

		return true;
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

	private String flatCells(CellViewModel[][] board) {
		String flatRow = "";
		String flatCell = "";
		String flatBoard = "";

		for (CellViewModel[] row : board) {
			flatRow = "";
			for (CellViewModel cell : row) {
				flatCell = cell.getContent() + cell.getMark() + SEPARATOR_CHAR;
				flatRow += flatCell;
			}
			flatBoard += flatRow + ",";
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

	private CellViewModel[][] parseCells(String cells, int rowsNumber, int columnsNumber) {

		CellViewModel[][] cellsViewModel = new CellViewModel[rowsNumber][columnsNumber];
		CellViewModel cellViewModel;
		String[] rows = cells.split(",");
		int i = 0;
		int j = 0;
		for(String row : rows) {
			String[] columns = row.split(SEPARATOR_CHAR);
			for (String column : columns) {
				cellViewModel = new CellViewModel();
				cellViewModel.setContent(column.substring(0,1));
				cellViewModel.setMark(column.substring(1,2));
				cellsViewModel[i][j] = cellViewModel;
				j++;
			}
			j = 0;
			i++;
		}
		return cellsViewModel;

	}

	private BoardQueueViewModel getCellsWithoutMines(CellXYViewModel cellToUncover, int rows, int columns) {
		int mines = 0;
		Queue<CellXYViewModel> q = new LinkedList<>();
		BoardQueueViewModel boardQueueViewModel = new BoardQueueViewModel();

		// North
		if (this.isValid(cellToUncover.getX()-1, cellToUncover.getY(), rows, columns))	{
			if (cells[cellToUncover.getX()-1][cellToUncover.getY()].getContent().equals("M")) {
				mines++;
			} else {
				if (cells[cellToUncover.getX()-1][cellToUncover.getY()].getContent().equals("C")) {
					q.add(new CellXYViewModel(cellToUncover.getX() - 1, cellToUncover.getY()));
				}
			}
		}

		// South
		if (this.isValid(cellToUncover.getX()+1, cellToUncover.getY(), rows, columns))	{
			if (cells[cellToUncover.getX()+1][cellToUncover.getY()].getContent().equals("M")) {
				mines++;
			} else {
				if (cells[cellToUncover.getX() + 1][cellToUncover.getY()].getContent().equals("C")) {
					q.add(new CellXYViewModel(cellToUncover.getX() + 1, cellToUncover.getY()));
				}
			}
		}

		// East
		if (this.isValid(cellToUncover.getX(), cellToUncover.getY() + 1, rows, columns))	{
			if (cells[cellToUncover.getX()][cellToUncover.getY()+1].getContent().equals("M")) {
				mines++;
			} else {
				if (cells[cellToUncover.getX()][cellToUncover.getY() + 1].getContent().equals("C")) {
					q.add(new CellXYViewModel(cellToUncover.getX(), cellToUncover.getY() + 1));
				}
			}
		}

		// West
		if (this.isValid(cellToUncover.getX(), cellToUncover.getY() - 1, rows, columns))	{
			if (cells[cellToUncover.getX()][cellToUncover.getY() - 1].getContent().equals("M")) {
				mines++;
			} else {
				if (cells[cellToUncover.getX()][cellToUncover.getY() - 1].getContent().equals("C")) {
					q.add(new CellXYViewModel(cellToUncover.getX(), cellToUncover.getY() - 1));
				}
			}
		}

		// North-East
		if (this.isValid(cellToUncover.getX() - 1, cellToUncover.getY() + 1, rows, columns))	{
			if (cells[cellToUncover.getX() - 1][cellToUncover.getY() + 1].getContent().equals("M")) {
				mines++;
			} else {
				if (cells[cellToUncover.getX() - 1][cellToUncover.getY() + 1].getContent().equals("C")) {
					q.add(new CellXYViewModel(cellToUncover.getX() - 1,cellToUncover.getY() + 1));
				}
			}
		}

		// North-West
		if (this.isValid(cellToUncover.getX() - 1, cellToUncover.getY() - 1, rows, columns))	{
			if (cells[cellToUncover.getX() - 1][cellToUncover.getY() - 1].getContent().equals("M")) {
				mines++;
			} else {
				if (cells[cellToUncover.getX() - 1][cellToUncover.getY() - 1].getContent().equals("C")) {
					q.add(new CellXYViewModel(cellToUncover.getX() - 1, cellToUncover.getY() - 1));
				}
			}
		}

		// South-East
		if (this.isValid(cellToUncover.getX() + 1, cellToUncover.getY() + 1, rows, columns))	{
			if (cells[cellToUncover.getX() + 1][cellToUncover.getY() + 1].getContent().equals("M")) {
				mines++;
			} else {
				if (cells[cellToUncover.getX() + 1][cellToUncover.getY() + 1].getContent().equals("C")) {
					q.add(new CellXYViewModel(cellToUncover.getX() + 1, cellToUncover.getY() + 1));
				}
			}
		}

		// South-West
		if (this.isValid(cellToUncover.getX() + 1, cellToUncover.getY() - 1, rows, columns))	{
			if (cells[cellToUncover.getX() + 1][cellToUncover.getY() - 1].getContent().equals("M")) {
				mines++;
			} else {
				if (cells[cellToUncover.getX() + 1][cellToUncover.getY() - 1].getContent().equals("C")) {
					q.add(new CellXYViewModel(cellToUncover.getX() + 1, cellToUncover.getY() - 1));
				}
			}
		}
		boardQueueViewModel.setNumberOfMinesAround(mines);
		boardQueueViewModel.setQ(q);

		return boardQueueViewModel;
	}

	private boolean isValid(int x, int y, int rows, int columns) {
		return x>=0 && x<rows && y>=0 && y<columns;
	}
}
