package pet.makaresh.bot.basket.model.types;

import lombok.Getter;

@Getter
public enum Season {
    WINTER(1), SUMMER(2), SPRING(3), AUTUMN(4), GENERAL(5);

    private final int seasonCode;

    Season(int seasonCode) {
        this.seasonCode = seasonCode;
    }
}
