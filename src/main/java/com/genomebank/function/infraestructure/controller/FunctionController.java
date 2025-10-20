package com.genomebank.function.infraestructure.controller;

import com.genomebank.function.application.ports.IFunctionService;
import com.genomebank.function.entities.Function;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/functions")
public class FunctionController {
    private IFunctionService functionService;


    @GetMapping("/consultarFunciones")
    //@RequestParam nos permite tener el parametro opcional, se puede llamar al endpoin de diferentes formas
    //genomes/consultar_genomas
    //genomes/consultar_genomas?code=G123
    //genomes/consultar_genomas?category=Mamífero
    //genomes/consultar_genomas?code=G123&category=Mamífero

    public ResponseEntity<List<Function>> consultarFuncion(@RequestParam(required = false) String code,
                                                           @RequestParam(required = false) String category) {

        List<Function> functions = functionService.filtrarFunciones(code, category);
        return ResponseEntity.ok(functions);

    }


    @GetMapping("/consultar/{code}")
    public ResponseEntity <Function> consultarFuncionId(@PathVariable String code) {
        return this.functionService.obtenerFuncionPorId(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/crear")
    public ResponseEntity<Function> crearFuncion(@RequestBody Function function) {
        return ResponseEntity.ok(this.functionService.crearFuncion(function));
    }



    @PreAuthorize("hasRole('ADMIN')") // Solo ADMIN puede crear
    @PutMapping("/actualizar/{code}")
    public ResponseEntity<Function> actualizarFuncionId(@PathVariable String code, @RequestBody Function function) {
        return this.functionService.actualizarFuncion(code, function)
                .map(funcionEditada -> ResponseEntity.ok()
                                .body(funcionEditada))
                .orElse(ResponseEntity.notFound().build());
    }



    @PreAuthorize("hasRole('ADMIN')") // Solo ADMIN puede crear
    @DeleteMapping("/eliminar/{code}")
    public ResponseEntity<Void> eliminarFuncionId(@PathVariable String code) {
        boolean eliminar = this.functionService.eliminarFuncion(code);

        if (eliminar) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}






