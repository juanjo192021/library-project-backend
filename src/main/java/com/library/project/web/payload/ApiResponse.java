package com.library.project.web.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Data
@NoArgsConstructor
public class ApiResponse {
    private String time;
    private String message;
    private String url;

    public ApiResponse(String message, String url) {
        this.message = message;
        this.url = url.replace("uri=","");
        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
