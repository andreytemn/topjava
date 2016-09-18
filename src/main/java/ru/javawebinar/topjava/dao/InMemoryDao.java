package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by andrey on 18.09.16.
 */
public class InMemoryDao implements Dao {
    private Map<Integer, Meal> map = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    public InMemoryDao() {
        int id = getNewId();
        map.put(id, new Meal(id, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        id = getNewId();
        map.put(id, new Meal(id, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        id = getNewId();
        map.put(id, new Meal(id, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        id = getNewId();
        map.put(id, new Meal(id, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        id = getNewId();
        map.put(id, new Meal(id, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        id = getNewId();
        map.put(id, new Meal(id, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public Meal get(int id) {
        return map.get(id);
    }

    @Override
    public Collection<MealWithExceed> getAll() {
        return MealsUtil.getFilteredWithExceeded((List<Meal>) map.values(), LocalTime.MIN, LocalTime.MAX, 2000);
    }

    @Override
    public void add(LocalDateTime ldt, String desc, int calories) {
        int newId = getNewId();
        Meal newMeal = new Meal(newId, ldt, desc, calories);
        map.put(newId, newMeal);
    }

    private int getNewId() {
        return counter.incrementAndGet();
    }

    @Override
    public void edit(int id, LocalDateTime ldt, String desc, int calories) {
        map.put(id, new Meal(id, ldt, desc, calories));
    }

    @Override
    public void delete(int id) {
        map.remove(id);
    }
}
