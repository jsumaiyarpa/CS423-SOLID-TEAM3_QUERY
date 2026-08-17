package llm_generated;

public class DeleteQuery<T> extends AbstractQuery<T> implements ModifyingQuery<T> {

    public DeleteQuery(String sql, String[] parameters) {
        super(sql, parameters);
    }

    @Override
    public void executeModifying() {
        System.out.println("Executing Delete Modifying Query: " + sql);
    }
}