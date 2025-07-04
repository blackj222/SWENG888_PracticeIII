package edu.psu.sweng888.practiceiii.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import edu.psu.sweng888.practiceiii.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> productList;
    private Context context;
    private List<Product> selectedProductList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageViewProduct;
        TextView textViewName, textViewSeller, textViewDescription, textViewPrice;

        public ViewHolder(View itemView) {
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

