package llm_generated;

import java.util.Collections;
import java.util.List;

public class CountQuery<T> extends AbstractQuery<T> implements ReadOnlyQuery<T> {

    public CountQuery(String sql, String[] parameters) {
        super(sql, parameters);
    }

    @Override
    public List<T> executeRead() {
        System.out.println("Executing Count Query: " + sql);
        return Collections.emptyList();
    }

    public long count() {
        return 0L;
    }
}