package eaut.edu.vn.interfaces;

import java.util.List;

public interface IService<E> {

    List<E> getAll();

    void add(E e);

    void remove(E e);

    E find(Object obj);
}
