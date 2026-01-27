package com.lowlayer.app.dto;

import com.lowlayer.app.exceptions.ErrorCode;

public record ErrorResponseDTO<T>(ErrorCode code, T message) {

}
