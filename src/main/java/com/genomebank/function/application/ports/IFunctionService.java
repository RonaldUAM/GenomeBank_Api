package com.genomebank.function.application.ports;

import com.genomebank.function.entities.Function;

import java.util.List;
import java.util.Optional;

public interface IFunctionService {
    public List<Function> filtrarFunciones(String code, String category);
    public boolean eliminarFuncion(String code);
    public Optional<Function> obtenerFuncionPorId(String code);//Revisar
    public Function crearFuncion(Function function);
    Optional<Function> actualizarFuncion(String code, Function function);

}
