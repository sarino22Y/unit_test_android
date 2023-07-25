package com.example.unittestbd.controler;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.example.unittestbd.model.Client;
import com.example.unittestbd.service.ClientService;
import com.example.unittestbd.utils.AsyncResponse;
import org.json.JSONArray;

public class Controle {
    private static Controle instance = null;

    private static ClientService service;

    Client client;

    private static Context context = null;

    private Controle(Context context) {
        this.context = context;
    }

    private Controle() {
        super();
    }

    /**
     * Création de l'instance.
     * @return
     */
    public static final Controle getInstance(Context context) {
        if (Controle.instance == null) {
            instance = new Controle();
        }
        return instance;
    }

    public void createClient(Context context, String lastname, String firstname, int solde){
        try {
            client = new Client();
            client.setLastname(lastname);
            client.setFirstname(firstname);
            client.setSolde(solde);

            JSONArray clientJsonArray = client.convertToJSONArray();
            String params = "?action=create&dataclient=" + clientJsonArray.toString();

//          Créez une nouvelle instance de ClientService à chaque l'exécuter.
            ClientService service = new ClientService(new AsyncResponse() {
                @Override
                public void processFinish(String response) {
                    // Traitez la réponse du serveur.
                    Log.d("serveur", "************" + response);
                    String[] message = response.split("%");

                    Log.d("serveur", "************" + message[0]);
                    Log.d("serveur", "************" + message[1]);

                    // dans message[0] : "insert", "read"
                    // dans message[1] : reste du message

                    // s'il y a 2 cases
                    if (message.length > 1){
                        if (message[0].equals("catch")) {
                            Toast.makeText(context, message[1], Toast.LENGTH_SHORT).show();
                            Log.d("Connexion impossible", "************" + message[1]);
                        }
                         if (message[0].equals("Insertion réussie")){
                             Toast.makeText(context, message[0] + " : " + message[1], Toast.LENGTH_SHORT).show();
                             Log.d("Insertion réussie", "************" + message[1]);
                         } else {
                             if (message[0].equals("Sélection réussie")){
                                 Toast.makeText(context, message[0] + " : " + message[1], Toast.LENGTH_SHORT).show();
                                 Log.d("Sélection réussie", "************" + message[1]);
                             } else {
                                 if (message[0].equals("Erreur")){
                                     Toast.makeText(context, message[0] + " : " + message[1], Toast.LENGTH_SHORT).show();
                                     Log.d("Erreur", "************" + message[1]);
                                 }
                             }
                         }
                    }
                }
            });
            service.execute(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
