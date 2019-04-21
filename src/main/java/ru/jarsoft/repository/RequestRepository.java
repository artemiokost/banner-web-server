package ru.jarsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jarsoft.model.Request;

public interface RequestRepository extends JpaRepository<Request, Integer> {

}
