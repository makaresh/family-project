package pet.makaresh.bot.basket.model.types;

import lombok.Getter;

@Getter
public enum Necessity {
    FIRST(1), SECOND(2), THIRD(3);

    private final int necessityCode;


    Necessity(int necessityCode) {
        this.necessityCode = necessityCode;
    }
}
