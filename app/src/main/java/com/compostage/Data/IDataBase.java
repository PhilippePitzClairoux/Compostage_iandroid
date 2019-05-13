package com.compostage.Data;

import com.compostage.Exceptions.InvalidServerQuery;

public interface IDataBase {

    //get data from db
    void fetch_data_locally();

    //insert data in the db
    void insert_data_locally();

    //get data from db
    void fetch_data() throws InvalidServerQuery;

    //insert data in the db
    void insert_data() throws InvalidServerQuery;


}
