package pet.makaresh.bot.basket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private UUID productId;
    private String productName;
    private Double price;
    private Double quantity;
    private String comment;
    private String necessity;
    private String userName;
    private String season;
}
