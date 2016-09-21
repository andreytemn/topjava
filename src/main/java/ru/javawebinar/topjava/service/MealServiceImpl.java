package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class MealServiceImpl implements MealService {
    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(int userId, Meal meal) {
        return repository.save(userId, meal);
    }

    @Override
    public void delete(int userId, int id) {
        try {
            repository.delete(userId, id);
        } catch (NullPointerException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public Meal get(int userId, int id) {
        try {
            return repository.get(userId, id);
        } catch (NullPointerException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public List<Meal> getAll(int userId) {
        try {
            return (List<Meal>) repository.getAll(userId);
        } catch (NullPointerException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public void update(int userId, Meal meal) {
        try {
            repository.save(userId, meal);
        } catch (NullPointerException e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
