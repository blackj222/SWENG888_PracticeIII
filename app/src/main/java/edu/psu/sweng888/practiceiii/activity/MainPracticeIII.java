package edu.psu.sweng888.practiceiii.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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

        // Initialize and use the database helper to fetch products
        try (ProductDatabaseHelper dbHelper = new ProductDatabaseHelper(this)) {

            // If the database is empty, populate it and retrieve again
            products = dbHelper.getAllProducts();
            if (products.isEmpty()) {
                dbHelper.populateProductDatabase();
                products = dbHelper.getAllProducts();
            }
        }

        // Initialize the adapter with the list of products
        productAdapter = new ProductAdapter(products, false);

        // Check if any selected products were passed from another activity
        ArrayList<Product> movedSelectedProducts = (ArrayList<Product>)
                getIntent().getSerializableExtra("selected_products");

        // If any selected products were passed, add them to the adapter's list
        if (movedSelectedProducts != null) {
            productAdapter.addSelectedProducts(movedSelectedProducts);
        }

        // Set up the RecyclerView with the adapter and a vertical layout manager
        recyclerView.setAdapter(productAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Set up the button that sends selected products to the next screen
        Button sendSelectedButton = findViewById(R.id.next_screen_button);

        sendSelectedButton.setOnClickListener(v -> {
            // Retrieve selected products from the adapter
            ArrayList<Product> selectedProducts = new ArrayList<>(productAdapter.getSelectedProducts());

            // Create an intent to launch the SendEmailScreen activity
            Intent intent = new Intent(this, SendEmailScreen.class);
            intent.putExtra("selected_products", selectedProducts); // Pass selected products to the new activity
            startActivity(intent); // Start the new activity
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Delete the product database when the activity is destroyed
        this.deleteDatabase("product_database");
    }
}