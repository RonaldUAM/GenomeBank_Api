package com.breaze.genomebank.function.application.mappers;

import com.breaze.genomebank.function.application.dto.FunctionOutDto;
import com.breaze.genomebank.function.entities.Function;
import org.springframework.stereotype.Component;

@Component
public class FunctionMapper {
    public FunctionOutDto toDto(Function function){
        return FunctionOutDto.builder()
                .name(function.getName())
                .code(function.getCode())
                .category(function.getCategory().getValue())
                .build();
    }
}
