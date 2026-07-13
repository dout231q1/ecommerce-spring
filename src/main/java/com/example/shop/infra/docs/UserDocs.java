package com.example.shop.infra.docs;

public interface UserDocs {
    String USER_NOT_FOUND = """
            {
                "status": 404,
                "message": "User with ID 99 not found"
            }
            """;
    String POPULATED_LIST = """
            [
                {
                    "id": 1, "username": "Robert Pattinson", "balance": 1500.00
                },
                {
                    "id": 2, "username": "Christian Bale", "balance": 9999.5
                }
            ]
            """;

    String EMPTY_LIST = "[]";

    String USER_MISSING_FIELDS_EXAMPLE = """
            {
                "timestamp": "2026-07-13T19:38:05",
                "status": "400 BAD_REQUEST",
                "message": "Validation failed for the submitted fields",
                "fields": {
                    "balance": "Balance is required.",
                    "balance": "Balance cannot be negative.",
                    "username": "Username is required."
                }
            }
            """;

    String USER_INVALID_BALANCE_EXAMPLE = """
            {
                "timestamp": "2026-07-13T19:38:05",
                "status": "400 BAD_REQUEST",
                "message": "Validation failed for the submitted fields",
                "fields": {
                    "balance": "Balance cannot be negative."
                }
            }
            """;
}
