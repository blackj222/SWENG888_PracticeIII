package edu.psu.sweng888.practiceiii.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import edu.psu.sweng888.practiceiii.R;
import edu.psu.sweng888.practiceiii.model.Product;
import edu.psu.sweng888.practiceiii.model.ProductAdapter;

public class SendEmailScreen extends AppCompatActivity {

    // List to hold selected products passed from the previous screen
    ArrayList<Product> selectedProducts;

    // Adapter for displaying the selected products
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendemail_activity);

        // Set up the RecyclerView to display the selected products
        RecyclerView recyclerView = findViewById(R.id.selectedRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get the list of selected products passed from MainPracticeIII
        selectedProducts = (ArrayList<Product>)
                getIntent().getSerializableExtra("selected_products");

        // Set up the adapter in read-only mode (true flag disables selection toggles)
        productAdapter = new ProductAdapter(selectedProducts, true);

        // Attach the adapter to the RecyclerView
        recyclerView.setAdapter(productAdapter);

        // Set up the button that allows the user to return and change their selection
        Button sendSelectedButton = findViewById(R.id.change_selected_items_button);
        Button sendEmailButton = findViewById(R.id.send_email_button);


        // when SendSelectedButton is selected, pass the selected products to MainPracticeIII and start it again
        sendSelectedButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainPracticeIII.class);
            intent.putExtra("selected_products", selectedProducts);
            startActivity(intent);
        });

        // when SendSelectedButton is selected, pass the selected products to MainPracticeIII and start it again
        sendEmailButton.setOnClickListener(v -> {
            assert selectedProducts != null;
            implementEmailSend(selectedProducts);
        });
    }

    // Prepares and launches an email intent with product data and image attachments
    private void implementEmailSend(ArrayList<Product> selectedProducts) {
        StringBuilder emailBody = new StringBuilder();
        ArrayList<Uri> imageUris = new ArrayList<>();

        // Loop through each product and collect info and image URIs
        for (Product product : selectedProducts) {
            emailBody.append("Name: ").append(product.getName()).append("\n");
            emailBody.append("Price: $").append(product.getPrice()).append("\n");
            emailBody.append("Seller: ").append(product.getSeller()).append("\n");
            emailBody.append("Description: ").append(product.getDescription()).append("\n\n");

            // Convert the drawable image resource into a URI using FileProvider
            Uri imageUri = getImageUriFromDrawable(product.getPicture(), product.getName() + ".png");
            if (imageUri != null) {
                imageUris.add(imageUri);
            }
        }

        // Build the email intent
        Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"sweng888mobileapps@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Selected Products");
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody.toString());
        emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);

        // Launch email app using ActivityResultLauncher
        emailLauncher.launch(Intent.createChooser(emailIntent, "Send email using:"));
    }

    // Converts a drawable resource into a content URI using FileProvider
    private Uri getImageUriFromDrawable(int drawableId, String fileName) {
        try {
            InputStream inputStream = getResources().openRawResource(drawableId);
            File file = new File(getCacheDir(), fileName);
            FileOutputStream outputStream = new FileOutputStream(file);

            // Copy the raw image data into the cache file
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            // Close streams
            outputStream.close();
            inputStream.close();

            // Return a content URI that can be used as an attachment
            return FileProvider.getUriForFile(
                    this,
                    getPackageName() + ".fileprovider",
                    file
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* Handles the result of the email activity.
     * If the email was sent, shows a toast, clears the product list,
     * and returns to the main activity. */

    private final ActivityResultLauncher<Intent> emailLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                // Notify the user that email was sent
                Toast.makeText(this, "Email sent. Clearing list.", Toast.LENGTH_SHORT).show();

                // Clear the in-memory product list (not from the database)
                selectedProducts.clear();

                // Return to the main screen
                Intent intent = new Intent(this, MainPracticeIII.class);
                intent.putExtra("selected_products", selectedProducts);
                startActivity(intent);
                finish(); // Remove this activity from the back stack
            });
}