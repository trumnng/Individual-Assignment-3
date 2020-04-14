package com.example.individualassignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.individualassignment3.Entities.CNResponse;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private Button mButton;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.btnQuote);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuote();
            }
        });


    }

    //ShowQuote() method called when button is clicked
    private void showQuote() {
        //Create the instance of retrofit and parse retrieved json using the gson deserialiser
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.chucknorris.io").addConverterFactory(GsonConverterFactory.create()).build();

        //Get service and call the object for response
        CNService service = retrofit.create(CNService.class);
        Call<CNResponse> jokeCall = service.getValue();

        //Call enqueue method to ensure no NetworkOnMainThread Exception
        jokeCall.enqueue(new Callback<CNResponse>() {
            //On Success setText
            @Override
            public void onResponse(Call<CNResponse> call, Response<CNResponse> response) {
                Log.d(TAG, "onResponse: SUCCESS");
                mTextView = findViewById(R.id.txvQuote);
                mTextView.setText(response.body().getValue());
            }

            //On Failure Send Failure message to Logcat
            @Override
            public void onFailure(Call<CNResponse> call, Throwable t) {
                Log.d(TAG, "onResponse: FAILURE");
            }
        });
    }
}
