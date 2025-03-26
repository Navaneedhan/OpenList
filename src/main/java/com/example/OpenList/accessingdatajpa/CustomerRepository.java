package com.example.OpenList.accessingdatajpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Query(value = """
        select
            *
        from
            customers c
        where (c.first_name, c.last_name) in (
            SELECT
                first_name, last_name
            FROM
                UNNEST(CAST(:column1Array as varchar[]), CAST(:column2Array as varchar[])) AS t(first_name, last_name)
    )
    """, nativeQuery = true)
    List<Customer> findByTuples(
            @Param("column1Array") String[] column1Array,
            @Param("column2Array") String[] column2Array
    );
}
