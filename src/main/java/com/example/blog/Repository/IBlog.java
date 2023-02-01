package com.example.blog.Repository;

import com.example.blog.Model.WriteBlog;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBlog extends PagingAndSortingRepository<WriteBlog,Long> {
}
