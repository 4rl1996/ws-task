package com.gmail._4rl1996.wstesttask.data;

import lombok.Data;

import java.util.List;

@Data
public class TestMessage {
    private String op;
    private List<String> args;
}
