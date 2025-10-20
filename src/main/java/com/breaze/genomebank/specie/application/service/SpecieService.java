package com.breaze.genomebank.specie.application.service;

import com.breaze.genomebank.specie.application.dto.SpecieInDTO;
import com.breaze.genomebank.specie.application.dto.SpecieOutDTO;
import com.breaze.genomebank.specie.application.ports.ISpecieService;
import com.breaze.genomebank.specie.entities.Specie;
import com.breaze.genomebank.specie.infraestructure.repository.SpecieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de gestión de especies (CRUD de especies).
 */
@Service
@AllArgsConstructor
public class SpecieService implements ISpecieService {
    private SpecieRepository specieRepository;

    /**
     * Obtiene todas las especies que se hayan registrado hasta el momento.
     * Convierte cada entidad Specie a SpecieOutDTO antes de retornar.
     * @return Lista de SpecieOutDTO con todas las especies
     */
    @Override
    public List<SpecieOutDTO> getAllSpecies() 
    {
        //Obtiene todas las especies de la base de datos
        List<Specie> species = specieRepository.findAll();
        //Convierte cada entidad a dto y retorna la lista
        return species.stream()
                .map(this::convertToOutDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene una especie específica por su id.
     * @param id ID de la especie a buscar
     * @return SpecieOutDTO con los datos de la especie
     * @throws RuntimeException si no se encuentra la especie
     */
    @Override
    public SpecieOutDTO getSpecieById(Long id)
    {
        //Busca la especie por id y manda excepción si no existe
        Specie specie = specieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especie no encontrada con ID: " + id));
        //Convierte la entidad a dto y retorna
        return convertToOutDTO(specie);
    }

    /**
     * Crea una nueva especie y valida que el nombre científico no exista.
     * @param specieInDTO Datos de la especie a crear
     * @return SpecieOutDTO con los datos de la especie creada
     * @throws RuntimeException si el nombre científico ya existe
     */
    @Override
    public SpecieOutDTO createSpecie(SpecieInDTO specieInDTO)
    {
        //Valida que el nombre científico no sea nulo o vacío
        if (specieInDTO.getScientificName() == null || specieInDTO.getScientificName().trim().isEmpty())
        {
            throw new RuntimeException("El nombre científico es obligatorio");
        }
        
        //Valida que el nombre científico no exista
        if (specieRepository.existsByScientificName(specieInDTO.getScientificName()))
        {
            throw new RuntimeException("Ya existe una especie con el nombre científico: " + 
                specieInDTO.getScientificName());
        }
        
        //Crea la nueva entidad usando el patrón Builder
        Specie specie = Specie.builder()
                .scientificName(specieInDTO.getScientificName())
                .commonName(specieInDTO.getCommonName())
                .build();
        
        //Guarda la especie en la base de datos
        Specie savedSpecie = specieRepository.save(specie);
        //Convierte y retorna el DTO
        return convertToOutDTO(savedSpecie);
    }

    /**
     * Actualiza una especie existente y valida que el nuevo nombre científico no exista ya.
     * @param id ID de la especie a actualizar
     * @param specieInDTO Nuevos datos de la especie
     * @return SpecieOutDTO con los datos actualizados
     * @throws RuntimeException si no se encuentra la especie o el nombre ya existe
     */
    @Override
    public SpecieOutDTO updateSpecie(Long id, SpecieInDTO specieInDTO)
    {
        //Busca la especie a actualizar
        Specie specie = specieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especie no encontrada con ID: " + id));
        
        //Valida que al menos un campo sea diferente
        boolean scientificNameChanged = !specie.getScientificName().equals(specieInDTO.getScientificName());
        boolean commonNameChanged = !specie.getCommonName().equals(specieInDTO.getCommonName());
        if (!scientificNameChanged && !commonNameChanged) {
            throw new RuntimeException("No se realizaron cambios. Los datos son idénticos a los actuales");
        }
        
        //Valida que el nuevo nombre científico no esté en uso por otra especie
        if (scientificNameChanged && specieRepository.existsByScientificName(specieInDTO.getScientificName())) {
            throw new RuntimeException("Ya existe otra especie con el nombre científico: " + 
                specieInDTO.getScientificName());
        }
        
        //Actualiza los campos de la especie usando el Builder()
        Specie updatedSpecie = specie.toBuilder()
                .scientificName(specieInDTO.getScientificName())
                .commonName(specieInDTO.getCommonName())
                .build();
        
        //Guarda los cambios
        Specie savedSpecie = specieRepository.save(updatedSpecie);
        //Convierte y retorna el dto
        return convertToOutDTO(savedSpecie);
    }

    /**
     * Elimina una especie del sistema y se eliminan en cascada todos sus genomas.
     * @param id de la especie a eliminar
     * @throws RuntimeException si no se encuentra la especie
     */
    @Override
    public void deleteSpecie(Long id)
    {
        // Verifica que la especie exista
        if (!specieRepository.existsById(id))
        {
            throw new RuntimeException("Especie no encontrada con ID: " + id);
        }

        // Elimina la especie (junto a sus genomas en cascada)
        specieRepository.deleteById(id);
    }

    /**
     * Método privado para convertir una entidad Specie a SpecieOutDTO.
     * Calcula la cantidad de genomas que tiene a la especie.
     * @param specie Entidad a convertir
     * @return SpecieOutDTO con los datos de la especie
     */
    private SpecieOutDTO convertToOutDTO(Specie specie)
    {
        return SpecieOutDTO.builder()
                .id(specie.getId())
                .scientificName(specie.getScientificName())
                .commonName(specie.getCommonName())
                .genomeCount(specie.getGenomes()!=null?specie.getGenomes().size():0)
                .build();
    }
}