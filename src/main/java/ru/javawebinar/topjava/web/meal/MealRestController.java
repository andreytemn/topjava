package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {
    @Autowired
    private MealService service;

    private User user;

    public List<MealWithExceed> getAll() {
        return MealsUtil.getFilteredWithExceeded(service.getAll(user.getId()), LocalTime.MIN, LocalTime.MAX, user.getCaloriesPerDay());
    }

    public List<MealWithExceed> getFiltered(LocalTime start, LocalTime end) {
        return MealsUtil.getFilteredWithExceeded(service.getAll(user.getId()), start, end, user.getCaloriesPerDay());
    }

    public void delete(int id) {
        service.delete(user.getId(), id);
    }

    public void save(int id, LocalDateTime ldt, String description, int calories) {
        service.save(user.getId(), new Meal(id, ldt, description, calories));
    }

    public void update(int id, LocalDateTime ldt, String description, int calories) {
        service.update(user.getId(), new Meal(id, ldt, description, calories));
    }

}
