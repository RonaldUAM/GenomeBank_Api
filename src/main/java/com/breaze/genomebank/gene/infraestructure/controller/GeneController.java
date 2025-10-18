package com.breaze.genomebank.gene.infraestructure.controller;

import com.breaze.genomebank.gene.application.ports.IGeneService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/genes")
public class GeneController {
    private IGeneService geneService;
}
