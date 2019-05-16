package com.compostage.Data;

import com.compostage.Exceptions.InvalidServerQuery;
import com.compostage.db_query_engine;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public interface IDataBase {

    //get data from db
    void fetch_data_locally();
    //insert data in the db
    void insert_data_locally();

    //update local db
    void update_data_locally();

    //get data from db
    void fetch_data() throws InvalidServerQuery, IOException;


    void update_data();



}
