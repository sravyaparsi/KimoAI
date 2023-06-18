package com.example.demo.Dao;

import com.example.demo.Model.CourseModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao  extends MongoRepository<CourseModel, Integer> {
    public  List<CourseModel> findAllByOrderByNameAsc();

    public  List<CourseModel> findAllByOrderByDateDesc();

    CourseModel findByName(String title);

    List<CourseModel> findAllByOrderByRatingDesc();
}
