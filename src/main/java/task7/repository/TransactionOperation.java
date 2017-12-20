package task7.repository;

@FunctionalInterface
public interface TransactionOperation {
    Object doInTransaction();
}
