package com.genomebank.genome.infraestructure.controller;

import com.genomebank.genome.application.ports.IGenomeService;
import com.genomebank.genome.entities.Genome;
import com.genomebank.genome.infraestructure.dto.CreatGenomeInDTO;
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

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/consultar_genoma")
    public ResponseEntity<List<Genome>> filterGenomes(@RequestParam(required = false) Long specieId){

        List<Genome>genomes;

        if (specieId != null) {
            genomes=genomeService.getGenomesBySpecieId(specieId);
        }else{
            genomes=genomeService.getGenomes();
        }

        return ResponseEntity.ok(genomes);
    }

    /**
     * Endpoint para consultar un genoma por su ID.
     * @param id El ID del genoma a consultar.
     * @return ResponseEntity con el genoma si se encuentra, o un estado 404 si no se encuentra.
     */
    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/consultar_genoma/{id}")
    public ResponseEntity<Genome> getGenomesById(@PathVariable Long id){

        return this.genomeService.getGenomesById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }



    /**
     * Endpoint para crear un nuevo genoma.
     * @param genome El objeto Genoma a crear.
     * @return ResponseEntity con el genoma creado.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<Genome> createGenome(@RequestBody CreatGenomeInDTO creatGenomeInDTO){
        return ResponseEntity.ok(this.genomeService.createGenome(creatGenomeInDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Genome> updateGenome(@PathVariable Long id, @RequestBody Genome genome){
        return this.genomeService.updateGenome(id, genome)
                .map(genomaEditado->ResponseEntity.ok()
                        .body(genomaEditado))
                .orElse(ResponseEntity.notFound().build());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    //Void hace referencia a que no se devuelve un objeto
    public ResponseEntity<Void> deleteGenome(@PathVariable Long id){
       boolean delete = this.genomeService.deleteGenome(id);
       //Validacion
        if (delete){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}



