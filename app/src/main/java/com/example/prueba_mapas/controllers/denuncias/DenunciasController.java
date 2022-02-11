package com.example.prueba_mapas.controllers.denuncias;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseListener;
import com.google.gson.Gson;

import com.example.prueba_mapas.models.denuncias.Denuncia;

import org.json.JSONObject;

  import okhttp3.Response;

public class DenunciasController {
    private static final String TAG = DenunciasController.class.getSimpleName();
    private static DenunciasController instance;
    public static final String controller = "Denuncias";

    public DenunciasController(Class<Denuncia> clazz, String controller) {
        //super(clazz, controller);
    }

    public static DenunciasController getInstance() {
        if (instance == null) {
            instance = new DenunciasController(Denuncia.class, controller);
        }
        return instance;
    }

    public void cargarDenuncia(Denuncia denuncia, final DenunciaCallback callback){
        String url = "http://192.168.3.14:9091/api/Denuncias/addDenuncia";
        JSONObject body = null;
        try {
            Gson gson = new Gson();
            String json = gson.toJson(denuncia);
            Log.d(TAG, "cargarDenuncia > json: " + json);
            body = new JSONObject(json);
        } catch (Exception e) {
            Log.e(TAG, "cargarDenuncia > error: " + e.getMessage());
            return;
        }
        JSONObject finalBody = body;

        AndroidNetworking.post(url)
                .addJSONObjectBody(finalBody)
                .setPriority(Priority.HIGH)
                .build()
                .getAsOkHttpResponse(new OkHttpResponseListener() {
                    @Override
                    public void onResponse(Response response) {
                        Log.d(TAG, "cargarDenuncia > successfully");
                        if (callback != null) callback.onResponse("OK");
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e(TAG, "cargarDenuncia > onError: " + (anError != null ? anError.getMessage() : "Detalle de error no disponible."));
                        if (anError != null) anError.printStackTrace();
                        if (callback != null) callback.onError(anError);
                    }
                });
    }
}
