package com.lowlayer.app.dto;

import java.time.Instant;
import java.util.Set;

public record PostResponseDTO(Integer id, String title, String categoryName, String content, Set<String> tags, Instant createdAt, Instant updatedAt) {

}
