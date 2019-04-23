package ru.jarsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.jarsoft.model.Category;
import ru.jarsoft.validation.CategoryRequest;
import ru.jarsoft.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

/**
 * Banner controller.
 *
 * @author Artem Kostritsa
 * @version 1.0
 */

@RestController
@RequestMapping()
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/exists/category", params = "name")
    public Boolean existsByName(@RequestParam(value = "name") String name) {
        return categoryService.existsByName(name);
    }

    @GetMapping(value = "/exists/category", params = "reqName")
    public Boolean existsByReqName(@RequestParam(value = "reqName") String reqName) {
        return categoryService.existsByReqName(reqName);
    }

    @GetMapping(value = "/list/category")
    public List<Category> getList() {
        return categoryService.getList();
    }

    @PostMapping(value = "/category")
    public Category create(@Valid @RequestBody CategoryRequest categoryRequest) {
        return categoryService.create(categoryRequest);
    }

    @PutMapping(value = "/category")
    public Category updateById(@RequestParam(value = "categoryId") int categoryId,
                         @Valid @RequestBody CategoryRequest categoryRequest) {
        System.out.println(categoryRequest.isDeleted());
        return categoryService.updateById(categoryId, categoryRequest);
    }
}
