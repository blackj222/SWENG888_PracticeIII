package edu.psu.sweng888.practiceiii.model;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.psu.sweng888.practiceiii.R;
import edu.psu.sweng888.practiceiii.database.ProductDatabaseHelper;

public class ProductListFragment extends Fragment {
    private RecyclerView mProductRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        mProductRecyclerView = (RecyclerView) view
                .findViewById(R.id.product_recycler_view);
        mProductRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // updateUI();

        return view;
    }

//    private void updateUI() {
//
//        List<Product> products;
//        try (ProductDatabaseHelper dbHelper = new ProductDatabaseHelper(this)) {
//
//            dbHelper.populateProductDatabase();
//
//            dbHelper.deleteDatabase("product_database");
//            products = dbHelper.getAllProducts();
//            if (products.isEmpty()) {
//                dbHelper.populateProductDatabase();
//                products = dbHelper.getAllProducts();
//            }
//        }
//
//        ProductAdapter productAdapter = new ProductAdapter(this, products);
//        recyclerView.setAdapter(productAdapter);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
//                LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//    }

    private class ProductHolder extends RecyclerView.ViewHolder {
        public ProductHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_product, parent, false));
        }
    }

    public class ProductAdapter extends RecyclerView.Adapter<edu.psu.sweng888.practiceiii.model.ProductAdapter.ViewHolder> {

        private List<Product> productList;
        private Context context;
        private List<Product> selectedProductList;

        public ProductAdapter(Context context, List<Product> productList) {
            this.context = context;
            this.productList = productList;
        }

        @NonNull
        @Override
        public edu.psu.sweng888.practiceiii.model.ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.fragment_product, parent, false);
            return new edu.psu.sweng888.practiceiii.model.ProductAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(edu.psu.sweng888.practiceiii.model.ProductAdapter.ViewHolder holder, int position) {
            Product product = productList.get(position);

            String price = "$" + product.getPrice();
            holder.textViewName.setText(product.getName());
            holder.textViewSeller.setText(product.getSeller());
            holder.textViewDescription.setText(product.getDescription());
            holder.textViewPrice.setText(price);

            // Load image (from drawable for now)
            int imageId = context.getResources().getIdentifier(product.getPicture(), "drawable", context.getPackageName());
            holder.imageViewProduct.setImageResource(imageId);
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }

        public List<Product> getSelectedProductList(){
            return this.selectedProductList;
        }

        public class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageView imageViewProduct;
            TextView textViewName, textViewSeller, textViewDescription, textViewPrice;

            public ProductHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
                textViewName = itemView.findViewById(R.id.textViewName);
                textViewSeller = itemView.findViewById(R.id.textViewSeller);
                textViewDescription = itemView.findViewById(R.id.textViewDescription);
                textViewPrice = itemView.findViewById(R.id.textViewPrice);
            }

            @Override
            public void onClick(View v) {

            }
        }
}
}
