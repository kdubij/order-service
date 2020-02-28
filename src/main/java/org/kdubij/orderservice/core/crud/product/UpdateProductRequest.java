package org.kdubij.orderservice.core.crud.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kdubij.orderservice.validation.PositiveNonZeroAmountConstraint;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class UpdateProductRequest {

    private String name;

    @PositiveNonZeroAmountConstraint
    private String price;

}
