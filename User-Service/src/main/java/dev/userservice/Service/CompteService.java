package dev.userservice.Service;

import dev.userservice.Model.CptModel;
import dev.userservice.dto.CptRequestDto;
import dev.userservice.dto.CptRespondDto;

import java.util.List;
import java.util.Optional;

public interface CompteService {
CptRespondDto SaveCpt(CptRequestDto cptRequestDto);
List<CptRespondDto> getAllCpt();
Optional<CptRespondDto> getByIdCpt(Long id);
Optional<CptRespondDto> delleteCpt(Long id);
CptRespondDto UpdateCpt(CptRespondDto cptRespondDto , Long id);
    void SaveCptEntity(CptModel entity);
}
