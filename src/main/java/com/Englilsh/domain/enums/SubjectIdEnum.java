package com.Englilsh.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SubjectIdEnum {
    USE_OF_ENGLISH(4,"Use of English"),
    SIX(6,"六级"),
    ONE(1,"英语一"),
    TWO(2,"英语二");

    int value;
    String name;
}
