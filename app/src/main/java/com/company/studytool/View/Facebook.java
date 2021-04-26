package com.company.studytool.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.company.studytool.R;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Facebook#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Facebook extends Fragment {

    ImageView imageView;
    ShareButton photo;
    ShareButton link;
    CallbackManager callbackManager;
    LoginButton loginButton;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Facebook() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Facebook.
     */
    // TODO: Rename and change types and number of parameters
    public static Facebook newInstance(String param1, String param2) {
        Facebook fragment = new Facebook();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_facebook, container, false);

        loginButton= root.findViewById(R.id.lb_login);

        photo= root.findViewById(R.id.share);
        link= root.findViewById(R.id.shareLink);
        imageView= root.findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.photostudy);
        callbackManager= CallbackManager.Factory.create();

        return root;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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