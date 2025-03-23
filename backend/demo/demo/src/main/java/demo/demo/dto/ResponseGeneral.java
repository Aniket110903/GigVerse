package demo.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import demo.demo.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseGeneral<T> {
    private int status;
    private String message;
    private String error;
    private T data;

    // Success response helper method
    public static <T> ResponseGeneral<T> success(T data, String message) {
        return new ResponseGeneral<>(Constants.statusSuccess, message, "", data);
    }

    // Error response helper method
    public static <T> ResponseGeneral<T> error(int status, String errorMessage) {
        return new ResponseGeneral<>(status, "Error", errorMessage, null);
    }
}
