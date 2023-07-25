package com.nice.surveaweb.util;

import com.nice.surveaweb.dto.ErrorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Result<T> {
    T result;
    ErrorDto error;

    public static <T> Result<T> ofResult(T result) {
        return new Result<>(result, null);
    }

    public static <T> Result<T> ofError(ErrorDto error) {
        return new Result<>(null, error);
    }
}
