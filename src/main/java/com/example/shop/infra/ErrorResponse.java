package com.example.shop.infra;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;


@AllArgsConstructor
@Getter
@Setter

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"timestamp", "status", "message", "fields"})
public class ErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
    private String status;
    private String message;
    private Map<String, String> fields;

    public ErrorResponse(HttpStatus status, String message){
        this.timestamp = LocalDateTime.now();
        this.status = status.value() + " " + status.name();
        this.message = message;
    }

    public ErrorResponse(HttpStatus status, String message, Map<String, String> fields) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value() + " " + status.name();
        this.message = message;
        this.fields = fields;
    }
}
