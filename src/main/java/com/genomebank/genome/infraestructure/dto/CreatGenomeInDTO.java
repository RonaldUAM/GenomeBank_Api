package com.genomebank.genome.infraestructure.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatGenomeInDTO {

   private List<Integer> genome;

    public List<Integer> getGenome() {
        return genome;
    }
}
