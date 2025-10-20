package com.genomebank.genome.infraestructure.controller;

import com.genomebank.genome.application.ports.IGenomeService;
import com.genomebank.genome.entities.Genome;
import com.genomebank.genome.infraestructure.dto.CrearinDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/genomes")
@AllArgsConstructor
public class GenomeController {
    private final IGenomeService genomeService;

    /**
     * Endpoint para obtener todos los genomas.
     * @return ResponseEntity con la lista de genomas.
     */
    //Tratar de mejorar para filtrar
    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/consultar_genoma")
    public ResponseEntity<List<Genome>> obtenerGenomas(){
        List<Genome>genomes= genomeService.obtenerGenomas();
        return ResponseEntity.ok(genomes);
    }

    /**
     * Endpoint para consultar un genoma por su ID.
     * @param id El ID del genoma a consultar.
     * @return ResponseEntity con el genoma si se encuentra, o un estado 404 si no se encuentra.
     */
    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/consultar_genoma/{id}")
    public ResponseEntity<Genome> obtenerGenoma(@PathVariable Long id){

        return this.genomeService.obtenerGenomaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }



    /**
     * Endpoint para crear un nuevo genoma.
     * @param genome El objeto Genoma a crear.
     * @return ResponseEntity con el genoma creado.
     */
    @PreAuthorize("hasRole('ADMIN')") // Solo ADMIN puede crear
    @PostMapping("/crear")
    public ResponseEntity<Genome> crearGenoma(@RequestBody CrearinDTO crearinDTO){
        return ResponseEntity.ok(this.genomeService.crearGenoma(crearinDTO));
    }

    @PreAuthorize("hasRole('ADMIN')") // Solo ADMIN puede crear
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Genome> actualizarGenoma(@PathVariable Long id, @RequestBody Genome genome){
        return this.genomeService.actualizarGenoma(id, genome)
                .map(genomaEditado->ResponseEntity.ok()
                        .body(genomaEditado))
                .orElse(ResponseEntity.notFound().build());
    }
    @PreAuthorize("hasRole('ADMIN')") // Solo ADMIN puede crear
    @DeleteMapping("/eliminar/{id}")
    //Void hace referencia a que no se devuelve un objeto
    public ResponseEntity<Void> eliminarGenoma(@PathVariable Long id){
       boolean eliminar = this.genomeService.eliminarGenoma(id);
       //Validacion
        if (eliminar){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}



