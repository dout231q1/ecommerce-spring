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
}
