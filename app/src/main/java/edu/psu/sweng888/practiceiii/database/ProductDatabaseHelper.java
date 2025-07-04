package edu.psu.sweng888.practiceiii.database;

import edu.psu.sweng888.practiceiii.model.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_SELLER + " TEXT,"
                + COLUMN_PRICE + " REAL,"
                + COLUMN_PICTURE + " TEXT)";
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

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, product.getName());
        values.put(COLUMN_DESCRIPTION, product.getDescription());
        values.put(COLUMN_SELLER, product.getSeller());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_PICTURE, product.getPicture());

        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    // Get all products
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SELLER)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PICTURE))
                );
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

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, product.getName());
        values.put(COLUMN_DESCRIPTION, product.getDescription());
        values.put(COLUMN_SELLER, product.getSeller());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_PICTURE, product.getPicture());

        db.update(TABLE_PRODUCTS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(product.getId())});
        db.close();
    }

    public void populateProductDatabase(){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, "iPhone 14");
        values.put(COLUMN_DESCRIPTION, "Refurbished iPhone");
        values.put(COLUMN_SELLER, "Apple");
        values.put(COLUMN_PRICE, 600.45);
        values.put(COLUMN_PICTURE, "A");
        database.insert(TABLE_PRODUCTS, null, values);

        values = new ContentValues();
        values.put(COLUMN_NAME, "Note 10");
        values.put(COLUMN_DESCRIPTION, "Refurbished Note");
        values.put(COLUMN_SELLER, "Samsung");
        values.put(COLUMN_PRICE, 500.45);
        values.put(COLUMN_PICTURE, "A");
        database.insert(TABLE_PRODUCTS, null, values);
    }
}