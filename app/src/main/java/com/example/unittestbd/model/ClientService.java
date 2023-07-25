package com.example.unittestbd.model;

import android.util.Log;
import com.example.unittestbd.utils.HTTPAcces1;
import okhttp3.Response;
import org.json.JSONArray;

public class ClientService implements HTTPAcces1.OnOperationCompletedListener {

//    private static final String SERVER_URL = "http://10.0.0.160/apicartenfc/serveurclient.php";

    private String serverResponse;
    private HTTPAcces1 httpAccess;

    public ClientService(HTTPAcces1 httpAccess) {
        this.httpAccess = httpAccess;
    }

    public ClientService() {
        super();
    }

    /**
     * Retour du serveur distant.
     *
     * @param
     * @return
     */
//    @Override
//    public void processFinish(String output) {
//        // Stocker la réponse du serveur dans la variable
//        Log.d("serveur", "************" + output);
//        String[] message = output.split("%");
//
//        // dans message[0] : "insert", "read"
//        // dans message[1] : reste du message
//
//        // s'il y a 2 cases
//        if (message.length > 1){
//             if (message[0].equals("Insertion réussie")){
//                 Log.d("Insertion réussie", "************" + message[1]);
//             } else {
//                 if (message[0].equals("Sélection réussie")){
//                     Log.d("Sélection réussie", "************" + message[1]);
//                 } else {
//                     if (message[0].equals("Erreur")){
//                         Log.d("Erreur", "************" + message[1]);
//                     }
//                 }
//             }
//        }
//    }

    public void  send(String action, JSONArray dataJSON) {
        if (httpAccess != null) {
            httpAccess.execute(action, dataJSON.toString());
        }
    }

    // Méthode pour accéder à la réponse du serveur depuis l'extérieur de la classe

    public String getServerResponse() {
        return serverResponse;
    }

    @Override
    public void onOperationCompleted(String output) {
        this.serverResponse = output;
//         Stocker la réponse du serveur dans la variable
        Log.d("serveur", "************" + output);
        String[] message = output.split("%");

        // dans message[0] : "insert", "read"
        // dans message[1] : reste du message

        // s'il y a 2 cases
        if (message.length > 1){
             if (message[0].equals("Insertion réussie")){
                 Log.d("Insertion réussie", "************" + message[1]);
             } else {
                 if (message[0].equals("Sélection réussie")){
                     Log.d("Sélection réussie", "************" + message[1]);
                 } else {
                     if (message[0].equals("Erreur")){
                         Log.d("Erreur", "************" + message[1]);
                     }
                 }
             }
        }
    }
}
