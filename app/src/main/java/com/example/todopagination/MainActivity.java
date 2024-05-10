package com.example.todopagination;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todopagination.response.API;
import com.example.todopagination.response.ResponseTodo;
import com.example.todopagination.response.TodosItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TodoAdapter todoAdapter;
    private ArrayList<TodosItem> todosItemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private boolean isLoading = false;
    private int limit = 30;
    private int page = 1;
    private int total = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        todoAdapter = new TodoAdapter(this, todosItemList);
        recyclerView.setAdapter(todoAdapter);

        setupScrollListener();
        loadTodoData(page);
    }

    private void loadTodoData(int currentPage) {
        if (!isLoading && page <= (total / limit) + 1) {
            progressBar.setVisibility(View.VISIBLE);
            isLoading = true;

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://dummyjson.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            API api = retrofit.create(API.class);
            api.getTodos(currentPage, limit, total).enqueue(new Callback<ResponseTodo>() {
                @Override
                public void onResponse(Call<ResponseTodo> call, Response<ResponseTodo> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        if (currentPage == 1) {
                            todosItemList.clear();
                        }
                        todosItemList.addAll(response.body().getTodos());
                        todoAdapter.notifyDataSetChanged();
                        page++;
                    }
                    isLoading = false;
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<ResponseTodo> call, Throwable t) {
                    Log.d("MainActivity", "API call failed: " + t.getMessage());
                    isLoading = false;
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }

    private void setupScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager != null && !isLoading) {
                    int totalItemCount = layoutManager.getItemCount();
                    int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                    if (totalItemCount <= lastVisibleItem + 3) {
                        loadTodoData(page);
                    }
                }
            }
        });
    }
}
