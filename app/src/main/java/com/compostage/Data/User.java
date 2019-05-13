package com.compostage.Data;

import android.util.Log;
import android.widget.Toast;

import com.compostage.Exceptions.InvalidServerQuery;
import com.compostage.ServerQueries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;

public class User implements IDataBase {

    private String username;
    private UserType usertype;
    private String password;
    private String email;
    private String authquestion;
    private String authanswer;


    public User(String username) {
        this.username = username;
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
        this.password = password;
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

    //fetch from local db
    @Override
    public void fetch_data() throws InvalidServerQuery {

        String query = String.format(ServerQueries.GET_USER_INFO, this.username);

        try {

            JsonParser answer = new JsonParser(new HttpRequester(query).processRequest());
            JSONObject userType = answer.getObject("user_type");

            if (answer.fieldNameExists("error")) {
                throw new InvalidServerQuery(answer.getField("error"));
            }

            this.password = answer.getField("user_password");
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

    //Insert in the local db
    @Override
    public void insert_data() throws InvalidServerQuery {

    }

    @Override
    public void fetch_data_locally() {

    }

    @Override
    public void insert_data_locally() {

    }



    public String sync_data(String password, String email, String authquestion, String authanswer) {

        password = (!password.equals(this.password) ? password : "");
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
}
