package pet.makaresh.bot.budget.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalProduct {
    private String name;
    private Double quantity;
    private Double sum;
}
