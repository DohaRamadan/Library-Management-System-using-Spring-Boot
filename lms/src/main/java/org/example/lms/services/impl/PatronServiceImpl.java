package org.example.lms.services.impl;

import java.util.List;
import java.util.Optional;

import org.example.lms.dto.inbound.patron.PatronAddRequest;
import org.example.lms.dto.inbound.patron.PatronDeleteRequest;
import org.example.lms.dto.inbound.patron.PatronGetRequest;
import org.example.lms.dto.inbound.patron.PatronUpdateRequest;
import org.example.lms.dto.inbound.patron.PatronsGetRequest;
import org.example.lms.dto.outbound.patron.PatronAddResponse;
import org.example.lms.dto.outbound.patron.PatronDeleteResponse;
import org.example.lms.dto.outbound.patron.PatronGetResponse;
import org.example.lms.dto.outbound.patron.PatronResponse;
import org.example.lms.dto.outbound.patron.PatronUpdateResponse;
import org.example.lms.dto.outbound.patron.PatronsGetResponse;
import org.example.lms.exceptions.patron.NoPatronsExistException;
import org.example.lms.exceptions.patron.PatronAlreadyExistsException;
import org.example.lms.exceptions.patron.PatronNotFoundException;
import org.example.lms.models.Patron;
import org.example.lms.repositories.PatronRepository;
import org.example.lms.services.PatronService;
import org.springframework.stereotype.Service;

@Service
public class PatronServiceImpl implements PatronService {
    private final PatronRepository patronRepository;

    public PatronServiceImpl(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    @Override
    public PatronsGetResponse getAllPatrons(PatronsGetRequest patronsGetRequest) {
        List<Patron> patrons = patronRepository.findAll();
        if(patrons.isEmpty()){
            throw new NoPatronsExistException();
        }
        return new PatronsGetResponse(patrons);
    }

    @Override
    public PatronGetResponse getPatronById(PatronGetRequest patronGetRequest) {
        Optional<Patron> patronOptional = patronRepository.findById(Long.valueOf(patronGetRequest.getPatronId()));
        if(patronOptional.isEmpty()){
            throw new PatronNotFoundException();
        }
        return new PatronGetResponse(patronOptional.get());
    }

    @Override
    public PatronAddResponse addPatron(PatronAddRequest patronAddRequest) {
        Optional<Patron> patronOptional = patronRepository.findByEmail(patronAddRequest.getEmail());
        if(patronOptional.isPresent()){
            throw new PatronAlreadyExistsException();
        }
        Patron patron = createPatron(patronAddRequest);
        patronRepository.save(patron);
        return new PatronAddResponse();
    }

    @Override
    public PatronUpdateResponse updatePatron(PatronUpdateRequest patronUpdateRequest) {
        Optional<Patron> patronOptional = patronRepository.findByEmail(patronUpdateRequest.getEmail());
        if(patronOptional.isEmpty()){
            throw new PatronNotFoundException();
        }
        Patron patron = patronOptional.get();
        patron.setPhoneNumber(patronUpdateRequest.getPhoneNumber());
        patron.setEmail(patronUpdateRequest.getEmail());
        patron.setFirstName(patronUpdateRequest.getFirstName());
        patron.setLastName(patronUpdateRequest.getLastName());
        patronRepository.save(patron);
        return new PatronUpdateResponse(new PatronResponse(patron));
    }

    @Override
    public PatronDeleteResponse deletePatron(PatronDeleteRequest patronDeleteRequest) {
        Optional<Patron> patronOptional = patronRepository.findById(Long.valueOf(patronDeleteRequest.getPatronId()));
        if(patronOptional.isEmpty()){
            throw new PatronNotFoundException();
        }
        patronRepository.deleteById(Long.valueOf(patronDeleteRequest.getPatronId()));
        return new PatronDeleteResponse();
    }

    private Patron createPatron(PatronAddRequest patronAddRequest){
        Patron patron = new Patron();
        patron.setEmail(patronAddRequest.getEmail());
        patron.setFirstName(patronAddRequest.getFirstName());
        patron.setLastName(patronAddRequest.getLastName());
        patron.setPhoneNumber(patronAddRequest.getPhoneNumber());
        return patron;
    }
}
