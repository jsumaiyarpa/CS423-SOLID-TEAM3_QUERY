package llm_generated;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class LazyList<E> extends AbstractList<E> {
    private final List<E> entities = new ArrayList<>();

    @Override
    public E get(int index) {
        return entities.get(index);
    }

    @Override
    public int size() {
        return entities.size();
    }

    public void close() {
        entities.clear();
    }
}