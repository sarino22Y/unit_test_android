package com.example.unittestbd;

import android.app.Application;
import android.content.Context;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.unittestbd.model.Client;
import com.example.unittestbd.service.ClientService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
//    private BD bd;
    private ClientService service;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
//        bd = Room.inMemoryDatabaseBuilder(context, BD.class).build();
//        service = ClientService.getInstance(bd);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.unittestbd", appContext.getPackageName());
    }

    @Test
    public void testInsertClient() {
        Client client = new Client();
        client.setLastname("Rakoto");
        client.setFirstname("Andry");

//        service.insertClient(client);

//        Assert.assertNotNull(client.id);


    }
}