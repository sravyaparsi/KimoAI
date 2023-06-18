package com.example.demo.controller;

import com.example.demo.Model.CourseModel;
import com.example.demo.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class Courses {

    @Autowired
    private CourseService courseService;

    //Endpoint to get courses order by either name, date or rating
    @GetMapping(value ="/sort")
    public ResponseEntity<List> getCoursesOrderByName(@RequestParam String attribute) {
        List body = courseService.getCourses(attribute);
       return new ResponseEntity<>(body, HttpStatus.OK);
    }

    //Endpoint to get course Information
    @GetMapping(value = "/{id}")
    public ResponseEntity<CourseModel> getCourse(@PathVariable int id) {
        CourseModel courseModel = courseService.getCourseByIndex(id);
        return new ResponseEntity<>(courseModel, HttpStatus.OK);
    }

    //Endpoint to get chapter information
    @PostMapping(value = "/chapters")
    public ResponseEntity<CourseModel> getChapter(@RequestBody CourseModel courseModel) {
         courseService.getChapter(courseModel);
        return new ResponseEntity<>(courseModel, HttpStatus.OK);
    }

    //Endpoint to allow users to add rating

    @PostMapping(value = "/rate")
    public ResponseEntity<CourseModel> addRating(@RequestBody CourseModel courseModel) {
        courseService.addRating(courseModel);
        return new ResponseEntity<>(courseModel, HttpStatus.OK);
    }
}

