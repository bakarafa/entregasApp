package com.vianna.entregasapp.service.task;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.vianna.entregasapp.model.dto.InputLoginDTO;
import com.vianna.entregasapp.model.dto.LoginDTO;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

public class LoginTask extends AsyncTask<InputLoginDTO, Void, LoginDTO> {

    /*@Override
    protected UserDTO doInBackground(InputLoginDTO... inputLoginDTOS) {
        /////
        return null;
    }*/

    @Override
    protected LoginDTO doInBackground(InputLoginDTO... dados) {
//        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        //String data = "";

        StringBuilder result = new StringBuilder();

        HttpsURLConnection httpURLConnection = null;
        try {

            httpURLConnection = (HttpsURLConnection) new URL("https://api.entregas.davesmartins.com.br/oauth/token").openConnection();

            // Sets the request method for the URL
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            httpURLConnection.setRequestProperty("Accept","application/json");

            //Login e senha da aplicação
            httpURLConnection.setRequestProperty("Authorization","Basic YXBwOmFzZHNAQXNkIzIzMjQzRGFz");////


            // Tells the URL that I am sending a POST request body
            httpURLConnection.setDoOutput(true);
            // Tells the URL that I want to read the response data
            httpURLConnection.setDoInput(true);

            // JSON object for the REST API
            String param = "grant_type=password&username="+dados[0].getLogin()+"&password="+dados[0].getSenha();
            byte[] paramBytes = param.getBytes(StandardCharsets.UTF_8);




//            JSONObject jsonParam = new JSONObject();
//            jsonParam.put("username", params[0].getLogin());
//            jsonParam.put("password", params[0].getSenha());
//            jsonParam.put("grant_type", "password");

            Log.i("PARAMETRO", param);

            // To write primitive Java data types to an output stream in a portable way
            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            // Writes out a byte to the underlying output stream of the data posted from .execute function
            wr.write(paramBytes);

            // Flushes the jsonParam to the output stream
            wr.flush();
            wr.close();

            // // Representing the input stream
            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            // reading the input stream / response from the url
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Disconnects socket after using
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        Log.i("TAG", result.toString());

        //Transforma String GSON para objeto -- importar biblioteca GSON
//        XXXXXXXX obj = new Gson().fromJson(resp.toString(),XXXX);
        LoginDTO obj = new Gson().fromJson(result.toString(), LoginDTO.class);

        return obj;
    }
}
