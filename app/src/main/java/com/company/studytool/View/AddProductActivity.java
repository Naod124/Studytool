package com.company.studytool.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.company.studytool.Model.Product;
import com.company.studytool.Model.StudentDetails;
import com.company.studytool.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.UUID;

public class AddProductActivity extends AppCompatActivity {

    Toolbar toolbar;
    FrameLayout flPickImg;
    ImageView imageView;
    EditText etTitle;
    EditText etDescription;
    EditText etEmail;
    EditText etPhone;
    Button btnSubmit;

    Uri imageUri;
    String downloadUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        initViews();
        initToolbar();
        initListeners();
    }

    private void initViews(){
        toolbar = findViewById(R.id.toolbar);
        flPickImg = findViewById(R.id.flPickImg);
        imageView = findViewById(R.id.ivImg);
        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initListeners(){
        flPickImg.setOnClickListener(v -> {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(2, 1)
                    .start(this);
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm()){
                    createProduct();
                }
            }
        });
    }

    private boolean validateForm(){

        boolean valid = true;

        String title = etTitle.getText().toString();
        if(TextUtils.isEmpty(title)){
            etTitle.setError("Required");
            valid = false;
        }else{
            etTitle.setError(null);
        }

        String description = etDescription.getText().toString();
        if(TextUtils.isEmpty(description)){
            etDescription.setError("Required");
            valid = false;
        }else{
            etDescription.setError(null);
        }

        String email = etEmail.getText().toString();
        if(TextUtils.isEmpty(email)){
            etEmail.setError("Required");
            valid = false;
        }else{
            etEmail.setError(null);
        }

        String phone = etPhone.getText().toString();
        if(TextUtils.isEmpty(phone)){
            etPhone.setError("Required");
            valid = false;
        }else{
            etPhone.setError(null);
        }

        if(imageUri == null){
            Toast.makeText(this, "Please select product image", Toast.LENGTH_SHORT).show();
            return false;
        }

        return valid;
    }

    private void createProduct() {
        if(validateForm()){
//            Config.showProgressDialog(context, "Creating your profile...");

            final StorageReference reference = FirebaseStorage.getInstance().getReference().child(UUID.randomUUID() + ".jpg");
            Task uploadTask = reference.putFile(imageUri);

            Task<Uri> urlTask = uploadTask.continueWithTask((Continuation<UploadTask.TaskSnapshot, Task<Uri>>) task -> {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return reference.getDownloadUrl();
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        downloadUrl = task.getResult().toString();

                        Product product = new Product();

                        product.setTitle(etTitle.getText().toString());
                        product.setDescription(etDescription.getText().toString());
                        product.setImg(downloadUrl);
                        product.setUsername(StudentDetails.username);
                        product.setEmail(etEmail.getText().toString());
                        product.setPhone(etPhone.getText().toString());
                        product.setIsSold(false);

                        FirebaseDatabase.getInstance().getReference("items").push()
                                .setValue(product).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(AddProductActivity.this, "Product added", Toast.LENGTH_SHORT).show();
                                    finish();

                                } else {
                                    Toast.makeText(AddProductActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(AddProductActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();
                imageView.setImageURI(imageUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

}