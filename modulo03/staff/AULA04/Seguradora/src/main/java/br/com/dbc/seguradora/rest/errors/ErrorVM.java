package br.com.dbc.seguradora.rest.errors;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * View Model for transferring error message with a list of field errors.
 */
@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ErrorVM implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final String description;
    
}
