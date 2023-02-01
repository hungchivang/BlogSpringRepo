package com.example.blog.Service;

import com.example.blog.Model.Category;
import com.example.blog.Repository.ICategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    ICategory iCategory;

    public List<Category> getAll(){
        return (List<Category>) iCategory.findAll();
    }

    public Category findById(int id){
        return iCategory.findById(id).get();
    }
}
