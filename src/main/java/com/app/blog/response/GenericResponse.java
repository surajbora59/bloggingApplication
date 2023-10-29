package com.app.blog.response;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    Object meta;
    Object data;
}