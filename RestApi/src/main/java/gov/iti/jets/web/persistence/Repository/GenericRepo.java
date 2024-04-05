package gov.iti.jets.web.persistence.Repository;
import java.util.List;
import java.util.Optional;

public interface GenericRepo <T,ID>{
    public Optional<List<T>> findAll();
    public Optional<T> findById(ID id);
    public void create(T entity);
    public Optional<T> update(T entity);
    public void deleteById(ID id);
    public void delete(T entity);
}