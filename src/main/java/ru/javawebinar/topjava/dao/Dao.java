package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by andrey on 18.09.16.
 */
public interface Dao {
    Meal get(int id);

    Collection<MealWithExceed> getAll();

    void add(LocalDateTime ldt, String desc, int calories);

    void edit(int id, LocalDateTime ldt, String desc, int calories);

    void delete(int id);
}
