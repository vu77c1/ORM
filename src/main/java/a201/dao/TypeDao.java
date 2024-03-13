package a201.dao;

import java.util.List;

import a201.entities.Type;

public interface TypeDao {

    public List<Type> findAll();

    public Type findById(int id);

    public Type insert(Type type);

    public Type update(Type type);

    public void delete(Type type);

}
