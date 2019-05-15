package com.compostage.Data;

import com.compostage.Exceptions.InvalidServerQuery;
import com.compostage.db_query_engine;

import java.security.NoSuchAlgorithmException;

public interface IDataBase {

    //get data from db
    void fetch_data_locally(db_query_engine engine);

    //insert data in the db
    void insert_data_locally(db_query_engine engine) throws NoSuchAlgorithmException;

    //update local db
    void update_data_locally(db_query_engine engine);

    //get data from db
    void fetch_data() throws InvalidServerQuery;


    void update_data();


}
