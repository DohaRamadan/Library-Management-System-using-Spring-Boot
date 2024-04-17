package org.example.lms.services;

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

public interface PatronService {
    PatronsGetResponse getAllPatrons(PatronsGetRequest patronsGetRequest);
    PatronGetResponse getPatronById(PatronGetRequest patronGetRequest);
    PatronAddResponse addPatron(PatronAddRequest patronAddRequest);
    PatronUpdateResponse updatePatron(PatronUpdateRequest patronUpdateRequest);
    PatronDeleteResponse deletePatron(PatronDeleteRequest patronDeleteRequest);
}
