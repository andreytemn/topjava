package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface MealService {
    Meal save(int userId, Meal meal);

    void delete(int userId, int id);

    Meal get(int userId, int id);

    List<Meal> getAll(int userId);

    void update(int userId, Meal meal);
}
