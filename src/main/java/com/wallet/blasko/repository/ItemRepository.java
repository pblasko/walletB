package com.wallet.blasko.repository;

import com.wallet.blasko.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i WHERE FUNCTION('YEAR', i.actualDate) = :year AND FUNCTION('MONTH', i.actualDate) = :month ORDER BY i.actualDate DESC")
    List<Item> findAllByYearAndMonth(@Param("year") int year, @Param("month") int month);

}
