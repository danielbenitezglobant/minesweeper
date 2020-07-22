package com.danielbenitez.repository;

import com.danielbenitez.model.Board;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BoardRepository extends CrudRepository<Board, Long> {

    @Query("select b from Board b where b.userId = :userId AND b.status = 'active'")
    Board findByUserIdActiveStatus(@Param("userId") long userId);

    @Query("select b from Board b where b.userId = :userId")
    Board findByUserId(@Param("userId") long userId);

    @Query("select b from Board b where b.userId = :userId AND b.id = :id")
    Board findByIdAndUserId(@Param("id") long od, @Param("userId") long userId);

    @Transactional
    @Modifying
    @Query("update Board b set b.status = 'inactive' where b.userId = :userId")
    void setAsInactiveAllCurrentUserBoard(@Param("userId") long userId);
}
