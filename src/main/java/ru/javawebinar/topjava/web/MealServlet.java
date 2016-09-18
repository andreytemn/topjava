package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.dao.InMemoryDao;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created by andrey on 14.09.16.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    private Dao dao = new InMemoryDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "edit": {
                resp.sendRedirect("meal.jsp");
            }
            case "delete": {
                String id = req.getParameter("id");
                dao.delete(Integer.parseInt(id));
                req.getRequestDispatcher("mealList.jsp").forward(req, resp);
            }
            case "create": {
                resp.sendRedirect("meal.jsp");
            }
            default: {
                LOG.debug("forward meal list to mealList.jsp");
                Collection<MealWithExceed> mealWithExceeds = dao.getAll();
                req.setAttribute("list", mealWithExceeds);
                req.getRequestDispatcher("mealList.jsp").forward(req, resp);
            }
        }
    }
}
