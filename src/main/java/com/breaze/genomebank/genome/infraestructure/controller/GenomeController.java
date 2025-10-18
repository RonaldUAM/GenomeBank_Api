package com.breaze.genomebank.genome.infraestructure.controller;

import com.breaze.genomebank.genome.application.ports.IGenomeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/genomes")
@AllArgsConstructor
public class GenomeController {
    private IGenomeService genomeService;
}
