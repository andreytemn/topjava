package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
public class InMemoryMealRepositoryImpl implements MealRepository {
//    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
//
//    {
//        MealsUtil.MEALS.forEach(this::save);
//    }

    @Override
    public Meal save(int userId, Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        Map<Integer, Meal> orDefault = repository.getOrDefault(userId, new ConcurrentHashMap<>());
        orDefault.put(meal.getId(), meal);
        repository.put(userId, orDefault);
        return meal;
    }

    @Override
    public void delete(int userId, int id) {
        repository.get(userId).remove(id);
    }

    @Override
    public Meal get(int userId, int id) {
        return repository.get(userId).get(id);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.get(userId).values().stream().sorted(new Comparator<Meal>() {
            @Override
            public int compare(Meal meal, Meal t1) {
                if (meal.getDateTime().isAfter(t1.getDateTime())) return 1;
                if (meal.getDateTime().isEqual(t1.getDateTime())) return 0;
                return -1;
            }
        }).collect(Collectors.toList());
    }
}

