package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> filteredWithExceeded = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        for (UserMealWithExceed userMealWithExceed : filteredWithExceeded)
        {
            System.out.println(userMealWithExceed);
        }

//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime,
                                                                   LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> list = new ArrayList<>();
        Map<LocalDate, Integer> exceedingMap = new HashMap<>();
        mealList.forEach(s -> {
            if (!exceedingMap.containsKey(s.getDateTime().toLocalDate())) {
                exceedingMap.put(s.getDateTime().toLocalDate(), s.getCalories());
            } else {
                exceedingMap.put(s.getDateTime().toLocalDate(),
                        exceedingMap.get(s.getDateTime().toLocalDate()) + s.getCalories());
            }
        });
        mealList.stream().filter(s -> s.getDateTime().toLocalTime().isAfter(startTime) &&
                s.getDateTime().toLocalTime().isBefore(endTime))
                .collect(Collectors.toList())
                .forEach(s -> {
                    boolean exceeded = (exceedingMap.get(s.getDateTime().toLocalDate()) > caloriesPerDay);
                    list.add(new UserMealWithExceed(s.getDateTime(), s.getDescription(), s.getCalories(), exceeded));
                });
        return list;
    }
}
