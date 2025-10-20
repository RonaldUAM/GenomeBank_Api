package com.genomebank.function.application.ports;

import com.genomebank.function.entities.Function;

import java.util.List;
import java.util.Optional;

public interface IFunctionService {
    public List<Function> filterFunctions(String code, String category);
    public boolean deleteFunction(String code);
    public Optional<Function> getFunctionById(String code);
    public Function createFunction(Function function);
    Optional<Function> updateFunction(String code, Function function);

}
