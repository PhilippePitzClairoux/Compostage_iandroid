package com.compostage;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.compostage.Data.User;
import com.compostage.Exceptions.InvalidServerQuery;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.UnknownHostException;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class UserTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void fetch_data_locally() {

        db_query_engine engine = new db_query_engine(InstrumentationRegistry.getTargetContext());
        User tmp = new User("admin", engine);

        tmp.fetch_data_locally();
        assertTrue(tmp.is_loaded_properly());

    }

    //we check for this because in order for this feature to actually work,
    // we need alot of configuration
    @Test(expected = UnknownHostException.class)
    public void fetch_data_remotely() throws IOException, InvalidServerQuery {

        db_query_engine engine = new db_query_engine(InstrumentationRegistry.getTargetContext());
        User tmp = new User("admin", engine);

        tmp.fetch_data();
    }

    @Test
    public void test_password() {

        User tmp = new User("test", null);

        tmp.setPassword("test");
        assertTrue(tmp.test_password("test"));
    }

}
