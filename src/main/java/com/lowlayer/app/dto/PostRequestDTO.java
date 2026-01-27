package com.lowlayer.app.dto;

import java.util.Set;

import jakarta.validation.constraints.Size;


public record PostRequestDTO(String catName, String title,@Size(max = 500, message = "Contenido demasiado largo") String content, Set<String> tags) {
    
}
