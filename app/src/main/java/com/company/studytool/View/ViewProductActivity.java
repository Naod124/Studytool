package com.company.studytool.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.company.studytool.Model.Product;
import com.company.studytool.Model.StudentDetails;
import com.company.studytool.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ViewProductActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView productImg;
    TextView productTitle;
    TextView productDescription;
    TextView productPrice;
    CheckBox checkBox;

    FloatingActionButton btnCall;
    FloatingActionButton btnEmail;

    Product product;

    private int permissionRequestCall = 1;

    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        getIntentData();
        initViews();
        initToolbar();
        setData();
        initFirebase();
        initListeners();
    }

    private void initFirebase(){
        mRef = FirebaseDatabase.getInstance().getReference("items");
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getIntentData(){
        product = (Product) getIntent().getSerializableExtra("product");
    }

    private void initViews(){
        toolbar = findViewById(R.id.toolbar);
        productImg = findViewById(R.id.productImg);
        productTitle = findViewById(R.id.tvProductName);
        productDescription = findViewById(R.id.tvProductDescription);
        productPrice = findViewById(R.id.tvPrice);
        btnCall = findViewById(R.id.btnCall);
        btnEmail = findViewById(R.id.btnEmail);
        checkBox = findViewById(R.id.checkbox);
    }

    private void setData(){
        Glide.with(this)
                .load(product.getImg())
                .into(productImg);

        productTitle.setText(product.getTitle());
        productDescription.setText(product.getDescription());

        checkBox.setChecked(product.getIsSold());

        if(product.getUsername().equals(StudentDetails.username)){
            checkBox.setVisibility(View.VISIBLE);
        }else{
            checkBox.setVisibility(View.GONE);
        }

//        productPrice.setText(product);
    }

    private void initListeners(){
        btnCall.setOnClickListener(v -> {
            call();
        });

        btnEmail.setOnClickListener(v -> {
            email();
        });

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {

            mRef.child(product.getKey()).removeValue();

            /*Map map = new HashMap();
            map.put("isSold", isChecked);
            mRef.child(product.getKey()).updateChildren(map).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()){
                        Log.d("ViewProductActivity", "Updated");
                    }else{
                        Log.d("ViewProductActivity", "Error");
                    }
                }
            });*/
        });
    }

    private void call() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
        ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    permissionRequestCall
            );

        }else{
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + product.getPhone()));
            startActivity(intent);
        }
    }

    private void email(){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{product.getEmail()});
//        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
//        i.putExtra(Intent.EXTRA_TEXT   , "body of email");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ViewProductActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == permissionRequestCall){
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + product.getPhone()));
                startActivity(intent);
            }
        }
    }
}