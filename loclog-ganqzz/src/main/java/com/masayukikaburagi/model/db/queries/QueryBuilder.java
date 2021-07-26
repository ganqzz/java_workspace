package com.masayukikaburagi.model.db.queries;

/**
 * Query builder
 * TODO: Queryを柔軟に組み立てられるようにする。
 *
 * @author falcon
 */
public class QueryBuilder {

    // 下のような形で、method chainできるようにする。
    public QueryBuilder hoge() {
        QueryBuilder qb = new QueryBuilder();

        // ...

        return qb;
    }
}
