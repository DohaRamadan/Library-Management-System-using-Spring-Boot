package org.example.lms;

import org.example.lms.controllers.PatronController;
import org.example.lms.dto.inbound.patron.*;
import org.example.lms.dto.outbound.patron.*;
import org.example.lms.exceptions.patron.*;
import org.example.lms.models.Patron;
import org.example.lms.services.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PatronControllerTest {

    @Mock
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPatrons_Success() {
        // Prepare
        List<Patron> patrons = new ArrayList<>();
        patrons.add(createSamplePatron());
        when(patronService.getAllPatrons(any())).thenReturn(new PatronsGetResponse(patrons));

        // Execute
        ResponseEntity<PatronsGetResponse> responseEntity = patronController.getAllPatrons();

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(patrons.size(), responseEntity.getBody().getPatronResponses().size());
    }

    @Test
    public void testGetPatronById_Success() {
        // Prepare
        String patronId = "1";
        Patron patron = createSamplePatron();
        when(patronService.getPatronById(any())).thenReturn(new PatronGetResponse(patron));

        // Execute
        ResponseEntity<PatronGetResponse> responseEntity = patronController.getPatronById(patronId);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(patronId, Objects.requireNonNull(responseEntity.getBody()).getPatronResponse().getId().toString());
    }

    @Test
    public void testAddPatron_Success() {
        // Prepare
        PatronAddRequest addRequest = new PatronAddRequest();
        addRequest.setFirstName("John");
        addRequest.setLastName("Doe");
        addRequest.setPhoneNumber("1234567890");
        addRequest.setEmail("john.doe@example.com");

        when(patronService.addPatron(any())).thenReturn(new PatronAddResponse());

        // Execute
        ResponseEntity<PatronAddResponse> responseEntity = patronController.addPatron(addRequest);

        // Verify
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdatePatron_Success() {
        // Prepare
        PatronUpdateRequest updateRequest = new PatronUpdateRequest(/* Add patron details here */);
        when(patronService.updatePatron(any())).thenReturn(new PatronUpdateResponse(new PatronResponse()));

        // Execute
        ResponseEntity<PatronUpdateResponse> responseEntity = patronController.updatePatron(updateRequest);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeletePatron_Success() {
        // Prepare
        String patronId = "1";
        when(patronService.deletePatron(any())).thenReturn(new PatronDeleteResponse());

        // Execute
        ResponseEntity<PatronDeleteResponse> responseEntity = patronController.deletePatron(patronId);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllPatrons_NoPatronsExist() {
        // Prepare
        when(patronService.getAllPatrons(any())).thenThrow(new NoPatronsExistException());

        // Execute and Verify
        assertThrows(NoPatronsExistException.class, () -> patronController.getAllPatrons());
    }

    @Test
    public void testGetPatronById_PatronNotFound() {
        // Prepare
        String patronId = "1";
        when(patronService.getPatronById(any())).thenThrow(new PatronNotFoundException());

        // Execute and Verify
        assertThrows(PatronNotFoundException.class, () -> patronController.getPatronById(patronId));
    }

    @Test
    public void testUpdatePatron_PatronNotFound() {
        // Prepare
        PatronUpdateRequest updateRequest = new PatronUpdateRequest();
        updateRequest.setId("1");
        updateRequest.setFirstName("John");
        updateRequest.setLastName("Doe");
        updateRequest.setPhoneNumber("1234567890");
        updateRequest.setEmail("john.doe@example.com");

        when(patronService.updatePatron(any())).thenThrow(new PatronNotFoundException());

        // Execute and Verify
        assertThrows(PatronNotFoundException.class, () -> patronController.updatePatron(updateRequest));
    }

    @Test
    public void testDeletePatron_PatronNotFound() {
        // Prepare
        String patronId = "1";

        PatronDeleteRequest deleteRequest = new PatronDeleteRequest();
        deleteRequest.setPatronId(patronId);

        when(patronService.deletePatron(any())).thenThrow(new PatronNotFoundException());

        // Execute and Verify
        assertThrows(PatronNotFoundException.class, () -> patronController.deletePatron(patronId));
    }

    @Test
    public void testAddPatron_PatronAlreadyExists() {
        // Prepare
        PatronAddRequest addRequest = new PatronAddRequest(/* Add existing patron details here */);
        when(patronService.addPatron(any())).thenThrow(new PatronAlreadyExistsException());

        // Execute and Verify
        assertThrows(PatronAlreadyExistsException.class, () -> patronController.addPatron(addRequest));
    }


    private Patron createSamplePatron() {
        Patron patron = new Patron();
        patron.setId(1L);
        patron.setFirstName("John");
        patron.setLastName("Doe");
        patron.setPhoneNumber("1234567890");
        patron.setEmail("john@example.com");
        return patron;
    }
}
