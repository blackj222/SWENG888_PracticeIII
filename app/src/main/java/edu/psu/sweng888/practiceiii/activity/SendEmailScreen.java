package edu.psu.sweng888.practiceiii.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.psu.sweng888.practiceiii.R;
import edu.psu.sweng888.practiceiii.database.ProductDatabaseHelper;
import edu.psu.sweng888.practiceiii.model.Product;
import edu.psu.sweng888.practiceiii.model.ProductAdapter;

public class SendEmailScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendemail_activity);

        RecyclerView recyclerView = findViewById(R.id.selectedRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Product> selectedProducts = (ArrayList<Product>)
                getIntent().getSerializableExtra("selected_products");

        ProductAdapter productAdapter = new ProductAdapter(selectedProducts, true);

        recyclerView.setAdapter(productAdapter);

        Button sendSelectedButton = findViewById(R.id.change_selected_items_button);

        sendSelectedButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainPracticeIII.class);
            intent.putExtra("selected_products", selectedProducts);
            startActivity(intent);
        });
    }
}