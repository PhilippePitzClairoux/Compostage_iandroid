package com.compostage.Data;


import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.compostage.Exceptions.InvalidServerQuery;

import com.compostage.MainActivity;
import com.compostage.ServerQueries;
import com.compostage.db_query_engine;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class User implements Serializable, IDataBase {

    private String username;
    private UserType usertype;
    private String password;
    private String email;
    private String authquestion;
    private String authanswer;
    private db_query_engine query_engine_instance;

    private static String INSERT_NEW_USER_LOCALLY = "INSERT INTO users(username, user_type_id," +
            " password, email, auth_question, auth_answer) VALUES (?, ?, ?, ?, ?, ?)";
    private static String FETCH_USER_LOCALLY = "SELECT * FROM users WHERE username = ?";


    public User(String username, db_query_engine engine)
    {
        this.username = username;
        this.query_engine_instance = engine;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUsertype() {
        return usertype;
    }

    public void setUsertype(UserType usertype) {
        this.usertype = usertype;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthquestion() {
        return authquestion;
    }

    public void setAuthquestion(String authquestion) {
        this.authquestion = authquestion;
    }

    public String getAuthanswer() {
        return authanswer;
    }

    public void setAuthanswer(String authanswer) {
        this.authanswer = authanswer;
    }

    @Override
    public void fetch_data() throws InvalidServerQuery {

        String query = String.format(ServerQueries.GET_USER_INFO, this.username);

        try {

            JsonParser answer = new JsonParser(new HttpRequester(query).processRequest());
            JSONObject userType = answer.getObject("user_type");

            if (answer.fieldNameExists("error")) {
                throw new InvalidServerQuery(answer.getField("error"));
            }

            this.password = (answer.getField("user_password").charAt(2) == 'y' ? answer.getField("user_password").replace("$2y$", "$2a$") : answer.getField("user_password"));
            this.email = answer.getField("user_email");
            this.authquestion = answer.getField("user_auth_question");
            this.authanswer = answer.getField("user_auth_answer");

            this.usertype = new UserType(userType.getString("user_type_name"),
                    userType.getString("user_type_description"));

            JSONArray permissions = userType.getJSONArray("user_permissions");

            System.out.println(permissions.toString());

            for (int i = 0; i < permissions.length(); i++) {

                JSONObject currentPermission = permissions.getJSONObject(i);
                this.usertype.addUserPermission(new UserPermissions(
                        currentPermission.getString("permission_name"),
                        currentPermission.getString("permission_description")));
            }

        } catch (MalformedURLException e) {

            Log.e(query, e.getMessage());
            System.out.println(e.getMessage());
        } catch (JSONException e) {

            Log.e(query, e.getMessage());
            System.out.println(e.getMessage());
        } catch (IOException e) {

            Log.e(query, e.getMessage());
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void update_data() {

    }

    @Override
    public void fetch_data_locally() {

        Cursor info = query_engine_instance.execution_with_return(FETCH_USER_LOCALLY,
                new String[] { this.getUsername() });

        if (info.moveToFirst()) {

            this.setEmail(info.getString(info.getColumnIndex("email")));
            this.password = info.getString(info.getColumnIndex("password"));
            this.setUsertype(new UserType(info.getString(info.getColumnIndex("user_type_id")),
                    this.query_engine_instance));
            this.setAuthquestion(info.getString(info.getColumnIndex("auth_question")));
            this.setAuthanswer(info.getString(info.getColumnIndex("auth_answer")));

            info.close();
        }
    }

    //Insert in the local db
    @Override
    public void insert_data_locally() {

        SQLiteStatement sqls = this.query_engine_instance.compile_statement(INSERT_NEW_USER_LOCALLY);

        sqls.bindString(1, this.getUsername());
        sqls.bindString(2, this.getUsertype().getUserTypeName());
        sqls.bindString(1, this.getPassword());
        sqls.bindString(1, this.getEmail());
        sqls.bindString(1, this.getAuthquestion());
        sqls.bindString(1, this.getAuthanswer());

        if (sqls.executeInsert() < 0) {
            System.out.println("Cannot insert user data locally...");
        }

        sqls.close();
    }

    @Override
    public void update_data_locally() {

    }


    public String sync_data(String password, String email, String authquestion, String authanswer) {

        password = (password.charAt(2) == 'a' ? password.replace("$2a$", "$2y$") : password);
        email = (!email.equals(this.email) ? email : "");
        authquestion = (!authquestion.equals(this.authquestion) ? authquestion : "");
        authanswer = (!authanswer.equals(this.authanswer) ? authanswer : "");
        JsonParser answer;

        String query = ServerQueries.getSyncUserInfoString(this.username, password,
                email, authquestion, authanswer);

        try {
            answer = new JsonParser(new HttpRequester(query).processRequest());

            if (answer.fieldNameExists("error")) {
                Log.e(query, answer.getField("error"));
                return answer.getField("error");
            }

            return answer.getField("status");

        } catch (MalformedURLException e) {

            Log.e(query, e.getMessage());
        } catch (JSONException e) {

            Log.e(query, e.getMessage());
        } catch (IOException e) {

            Log.e(query, e.getMessage());
        }
        //if theres a major fuckup, return nothing (should basically never happen
        return "";
    }

    public boolean test_password(String password) {
        System.out.println("password : " + password);
        System.out.println("Hash : " + this.getPassword());
        return BCrypt.checkpw(password, this.getPassword());
    }

}
