package org.tomaszkowalczyk94.pomodorotasksmanager.core.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public interface LambdaBuilder<T> {

    T simpleBuild();

    default T build() {
        T t = simpleBuild();
        getAfterBuild().forEach(consumer -> consumer.accept(t));
        return t;
    }

    default List<Consumer<T>> getAfterBuild() {
        return new ArrayList<>();
    }

}
