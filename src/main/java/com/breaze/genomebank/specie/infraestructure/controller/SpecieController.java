package com.breaze.genomebank.specie.infraestructure.controller;

import com.breaze.genomebank.specie.application.ports.ISpecieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/species")
@AllArgsConstructor
public class SpecieController {
    private ISpecieService specieService;
}
