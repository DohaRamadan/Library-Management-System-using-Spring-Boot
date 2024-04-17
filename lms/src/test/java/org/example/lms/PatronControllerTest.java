package org.example.lms;

import org.example.lms.controllers.PatronController;
import org.example.lms.dto.inbound.patron.*;
import org.example.lms.dto.outbound.patron.*;
import org.example.lms.services.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PatronControllerTest {

    @Mock
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPatrons() {
        // Prepare
        PatronsGetResponse expectedResponse = new PatronsGetResponse(Collections.emptyList());
        when(patronService.getAllPatrons(any())).thenReturn(expectedResponse);

        // Execute
        ResponseEntity<PatronsGetResponse> responseEntity = patronController.getAllPatrons();

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void testGetPatronById() {
        // Prepare
        String patronId = "1";
        PatronGetResponse expectedResponse = new PatronGetResponse(patronId, "John Doe");
        when(patronService.getPatronById(any())).thenReturn(expectedResponse);

        // Execute
        ResponseEntity<PatronGetResponse> responseEntity = patronController.getPatronById(patronId);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void testAddPatron() {
        // Prepare
        PatronAddRequest request = new PatronAddRequest("John Doe");
        PatronAddResponse expectedResponse = new PatronAddResponse("1", "John Doe");
        when(patronService.addPatron(any())).thenReturn(expectedResponse);

        // Execute
        ResponseEntity<PatronAddResponse> responseEntity = patronController.addPatron(request);

        // Verify
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void testUpdatePatron() {
        // Prepare
        PatronUpdateRequest request = new PatronUpdateRequest("1", "John Smith");
        PatronUpdateResponse expectedResponse = new PatronUpdateResponse("1", "John Smith");
        when(patronService.updatePatron(any())).thenReturn(expectedResponse);

        // Execute
        ResponseEntity<PatronUpdateResponse> responseEntity = patronController.updatePatron(request);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void testDeletePatron() {
        // Prepare
        String patronId = "1";
        PatronDeleteResponse expectedResponse = new PatronDeleteResponse(patronId, "John Doe");
        when(patronService.deletePatron(any())).thenReturn(expectedResponse);

        // Execute
        ResponseEntity<PatronDeleteResponse> responseEntity = patronController.deletePatron(patronId);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
}
