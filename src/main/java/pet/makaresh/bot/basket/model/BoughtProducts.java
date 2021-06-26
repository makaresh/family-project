package pet.makaresh.bot.basket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoughtProducts {
    private UUID product_id;
    private Double price;
    private int month;
}
