package com.realswordteam.asdust.recipes.input;

import java.util.List;

public interface InputStack<T> {
    boolean isEmpty();

    int getSize();

    List<T> getInputStackList();

    T getInputStack(int value);
}
