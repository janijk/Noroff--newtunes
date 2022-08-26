package com.assignment.newtunes.repositories;

import java.util.List;

public interface CRUDRepository <A,B>{
    /**
     * Insert object into db.
     *
     * @param object object to insert
     * @return 0 if failed, 1 if success
     */
    int insert(A object);

    /**
     * Update object in db.
     *
     * @param object object to update
     * @return 0 if failed, 1 if success
     */
    int update(A object);

    /**
     * Delete object from db.
     *
     * @param object object to remove
     * @return 0 if failed, 1 if success
     */
    int delete(A object);

    /**
     * Delete object from db by id.
     *
     * @param id id to delete by
     * @return 0 if failed, 1 if success
     */
    int deleteById(B id);

    /**
     * Find all items from a table.
     *
     * @return List of items
     */
    List<A> findAll();

    /**
     * Find one item from db by its id.
     *
     * @param id id to search by
     * @return item
     */
    A findById(B id);
}
