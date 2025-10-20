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

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/filtrar_funciones")
    //@RequestParam nos permite tener el parametro opcional, se puede llamar al endpoin de diferentes formas
    //genomes/consultar_genomas
    //genomes/consultar_genomas?code=G123
    //genomes/consultar_genomas?category=Mamífero
    //genomes/consultar_genomas?code=G123&category=Mamífero

    public ResponseEntity<List<Function>> filterFunctions(@RequestParam(required = false) String code,
                                                           @RequestParam(required = false) String category) {

        List<Function> functions = functionService.filterFunctions(code, category);
        return ResponseEntity.ok(functions);

    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/consultar/{code}")
    public ResponseEntity <Function> getFunctionById(@PathVariable String code) {
        return this.functionService.getFunctionById(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<Function> createFunction(@RequestBody Function function) {
        return ResponseEntity.ok(this.functionService.createFunction(function));
    }



    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar/{code}")
    public ResponseEntity<Function> updateFunctionById(@PathVariable String code, @RequestBody Function function) {
        return this.functionService.updateFunction(code, function)
                .map(funcionEditada -> ResponseEntity.ok()
                                .body(funcionEditada))
                .orElse(ResponseEntity.notFound().build());
    }



    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{code}")
    public ResponseEntity<Void> deleteFunctionById(@PathVariable String code) {
        boolean delete = this.functionService.deleteFunction(code);

        if (delete) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}






