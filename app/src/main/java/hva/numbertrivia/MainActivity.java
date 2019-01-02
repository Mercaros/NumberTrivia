package hva.numbertrivia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton generateNumber;
    private List<Number> numberList = new ArrayList<>();
    private NumberAdapter numberAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        generateNumber = findViewById(R.id.generateNumber);
        updateUI();
        generateNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
            }
        });
    }


    private void requestData() {
        NumberApiService service = NumberApiService.retrofit.create(NumberApiService.class);
        /**
         * Make an a-synchronous call by enqueing and definition of callbacks.
         */
        Call<Number> call = service.getRandomTrivia();
        call.enqueue(new Callback<Number>() {
            @Override
            public void onResponse(Call<Number> call, Response<Number> response) {
                if (response.isSuccessful()) {
                    Number number = response.body();
                    numberList.add(number);
                    updateUI();
                    Log.d("MainActivity", "posts loaded from API");
                }
            }

            @Override
            public void onFailure(Call<Number> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });

    }

    private void updateUI() {
        if (numberAdapter == null) {
            numberAdapter = new NumberAdapter(numberList);
            recyclerView.setAdapter(numberAdapter);
        } else {
            //Refresh list
            numberAdapter.swapList(numberList);
        }
    }
}
