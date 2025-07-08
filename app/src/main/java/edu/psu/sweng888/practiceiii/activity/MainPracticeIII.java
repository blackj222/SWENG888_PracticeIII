package edu.psu.sweng888.practiceiii.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.psu.sweng888.practiceiii.R;
import edu.psu.sweng888.practiceiii.model.Product;
import edu.psu.sweng888.practiceiii.model.ProductAdapter;
import edu.psu.sweng888.practiceiii.database.ProductDatabaseHelper;

public class MainPracticeIII extends AppCompatActivity {

    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practiceiii_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewMain);

        ArrayList<Product> products;

        try (ProductDatabaseHelper dbHelper = new ProductDatabaseHelper(this)) {
            // this.deleteDatabase("product_database");
            products = dbHelper.getAllProducts();
            if (products.isEmpty()) {
                dbHelper.populateProductDatabase();
                products = dbHelper.getAllProducts();
            }
        }

        productAdapter = new ProductAdapter(products, false);

        ArrayList<Product> movedSelectedProducts = (ArrayList<Product>)
                getIntent().getSerializableExtra("selected_products");

        if (movedSelectedProducts != null) {
            productAdapter.addSelectedProducts(movedSelectedProducts);
        }

        recyclerView.setAdapter(productAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        Button sendSelectedButton = findViewById(R.id.next_screen_button);

        sendSelectedButton.setOnClickListener(v -> {
            ArrayList<Product> selectedProducts = new ArrayList<>(productAdapter.getSelectedProducts());

            Intent intent = new Intent(this, SendEmailScreen.class);
            intent.putExtra("selected_products", selectedProducts);
            startActivity(intent);
        });
    }
}