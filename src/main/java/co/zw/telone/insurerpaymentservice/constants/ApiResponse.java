package co.zw.telone.insurerpaymentservice.constants;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T>{


    private HttpStatus httpStatus;
    private T data;

}
