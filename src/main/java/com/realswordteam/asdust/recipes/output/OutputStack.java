package com.realswordteam.asdust.recipes.output;

import java.util.List;

public interface OutputStack<T> {

    boolean isEmpty();

    int getSize();

    List<T> getOutputStackList();

    T getOutputStack(int value);
}
