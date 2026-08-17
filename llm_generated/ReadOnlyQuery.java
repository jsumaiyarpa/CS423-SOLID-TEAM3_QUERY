package llm_generated;

import java.util.List;

public interface ReadOnlyQuery<T> extends ExecutableQuery<T> {
    List<T> executeRead();
}