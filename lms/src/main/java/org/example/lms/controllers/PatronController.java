package org.example.lms.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.example.lms.dto.inbound.patron.PatronAddRequest;
import org.example.lms.dto.inbound.patron.PatronDeleteRequest;
import org.example.lms.dto.inbound.patron.PatronGetRequest;
import org.example.lms.dto.inbound.patron.PatronUpdateRequest;
import org.example.lms.dto.inbound.patron.PatronsGetRequest;
import org.example.lms.dto.outbound.patron.PatronAddResponse;
import org.example.lms.dto.outbound.patron.PatronDeleteResponse;
import org.example.lms.dto.outbound.patron.PatronGetResponse;
import org.example.lms.dto.outbound.patron.PatronUpdateResponse;
import org.example.lms.dto.outbound.patron.PatronsGetResponse;
import org.example.lms.services.PatronService;
import org.example.lms.validtors.ID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public ResponseEntity<PatronsGetResponse> getAllPatrons() {
        PatronsGetResponse response = patronService.getAllPatrons(new PatronsGetRequest());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{patronId}")
    public ResponseEntity<PatronGetResponse> getPatronById(@Valid @ID @PathVariable(name = "patronId", required = true) @NotNull String patronId) {
        PatronGetResponse response = patronService.getPatronById(new PatronGetRequest(patronId));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PatronAddResponse> addPatron(@Valid @RequestBody @NotNull PatronAddRequest patronAddRequest) {
        PatronAddResponse response = patronService.addPatron(patronAddRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PatronUpdateResponse> updatePatron(@Valid @RequestBody @NotNull PatronUpdateRequest patronUpdateRequest) {
        PatronUpdateResponse response = patronService.updatePatron(patronUpdateRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{patronId}")
    public ResponseEntity<PatronDeleteResponse> deletePatron(@Valid @ID @PathVariable(name = "patronId", required = true) @NotNull String patronId) {
        PatronDeleteResponse response = patronService.deletePatron(new PatronDeleteRequest(patronId));
        return ResponseEntity.ok(response);
    }
}
