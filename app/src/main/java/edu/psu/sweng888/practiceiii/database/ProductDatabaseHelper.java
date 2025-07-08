package edu.psu.sweng888.practiceiii.database;

import edu.psu.sweng888.practiceiii.R;
import edu.psu.sweng888.practiceiii.model.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "product_database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PRODUCTS = "products";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_SELLER = "seller";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_PICTURE = "picture";

    public ProductDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + COLUMN_ID + " TEXT PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_SELLER + " TEXT,"
                + COLUMN_PRICE + " REAL,"
                + COLUMN_PICTURE + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    // Called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    // Add a product
    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getContentValues(product);
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    // Get all products
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = parseProduct(cursor);
                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productList;
    }

    // Delete a product by ID
    public void deleteProduct(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Update a product
    public void updateProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_PRODUCTS, getContentValues(product), COLUMN_ID + " = ?", new String[]{String.valueOf(product.getId())});
        db.close();
    }

    public ContentValues getContentValues(Product product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, product.getId().toString());
        values.put(COLUMN_NAME, product.getName());
        values.put(COLUMN_DESCRIPTION, product.getDescription());
        values.put(COLUMN_SELLER, product.getSeller());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_PICTURE, product.getPicture());

        return values;
    }

    public void populateProductDatabase(){
        this.addProduct(new Product("iPhone 14", "Refurbished Apple iPhone", "Apple", 600.45, R.drawable.iphone14));
        this.addProduct(new Product("Note 10", "Refurbished Samsung Note", "Samsung", 500.45, R.drawable.note10));
        this.addProduct(new Product("Samsung Z Fold 4", "Refurbished Samsung Z Fold", "Samsung", 700.45, R.drawable.samsung_z_fold4));
        this.addProduct(new Product("Note 10", "Refurbished Google Pixel", "Google", 450.45, R.drawable.google_pixel5));
    }

    private Product parseProduct(Cursor cursor) {
        UUID id = UUID.fromString(cursor.getString(cursor.getColumnIndexOrThrow("id")));
        String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
        String seller = cursor.getString(cursor.getColumnIndexOrThrow("seller"));
        double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
        int picture = cursor.getInt(cursor.getColumnIndexOrThrow("picture"));
        return new Product(id, name, description, seller, price, picture);
    }

    public ArrayList<Product> getByCategory(String value, String comparator, String columnName) {
        ArrayList<Product> products = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                "Product",
                null,
                columnName + " " + comparator + "  ?",
                new String[]{ value },
                null, null, null
        );

        while (cursor.moveToNext()) {
            products.add(parseProduct(cursor));
        }
        cursor.close();
        return products;
    }
}