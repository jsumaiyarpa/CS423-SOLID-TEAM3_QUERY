package llm_generated;

import java.util.Collections;
import java.util.List;

public class CursorQuery<T> extends AbstractQuery<T> implements ReadOnlyQuery<T> {

    public CursorQuery(String sql, String[] parameters) {
        super(sql, parameters);
    }

    @Override
    public List<T> executeRead() {
        System.out.println("Executing Cursor Query: " + sql);
        return Collections.emptyList();
    }
}