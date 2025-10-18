package com.breaze.genomebank.function.infraestructure.controller;

import com.breaze.genomebank.function.application.ports.IFunctionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/functions")
public class FunctionController {
    private IFunctionService functionService;
}
