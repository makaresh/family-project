package pet.makaresh.bot.basket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketRequestedProduct {
    private UUID productId;
    private String name;
    private String comment;
}
