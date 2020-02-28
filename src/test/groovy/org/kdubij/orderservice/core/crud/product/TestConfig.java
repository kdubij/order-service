package org.kdubij.orderservice.core.crud.product;

import org.kdubij.orderservice.core.order.infrastructure.repository.mapper.ProductMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@TestConfiguration
@ActiveProfiles("test")
@Import(EmbeddedMongoAutoConfiguration.class)
public class TestConfig {

    @Bean
    ProductMapper productMapper() {
        return Mappers.getMapper(ProductMapper.class);
    }

}
