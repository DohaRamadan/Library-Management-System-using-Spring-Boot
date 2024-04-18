package org.example.lms.dto.outbound;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Data
public class ApiErrorResponse {
    private final String guid;
    private final String errorCode;
    private final String message;
    private final Integer statusCode;
    private final String statusName;
    private final String path;
    private final String method;
    private final LocalDateTime timestamp;
    private List<String> errors;

}