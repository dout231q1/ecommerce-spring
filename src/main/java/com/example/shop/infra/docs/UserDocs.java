package com.example.shop.infra.docs;

public interface UserDocs {
    String USER_NOT_FOUND = """
            {
                "status": 404,
                "message": "User with ID 99 not found"
            }
            """;
}
