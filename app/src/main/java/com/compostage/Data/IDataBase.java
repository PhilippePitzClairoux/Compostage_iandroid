package com.compostage.Data;

public interface IDataBase {

    //get data from db
    void fetch_data();

    //insert data in the db
    void insert_data();

    //send the current data to the server
    void sync_data();

}
