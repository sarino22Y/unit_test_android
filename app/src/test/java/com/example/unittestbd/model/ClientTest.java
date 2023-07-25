package com.example.unittestbd.model;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertTrue;
import com.example.unittestbd.service.ClientService;

@RunWith(RobolectricTestRunner.class)
public class ClientTest {


    @Before
    public void setUp() {
    }

    /**
     * Persistance de nouveau client.
     */
    @Test
    public void testInsertClient() {
        Client client = new Client();
        client.setLastname("Rakoto");
        client.setFirstname("Andry");
        client.setSolde(100);

        assertThat(client.isPersistable()).isTrue();
        assertThat(client.anomalies.size()).isEqualTo(0);
        assertThat(client.getLastname()).isEqualTo("Rakoto");
        assertThat(client.getFirstname()).isEqualTo("Andry");
        assertThat(client.getSolde()).isEqualTo(100);
        assertThat(client.isNew()).isTrue();

        /*ClientsRepository repoClient = new ClientsRepository();
        repoClient.Save(client);

        Client clientSaved = repoClient.getById(1);
        assertThat(clientSaved).isInstanceOf(Client.class);
        assertThat(clientSaved.getLastname()).isEqualTo("Rakoto");
        assertThat(clientSaved.getFirstname()).isEqualTo("Andry");
        assertThat(clientSaved.getSolde()).isEqualTo(100);*/
    }

    /**
     * Test client non persistable si nom et prenom vide.
     * L'anomalie contient : Nom vide, Prénom vide.
     */
    @Test
    public void testNameFirstnameVideNonPersistable() {
        Client client = new Client();
        client.setLastname("");
        client.setFirstname("");
        client.setSolde(100);

        assertThat(client.isPersistable()).isFalse();
        assertThat(client.anomalies.get(0)).isEqualTo("Nom vide");
        assertThat(client.anomalies.get(1)).isEqualTo("Prénom vide");
        assertThat(client.anomalies.size()).isEqualTo(2);
    }

    /**
     * Test client non persistable si nom vide.
     * L'anomalie contient : Nom vide.
     */
    @Test
    public void testNameVideNonPersistable() {
        Client client = new Client();
        client.setLastname("");
        client.setFirstname("Andry");
        client.setSolde(100);

        assertThat(client.isPersistable()).isFalse();
        assertThat(client.anomalies.get(0)).isEqualTo("Nom vide");
        assertThat(client.anomalies.size()).isEqualTo(1);
    }

    /**
     * Test client non persistable si prenom vide.
     * L'anomalie contient : Prénom vide.
     */
    @Test
    public void testFirstNameVideNonPersistable() {
        Client client = new Client();
        client.setLastname("Rakoto");
        client.setFirstname("");
        client.setSolde(100);

        assertThat(client.isPersistable()).isFalse();
        assertThat(client.anomalies.get(0)).isEqualTo("Prénom vide");
        assertThat(client.anomalies.size()).isEqualTo(1);
    }

    /**
     * Insertion de client dans base de donnée MySql.
     */
    @Test
    public void testInsertAndRetriviewClientMySql() throws IOException, JSONException {

        Client client = new Client("Dupont", "Jean", 100);

        // Convertissez l'objet Client en JSONArray
        JSONArray clientJsonArray = client.convertToJSONArray();

        // Créez le corps de la requête HTTP avec les données du client
        // RequestBody requestBody = RequestBody.create(JSON, clientJsonArray.toString());

        String host1 = "http://10.0.0.160";
        String host2 = "http://192.168.110.49";

        String BASE_URL = host1 + "/apicartenfc/serveurclient.php?action=create&dataclient=" + clientJsonArray.toString();
        // Créez une requête HTTP POST vers le serveur PHP avec les données du client
        Request request = new Request.Builder().url(BASE_URL)
                //.post(requestBody)
                .build();

        // Créez un client OkHttp pour effectuer la requête
        OkHttpClient clientOkHttp = new OkHttpClient();

        // Exécutez la requête de manière synchrone
        // (uniquement à des fins de test, évitez de le faire dans le code réel)
        Response response = clientOkHttp.newCall(request).execute();

        // Vérifiez si la requête a réussi (code HTTP 200)
        assertTrue(response.isSuccessful());

        // Vérifiez la réponse du serveur.
        String responseBody = response.body().string();
        String[] message = responseBody.split("%");
        JSONObject jsonObject = new JSONObject(message[1]);
        assertThat(message[0]).isEqualTo("Insertion réussie");
        assertThat(jsonObject.getString("LastName")).isEqualTo(client.getLastname());
        assertThat(jsonObject.getString("FirstName")).isEqualTo(client.getFirstname());
        assertThat(jsonObject.getInt("Solde")).isEqualTo(client.getSolde());
    }

    @Test
    public void sendTest() throws JSONException {
        Client client = new Client("Dupont123", "Jean1", 100);
        JSONArray clientJsonArray = client.convertToJSONArray();

        ClientService clientService = new ClientService();

        clientService.execute("?action=create&dataclient=" + clientJsonArray.toString());

        String responseBody = clientService.responseServer;
        String[] message = responseBody.split("%");
        JSONObject jsonObject = new JSONObject(message[1]);
        assertThat(message[0]).isEqualTo("Insertion réussie");
        assertThat(jsonObject.getString("LastName")).isEqualTo(client.getLastname());
        assertThat(jsonObject.getString("FirstName")).isEqualTo(client.getFirstname());
        assertThat(jsonObject.getInt("Solde")).isEqualTo(client.getSolde());
    }
}