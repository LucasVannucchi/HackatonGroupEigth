package com.groupEight.TaskManagement.DTO.responses;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record BaseResponse (
        String message,
        HttpStatus status,
        Object data
){
}
