package com.example.api_rest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.api_rest.interface1.JsonApi;
import com.example.api_rest.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        json = (TextView) findViewById(R.id.jsontext);
        getPost();
    }
    private void getPost(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonApi jsonApi = retrofit.create(JsonApi.class);
        Call<List<Post>> call = jsonApi.getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    json.setText("Codigo: "+response.code());
                    return;
                }
                List<Post> lista = response.body();
                for(Post post: lista){
                    String contenido = "";
            /*        contenido+="userId: "+ post.getUserId() + "\n";
                    contenido+="id: "+ post.getId() + "\n";*/
                    contenido+="title: "+ post.getTitle() + "\n";
                    contenido+="body: "+ post.getBody() + "\n";
                    json.append(contenido);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                json.setText(t.getMessage());
            }
        });
    }
}