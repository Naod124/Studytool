package com.company.studytool.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.company.studytool.Adapter.ProductsAdapter;
import com.company.studytool.Model.Product;
import com.company.studytool.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BuySellFragment extends Fragment {

    FloatingActionButton fab;
    View view;
    RecyclerView rvProducts;

    private DatabaseReference mRef;

    private ArrayList<Product> productList = new ArrayList<>();

    private ProductsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_store, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initFirebase();
        initViews();
        setListeners();
        initRecyclerView();
        getProducts();
    }

    private void initFirebase(){
        mRef = FirebaseDatabase.getInstance().getReference("items");
    }

    private void initViews(){
        fab = view.findViewById(R.id.fab);
        rvProducts = view.findViewById(R.id.rvProducts);
    }

    private void setListeners(){
        fab.setOnClickListener(v -> {
            startActivity(new Intent(requireActivity(), AddProductActivity.class));
        });
    }

    private void initRecyclerView(){
        adapter = new ProductsAdapter(requireActivity(), productList);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        rvProducts.setLayoutManager(linearLayoutManager);
        rvProducts.setAdapter(adapter);
    }

    private void getProducts(){
        Query query = mRef.orderByChild("isSold").equalTo(false);

        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productList.clear();
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.hasChildren()) {
                        try {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Product product = snapshot.getValue(Product.class);
                                product.setKey(snapshot.getKey());
                                productList.add(product);
                                adapter.notifyDataSetChanged();
                                Log.d("BuySellFragment", "Done");
                            }
                        }catch (Exception ex){
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
