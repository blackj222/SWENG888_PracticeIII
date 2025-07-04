package edu.psu.sweng888.practiceiii.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.psu.sweng888.practiceiii.R;
import edu.psu.sweng888.practiceiii.model.Product;
import edu.psu.sweng888.practiceiii.model.ProductAdapter;
import edu.psu.sweng888.practiceiii.database.ProductDatabaseHelper;

public class MainPracticeIII extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practiceiii_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<Product> products;
        try (ProductDatabaseHelper dbHelper = new ProductDatabaseHelper(this)) {

            dbHelper.populateProductDatabase();

            this.deleteDatabase("product_database");
            products = dbHelper.getAllProducts();
            if (products.isEmpty()) {
                dbHelper.populateProductDatabase();
                products = dbHelper.getAllProducts();
            }
        }

        ProductAdapter productAdapter = new ProductAdapter(this, products);
        recyclerView.setAdapter(productAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }
}