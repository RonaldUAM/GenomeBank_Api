package com.breaze.genomebank.function.infraestructure.repository;

import com.breaze.genomebank.function.entities.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFunctionRepository extends JpaRepository<Function,Integer> {
}
