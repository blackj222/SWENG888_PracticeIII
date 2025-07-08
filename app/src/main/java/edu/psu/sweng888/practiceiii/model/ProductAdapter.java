package edu.psu.sweng888.practiceiii.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.psu.sweng888.practiceiii.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private ArrayList<Product> productList;
    private ArrayList<Product> selectedProducts = new ArrayList<>();
    private boolean isSelected = false;

    public ArrayList<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public void addSelectedProducts(ArrayList<Product> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public ProductAdapter(ArrayList<Product> productList, boolean isSelected) {
        this.isSelected = isSelected;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.nameText.setText(product.getName());
        String curPrice = "$" + product.getPrice();
        holder.priceText.setText(curPrice);
        holder.textViewDescription.setText(product.getDescription());
        holder.textViewSeller.setText(product.getSeller());
        holder.checkBox.setEnabled(!isSelected);
        holder.imageViewProduct.setImageResource((product.getPicture()));

        if (!isSelected) {
            holder.itemView.setOnClickListener(v -> {
                checkSelection(holder, product);
            });

            holder.checkBox.setOnClickListener(v -> {
                checkSelection(holder, product);
            });
            holder.checkBox.setChecked(selectedProducts.contains(product));
        } else {
            holder.checkBox.setChecked(productList.contains(product));
        }
    }

    private void checkSelection(@NonNull ViewHolder holder, Product product) {
        if (selectedProducts.contains(product)) {
            selectedProducts.remove(product);
            holder.checkBox.setChecked(false);
        } else {
            selectedProducts.add(product);
            holder.checkBox.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, priceText, textViewSeller, textViewDescription;
        CheckBox checkBox;
        ImageView imageViewProduct;

        ViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.textViewName);
            priceText = itemView.findViewById(R.id.textViewPrice);
            checkBox = itemView.findViewById(R.id.checkbox);
            textViewSeller = itemView.findViewById(R.id.textViewSeller);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
        }
    }
}
