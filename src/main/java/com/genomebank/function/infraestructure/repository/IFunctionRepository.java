package com.genomebank.function.infraestructure.repository;

import com.genomebank.function.entities.Category;
import com.genomebank.function.entities.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFunctionRepository extends JpaRepository<Function,String> {
    List<Function> findByCodeAndCategory(String code, Category category);
}
