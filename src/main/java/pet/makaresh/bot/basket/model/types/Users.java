package pet.makaresh.bot.basket.model.types;

import lombok.Getter;

@Getter
public enum Users {

    GENERAL(1), MAKAR(2), MASHA(2);

    private final int userCode;

    Users(int userCode) {
        this.userCode = userCode;
    }
}
