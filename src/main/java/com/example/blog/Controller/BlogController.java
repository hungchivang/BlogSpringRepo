package com.example.blog.Controller;

import com.example.blog.Model.Category;
import com.example.blog.Model.WriteBlog;
import com.example.blog.Service.BlogService;
import com.example.blog.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.getAll();
    }

    @GetMapping("/myHome")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("blog", blogService.showAll());
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        modelAndView.addObject("detail", blogService.findByID(id));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createBlog() {
        ModelAndView modelAndView = new ModelAndView("create");
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute WriteBlog blog, @RequestParam MultipartFile upImg, @RequestParam int IdCategory) {
        String nameFile = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File("C:\\Users\\hungchivang\\Desktop\\Moudle4\\Blog\\src\\main\\webapp\\WEB-INF\\image\\" + nameFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        blog.setImg("/image/" + nameFile);
        Category category = categoryService.findById(IdCategory);
        blog.setCategory(category);
        blogService.create(blog);
        return "redirect:/myHome";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        blogService.delete(id);
        return "redirect:/myHome";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("editBlog", blogService.findByID(id));
        return modelAndView;
    }

    @PostMapping("/edit")
    public String editBlogs(@ModelAttribute WriteBlog blog, @RequestParam MultipartFile upImg) {
        if(!upImg.isEmpty()){
            try {
                FileCopyUtils.copy(upImg.getBytes(), new File("C:\\Users\\hungchivang\\Desktop\\Moudle4\\Blog\\src\\main\\webapp\\WEB-INF" + blog.getImg()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        blogService.create(blog);
        String id = String.valueOf(blog.getId());
        return "redirect:/detail/" + id;
    }

}
