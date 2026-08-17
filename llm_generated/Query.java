package llm_generated;

import java.util.ArrayList;
import java.util.List;

public class Query<T> extends AbstractQuery<T> implements ReadOnlyQuery<T> {

    public Query(String sql, String[] parameters) {
        super(sql, parameters);
    }

    @Override
    public List<T> executeRead() {
        System.out.println("Executing Read Query: " + sql);
        return new ArrayList<>();
    }
}