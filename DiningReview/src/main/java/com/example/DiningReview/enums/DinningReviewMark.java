package com.example.DiningReview.enums;

import lombok.Getter;

@Getter
public enum DinningReviewMark {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int value;
    DinningReviewMark(int value) {
        this.value = value;
    }
}
