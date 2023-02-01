package com.example.blog.Repository;

import com.example.blog.Model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICategory extends PagingAndSortingRepository<Category,Integer> {
}
