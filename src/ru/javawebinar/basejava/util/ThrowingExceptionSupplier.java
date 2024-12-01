package ru.javawebinar.basejava.util;

import java.io.IOException;

@FunctionalInterface
public interface ThrowingExceptionSupplier<T> {
    T get() throws IOException;
}
