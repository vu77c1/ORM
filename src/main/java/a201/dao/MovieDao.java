package a201.dao;

import java.util.List;

import a201.entities.MoVie;

public interface MovieDao {

    public List<MoVie> findAll();

    public MoVie findById(String id);

    public void insert(MoVie movie);

    public void update(MoVie movie);

    public void delete(MoVie movie);

}
