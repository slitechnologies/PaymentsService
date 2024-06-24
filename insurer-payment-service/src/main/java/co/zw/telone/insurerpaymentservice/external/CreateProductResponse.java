package co.zw.telone.insurerpaymentservice.external;

import co.zw.telone.insurerpaymentservice.constants.StatusCode;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductResponse {
    private Long productId;
    private String  productName;
    private Long categoryId;
    private Long insurerId;
    private StatusCode statusCode;
}
