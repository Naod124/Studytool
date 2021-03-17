package com.company.studytool.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.company.studytool.R;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;


public class FacebookActivity extends AppCompatActivity {

    ImageView imageView;
    ShareButton photo;
    ShareButton link;
    CallbackManager callbackManager;
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_facebook);

        loginButton= findViewById(R.id.lb_login);

        photo= findViewById(R.id.share);
        link= findViewById(R.id.shareLink);
        imageView= findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.photostudy);
        callbackManager= CallbackManager.Factory.create();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);

        ShareLinkContent shareLinkContent= new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://github.com/Naod124/Studytool.git"))
                .setShareHashtag(new ShareHashtag.Builder()
                        .setHashtag("#Study Tool App").build()).build();
        link.setShareContent(shareLinkContent);

        BitmapDrawable bitmapDrawable= (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap= bitmapDrawable.getBitmap();
        SharePhoto sharePhoto= new SharePhoto.Builder()
                .setBitmap(bitmap)
                .build();

        SharePhotoContent sharePhotoContent= new SharePhotoContent.Builder()
                .addPhoto(sharePhoto)
                .build();
        photo.setShareContent(sharePhotoContent);


    }



}