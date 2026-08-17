package llm_generated;

public interface ExecutableQuery<T> {
    void setParameter(int index, Object parameter);
    String getSql();
}