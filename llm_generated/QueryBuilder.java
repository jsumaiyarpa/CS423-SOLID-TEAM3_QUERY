package llm_generated;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder<T> {
    private final StringBuilder sqlBuilder;
    private final List<String> parameterList;

    public QueryBuilder() {
        this.sqlBuilder = new StringBuilder("SELECT * FROM ENTITY");
        this.parameterList = new ArrayList<>();
    }

    public QueryBuilder<T> where(String condition, Object... args) {
        sqlBuilder.append(" WHERE ").append(condition);
        for (Object arg : args) {
            parameterList.add(arg != null ? arg.toString() : null);
        }
        return this;
    }

    // Depends on Abstraction via QueryFactory (DIP & OCP Satisfied)
    public <Q extends ExecutableQuery<T>> Q buildCustomQuery(QueryFactory<T, Q> factory) {
        return factory.createQuery(sqlBuilder.toString(), parameterList.toArray(new String[0]));
    }
}