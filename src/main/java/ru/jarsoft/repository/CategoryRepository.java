package ru.jarsoft.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.jarsoft.model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Boolean existsByName(String name);

    Boolean existsByReqName(String reqName);

    List<Category> findAllByDeleted(Boolean bool, Sort sort);
}
