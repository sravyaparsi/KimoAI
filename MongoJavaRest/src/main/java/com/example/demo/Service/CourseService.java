package com.example.demo.Service;

import com.example.demo.Dao.CourseDao;
import com.example.demo.Model.Content;
import com.example.demo.Model.CourseModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseDao course;
    public List<CourseModel> getCourses(@NotNull String attribute) {
        List<CourseModel> c =  null;
        switch (attribute){
            case "title":
                c = course.findAllByOrderByNameAsc();
                break;
            case "date":
                c = course.findAllByOrderByDateDesc();
                break;
            case "rating":
                c = course.findAllByOrderByRatingDesc();
                break;
        }
        return c;
    }

    public CourseModel getCourseByIndex(int id) {
        List<CourseModel> c =  course.findAll();
        return c.get(id);
    }

    public List<Content> getChapter(@NotNull CourseModel courseModel) {
        CourseModel c = course.findByName(courseModel.getName());
        return c.getChapters().stream().filter(x->x.getName().equals(courseModel.getChapters().get(0).getName())).collect(Collectors.toList());
    }

    public void addRating(CourseModel courseModel) {
        CourseModel c = course.findByName(courseModel.getName());
        c.setRating(c.getRating() + courseModel.getRating());
        course.save(c);
    }
}
