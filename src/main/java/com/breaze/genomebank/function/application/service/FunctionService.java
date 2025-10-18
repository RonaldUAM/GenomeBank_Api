package com.breaze.genomebank.function.application.service;

import com.breaze.genomebank.function.application.ports.IFunctionService;
import com.breaze.genomebank.function.infraestructure.repository.IFunctionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FunctionService implements IFunctionService {
    private IFunctionRepository functionRepository;
}
