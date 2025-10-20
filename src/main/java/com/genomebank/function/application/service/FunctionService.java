package com.genomebank.function.application.service;

import com.genomebank.function.application.ports.IFunctionService;
import com.genomebank.function.entities.Category;
import com.genomebank.function.entities.Function;
import com.genomebank.function.infraestructure.repository.IFunctionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FunctionService implements IFunctionService {
    private IFunctionRepository functionRepository;

    @Override
    public List<Function> filterFunctions(String code, String category) {


        if (code!=null && category!=null){
            //Validar si se encuentra o no el ENUM, sino lanza excepcion
            Category cat=Category.fromValue(category).orElseThrow(() -> new RuntimeException("Categoria Invalida"));

            return functionRepository.findByCodeAndCategory(code, cat);

        } else if (code!=null) {
            return functionRepository.findByCode(code);

        } else if (category!=null) {
            Category category1 = Category.fromValue(category).orElseThrow(() -> new RuntimeException("Categoria Invalida"));
            return functionRepository.findByCategory(category1);

        }
        //En caso de que no se encuentre coincidencias retorna una lista vacia
        return List.of();
    }



    @Override
    public Optional<Function> getFunctionById(String code) {
        return this.functionRepository.findById(code);
    }

    @Override
    public Function createFunction(Function function) {
        return this.functionRepository.save(function);
    }

    @Override
    public Optional<Function> updateFunction(String code, Function function) {
        return this.functionRepository.findById(code).map(funcionEncontrada ->{
            funcionEncontrada .setName(funcionEncontrada.getName());
            funcionEncontrada.setCategory(funcionEncontrada.getCategory());

            return functionRepository.save(funcionEncontrada);
        });
    }

    @Override
    public boolean deleteFunction(String code) {
        if (functionRepository.existsById(code)) {
            functionRepository.deleteById(code);
            return true;
        }
        return false;
    }
}
