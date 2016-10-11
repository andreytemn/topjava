package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * gkislin
 * 02.10.2016
 */
@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user_id=:userId")
    int delete(@Param("id") int id, int userId);

    @Transactional
    Meal save(Meal meal, int userId);

    @Query("SELECT m FROM Meal m WHERE m.id=:id AND m.userId=:userId")
    Meal findOne(Integer id, int userId);

    @Query("SELECT m FROM Meal m WHERE m.user_id=:userId")
    List<Meal> findAll(int userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC")
    List<Meal> findBetween(int userId, LocalDateTime start, LocalDateTime end);
}
