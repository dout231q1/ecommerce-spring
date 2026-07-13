package com.example.shop.infra.docs;

public interface GlobalErrorDocs {
    String INVALID_ID_PARAMETER_EXAMPLE = """
            {
                "timestamp": "2026-07-13T20:10:35",
                "status": "400 BAD_REQUEST",
                "message": "'one' for parameter 'id'. Excepted a valid number.'"
            }
            """;
}
