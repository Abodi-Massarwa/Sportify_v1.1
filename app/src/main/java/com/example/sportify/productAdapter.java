package com.example.sportify;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class productAdapter extends FirebaseRecyclerAdapter<ProductDetails, productAdapter.product_view_holder> {

    static class product_view_holder extends RecyclerView.ViewHolder {
        FirebaseStorage fbs= FirebaseStorage.getInstance();
        StorageReference s_ref;
        FirebaseAuth ref=FirebaseAuth.getInstance();
        TextView title_text, details_text, price_text, condition_text, utc_text, quantity_text;
        Button buy_button;
        ImageView img;
        String link="a7a";
        public product_view_holder(@NonNull View itemView)
        {
            super(itemView);
            title_text = itemView.findViewById(R.id.title_text);
            details_text = itemView.findViewById(R.id.details_text);
            price_text = itemView.findViewById(R.id.price_text);
            condition_text = itemView.findViewById(R.id.condition_text);
            utc_text = itemView.findViewById(R.id.utc_text);
            img = itemView.findViewById(R.id.image_url);
            buy_button = itemView.findViewById(R.id.buy_button);
            quantity_text=itemView.findViewById(R.id.quantity_text);

            // buy button
            buy_button.setOnClickListener(v -> {
                s_ref=fbs.getReference(utc_text.getText().toString().split(":")[1].trim()+".png");
                Uri image_link;
                Log.d("arabic",utc_text.getText().toString().split(":")[1].trim()+".png");
                s_ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        FirebaseDatabase.getInstance().getReference().child("link").setValue(uri.toString().trim());
                        link=uri.toString().trim();
                        ProductDetails productDetails = new ProductDetails(title_text.getText().toString().trim(), details_text.getText().toString().trim(), condition_text.getText().toString().trim(), price_text.getText().toString().trim(), utc_text.getText().toString().trim(),quantity_text.getText().toString().trim(),link);
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("customers/"+ Objects.requireNonNull(ref.getCurrentUser()).getUid()+"/z_bought products/"+productDetails.getTitle());
                        ref1.setValue(productDetails);


                    }
                });
            });
        }


    }

    public productAdapter(@NonNull FirebaseRecyclerOptions<ProductDetails> options) { super(options);}

    @Override
    protected void onBindViewHolder(@NonNull product_view_holder holder, int position, @NonNull ProductDetails product)
    {
       holder.details_text.setText("Details: " + product.getDetails());
       holder.price_text.setText("Price: " + product.getPrice());
       holder.utc_text.setText("Utc: " + product.getUtc());
       holder.condition_text.setText("Condition: " + product.getCondition());
       holder.title_text.setText("Product name: " + product.getTitle());
       Glide.with(holder.img.getContext()).load(product.getImage_url()).into(holder.img);
    }

    @NonNull
    @Override
    public product_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_product,parent,false);
       return new product_view_holder(view);
    }



}
