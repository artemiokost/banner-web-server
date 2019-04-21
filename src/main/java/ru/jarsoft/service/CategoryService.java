package ru.jarsoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.jarsoft.exception.BadRequestException;
import ru.jarsoft.model.Banner;
import ru.jarsoft.model.Category;
import ru.jarsoft.validation.CategoryRequest;
import ru.jarsoft.repository.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for {@link Banner}.
 *
 * @author Artem Kostritsa
 * @version 1.0
 */

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category create(CategoryRequest categoryRequest) {

        Category category = new Category()
                .setName(categoryRequest.getName())
                .setReqName(categoryRequest.getReqName());

        return categoryRepository.save(category);
    }

    public Boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    public Boolean existsByReqName(String reqName) {
        return categoryRepository.existsByReqName(reqName);
    }

    public List<Category> getList(){
        return categoryRepository.findAllByDeleted(false, Sort.by(Sort.Order.asc("name")));
    }

    @Transactional
    public Category updateById(int categoryId, CategoryRequest categoryRequest) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BadRequestException("Invalid categoryId"));

        List<Banner> bannerList = category.getBannerList().stream()
                .filter(banner -> !banner.isDeleted()).collect(Collectors.toList());

        if (bannerList.isEmpty()) category.setDeleted(categoryRequest.isDeleted());
        category.setName(categoryRequest.getName()).setReqName(categoryRequest.getReqName());

        return categoryRepository.save(category);
    }
}
