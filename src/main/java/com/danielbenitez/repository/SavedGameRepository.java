package com.danielbenitez.repository;

import com.danielbenitez.model.SavedGame;
import com.danielbenitez.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedGameRepository extends CrudRepository<SavedGame, Long> {

}
