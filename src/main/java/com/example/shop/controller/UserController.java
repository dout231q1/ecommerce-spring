package com.example.shop.controller;

import com.example.shop.database.entity.User;
import com.example.shop.infra.ErrorResponse;
import com.example.shop.infra.docs.GlobalErrorDocs;
import com.example.shop.infra.docs.UserDocs;
import com.example.shop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @Operation(summary = "Get a user by ID", description = "Retrieves a single user by their unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "No user found with the given ID",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = UserDocs.USER_NOT_FOUND)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid 'id' parameter. Must be a valid number",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = GlobalErrorDocs.INVALID_ID_PARAMETER_EXAMPLE)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User userFound = userService.findById(id);
        return ResponseEntity.ok(userFound);
    }

    @Operation(summary = "Get all existing users", description = "Retrieves each registered user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User's list retrieved succesfully",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "Populated List",
                                            summary = "Example when there are users registered",
                                            value = UserDocs.POPULATED_LIST
                                    ),
                                    @ExampleObject(
                                            name = "Empty List",
                                            summary = "Example when no users are found",
                                            value = UserDocs.EMPTY_LIST
                                    )
                            }
                    )
            )
    })
    @GetMapping
    public ResponseEntity<List<User>> listUsers(){
        List<User> allUsers = userService.findAll();
        return ResponseEntity.ok(allUsers);
    }

    @Operation(summary = "Create a new user", description = "Registers a new user in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Failed to create a user. Check 'errors' field for details",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Missing Required Field",
                                            summary = "Example when a required field is not provided",
                                            value = UserDocs.USER_MISSING_FIELDS_EXAMPLE
                                    ),
                                    @ExampleObject(
                                            name = "Invalid Balance",
                                            summary = "Example when balance is provided but negative",
                                            value = UserDocs.USER_INVALID_BALANCE_EXAMPLE
                                    )
                            }
                    )
            )

    })
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User userCreated = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
