package com.example.webconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //private RequestQueue queue;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //queue = Volley.newRequestQueue(this);
        queue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/todos/2", (JSONObject) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.d("Json response", "onResponse: "+ response);
                try {
                    Log.d("Json response", "onResponse: "+ response.getString("title"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, (error) -> {
                Log.d("Json error", "onErrorResponse: "+ error.getMessage());

        });

        // JsonArrayRequest
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/todos", (JSONArray) null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Log.d("Json response", "onResponse: "+ response);
                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Log.d("Json response", "onResponse: "+ jsonObject.getString("id") +" " + jsonObject.getString("title"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, (error) -> {
            Log.d("Json error", "onErrorResponse: "+ error.getMessage());

        });
        queue.add(jsonArrayRequest);
        //queue.add(jsonObjectRequest);
        //queue.add(jsonArrayRequest);

/*

        final TextView textView = (TextView) findViewById(R.id.textView);
// ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://www.google.com";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);


        public static final String TAG = "MyTag";
        StringRequest stringRequest; // Assume this exists.
        RequestQueue requestQueue;  // Assume this exists.

// Set the tag on the request.
        stringRequest.setTag(TAG);

// Add the request to the RequestQueue.
        requestQueue.add(stringRequest);

        @Override
protected void onStop () {
    super.onStop();
    if (requestQueue != null) {
        requestQueue.cancelAll(TAG);
    }
}


*/

    }

    }