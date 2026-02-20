package dev.userservice.Controller;

import dev.userservice.Service.CompteService;
import dev.userservice.Service.ServiceImpl.CptServiceImpl;
import dev.userservice.dto.CptRequestDto;
import dev.userservice.dto.CptRespondDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cpt")
public class CptController {
    private final CompteService compteService;

    public CptController(CompteService compteService) {
        this.compteService = compteService;
    }
    @GetMapping
    public ResponseEntity<List<CptRespondDto>> getAll() {

        return ResponseEntity.ok(compteService.getAllCpt());
    }


    @GetMapping("/{id}")
    public ResponseEntity<CptRespondDto> getById(@PathVariable Long id) {

        return compteService.getByIdCpt(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<CptRespondDto> update(
            @PathVariable Long id,
            @RequestBody CptRequestDto dto) {

        CptRespondDto updated = compteService.UpdateCpt(
                new CptRespondDto(id, dto.nom(), dto.prenom(), dto.age(), dto.pays()),
                id
        );

        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CptRespondDto> delete(@PathVariable Long id) {

        return compteService.delleteCpt(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
