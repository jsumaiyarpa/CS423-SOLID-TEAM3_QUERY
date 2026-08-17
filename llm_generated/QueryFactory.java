package llm_generated;

public interface QueryFactory<T, Q extends ExecutableQuery<T>> {
    Q createQuery(String sql, String[] parameters);
}