package pet.makaresh.bot.basket.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import pet.makaresh.bot.basket.model.Product;
import pet.makaresh.bot.basket.repository.callback.handler.ProductHandler;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ProductConfiguration {

    private final JdbcTemplate jdbcTemplate;


    @Bean
    public Map<Integer, String> seasonMap() {
        return Map.of(
                1, "general",
                2, "winter",
                3, "summer",
                4, "spring",
                5, "autumn"
        );
    }

    @Bean
    public Map<Integer, String> userMap() {
        return Map.of(
                1, "GENERAL",
                2, "MAKAR",
                3, "MASHA"
        );
    }

    @Bean
    public Map<Integer, String> necessityMap() {
        return Map.of(
                1, "FIRST",
                2, "SECOND",
                3, "THIRD"
        );
    }

    @Bean
    public Map<String, Product> productMap() {
        ProductHandler productHandler = new ProductHandler(seasonMap(), userMap(), necessityMap());
        jdbcTemplate.query("select * from saved_products", productHandler);
        return productHandler.getMap();
    }
}
