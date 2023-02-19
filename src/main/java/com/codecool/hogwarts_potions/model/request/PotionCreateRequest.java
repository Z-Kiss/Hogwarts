package com.codecool.hogwarts_potions.model.request;

import com.codecool.hogwarts_potions.model.potion.Potion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PotionCreateRequest {

    private Long studentId;

    private Potion potion;
}
