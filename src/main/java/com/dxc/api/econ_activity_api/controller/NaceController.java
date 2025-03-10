package com.dxc.api.econ_activity_api.controller;

import com.dxc.api.econ_activity_api.dto.NaceDto;
import com.dxc.api.econ_activity_api.service.NaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/nace")
@Tag(name = "NACE(Nomenclature of Economic Activities) data management")
public class NaceController {
    private final NaceService naceService;

    public NaceController(NaceService naceService) {
        this.naceService = naceService;
    }

    @PostMapping
    @Operation(summary = "Create a new NACE entry", description = "Adds a new NACE record")
    public ResponseEntity<?> createNaceDetail(@Valid @RequestBody NaceDto naceDto) {
        try {
            return ResponseEntity.ok(this.naceService.createNaceDetail(naceDto));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("Content-Type", "application/json").body("""
                    {"error":"Error saving Nace Details"}
                    """);
        }
    }

    @GetMapping("/code/{code}")
    @Operation(summary = "Fetch all NACE entries by NACE code", description = "Fetch all NACE records for a given NACE code")
    public ResponseEntity<List<NaceDto>> getAllNaceByCode(@PathVariable String code) {
        List<NaceDto> naceList = this.naceService.getAllNaceByCode(code);
        if (naceList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(naceList);
    }
}
