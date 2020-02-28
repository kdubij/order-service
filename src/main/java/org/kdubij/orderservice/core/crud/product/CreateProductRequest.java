package org.kdubij.orderservice.core.crud.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kdubij.orderservice.validation.PositiveNonZeroAmountConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class CreateProductRequest {

    @NotBlank
    private String name;

    @NotNull
    @PositiveNonZeroAmountConstraint
    private String price;
}
