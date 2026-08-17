package llm_generated;

import java.util.Arrays;

public abstract class AbstractQuery<T> implements ExecutableQuery<T> {
    protected final String sql;
    protected final String[] parameters;

    protected AbstractQuery(String sql, String[] parameters) {
        this.sql = sql;
        this.parameters = parameters != null ? Arrays.copyOf(parameters, parameters.length) : new String[0];
    }

    @Override
    public void setParameter(int index, Object parameter) {
        if (index >= 0 && index < parameters.length) {
            parameters[index] = parameter != null ? parameter.toString() : null;
        }
    }

    @Override
    public String getSql() {
        return sql;
    }

    public String[] getParameters() {
        return Arrays.copyOf(parameters, parameters.length);
    }
}