package com.danielbenitez.service.impl;

import com.danielbenitez.repository.UserRepository;
import com.danielbenitez.service.MineSweeperService;
import com.danielbenitez.viewmodel.BoardViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service(value = "mineSweeperService")
public class MineSweeperServiceImpl implements MineSweeperService {
	
	@Autowired
	private UserRepository userRepository;

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
		//To be implemented...
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
}
