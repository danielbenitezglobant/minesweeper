package com.danielbenitez.repository;

import com.danielbenitez.model.Board;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends CrudRepository<Board, Long> {

    @Query("select b from Board b where b.userId = :userId AND b.status = 'active'")
    Board findByUserId(@Param("userId") long userId);

}
