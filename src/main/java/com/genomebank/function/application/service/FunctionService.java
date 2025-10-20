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

//Buscar
    @Override
    public List<Function> filtrarFunciones(String code, String category) {
        //Validar si se encuentra o no el ENUM, sino lanza excepcion
        Category categoria=Category.fromValue(category).orElseThrow(() -> new RuntimeException("x"));

        if (code!=null && category!=null){

            return functionRepository.findByCodeAndCategory(code,categoria);

        }
    }



    @Override
    public boolean eliminarFuncion(String code) {
        if (functionRepository.existsById(code)) {
            functionRepository.deleteById(code);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Function> obtenerFuncionPorId(String code) {
        return this.functionRepository.findById(code);
    }

    @Override
    public Function crearFuncion(Function function) {
        return this.functionRepository.save(function);
    }

    @Override
    public Optional<Function> actualizarFuncion(String code, Function function) {
        return this.functionRepository.findById(code).map(funcionEncontrada ->{
            funcionEncontrada .setName(funcionEncontrada.getName());
            //REVISAR QUE MAS SE PUEDE ACTUALIZAR

            return functionRepository.save(funcionEncontrada);
        });
    }
}
