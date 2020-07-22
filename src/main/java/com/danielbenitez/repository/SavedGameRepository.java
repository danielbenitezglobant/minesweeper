package com.danielbenitez.repository;

import com.danielbenitez.model.SavedGame;
import com.danielbenitez.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedGameRepository extends CrudRepository<SavedGame, Long> {

    List<SavedGame> findSavedGameByUserId(long userId);

    SavedGame findSavedGameByIdAndUserId(long id, long userId);
}
