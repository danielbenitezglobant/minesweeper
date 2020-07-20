package com.danielbenitez.repository;

import com.danielbenitez.model.Board;
import com.danielbenitez.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends CrudRepository<Board, Long> {

}
