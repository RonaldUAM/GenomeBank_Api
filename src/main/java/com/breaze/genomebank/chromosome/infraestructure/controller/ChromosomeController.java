package com.breaze.genomebank.chromosome.infraestructure.controller;

import com.breaze.genomebank.chromosome.application.ports.IChromosomeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chromosomes")
@AllArgsConstructor
public class ChromosomeController {
    private IChromosomeService chromosomeService;
}
