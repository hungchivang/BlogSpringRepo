package com.example.blog.Service;

import com.example.blog.Model.WriteBlog;
import com.example.blog.Repository.IBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    IBlog iBlog;

    public List<WriteBlog> showAll(){
        return (List<WriteBlog>) iBlog.findAll();
    }
    public void create (WriteBlog blog){
        iBlog.save(blog);
    }

    public void delete(long id){
        iBlog.deleteById(id);
    }

    public WriteBlog findByID(long id){
        return iBlog.findById(id).get();
    }


}
