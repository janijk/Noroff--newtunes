package com.assignment.newtunes.repositories;

import java.util.List;

public interface CRUDRepository <A,B>{
    int insert(A object);
    int update(A object);
    int delete(A object);
    int deleteById(B id);
    List<A> findAll();
    A findById(B id);
}
