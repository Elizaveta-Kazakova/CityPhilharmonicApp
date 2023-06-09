package ru.nsu.fit.ekazakova.cityPhiharmonic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImpresarioDto implements ImpresarioDetailsDto {
    private Long id;
    private String name;
}
