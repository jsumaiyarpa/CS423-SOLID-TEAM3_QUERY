package llm_generated;

import java.util.ArrayList;
import java.util.List;

public class QueryParameterBindings {
    private final List<String> parameters;

    public QueryParameterBindings() {
        this.parameters = new ArrayList<>();
    }

    public QueryParameterBindings(List<String> parameters) {
        this.parameters = parameters != null ? new ArrayList<>(parameters) : new ArrayList<>();
    }

    public void addParameter(Object param) {
        parameters.add(param != null ? param.toString() : null);
    }

    public void setParameter(int index, Object param) {
        if (index >= 0 && index < parameters.size()) {
            parameters.set(index, param != null ? param.toString() : null);
        }
    }

    public String[] getParameters() {
        return parameters.toArray(new String[0]);
    }
}