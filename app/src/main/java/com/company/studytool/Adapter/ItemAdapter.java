package com.company.studytool.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.studytool.Model.Item;
import com.company.studytool.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    private Context mContext;
    private List<Item> items;
    private boolean isFragment;

    public ItemAdapter(Context mContext, List<Item> items, boolean isFragment) {
        this.mContext = mContext;
        this.items = items;
        this.isFragment = isFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.db_item_item, parent , false);
        return new ItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        final User user = items.get(position);
//        holder.btnFollow.setVisibility(View.VISIBLE);
//
//        holder.username.setText(user.getUsername());
//        holder.fullname.setText(user.getName());
//
//        Picasso.get().load(user.getImageurl()).placeholder(R.mipmap.ic_launcher).into(holder.imageProfile);
//
//        isFollowed(user.getId() , holder.btnFollow);
//
//        if (user.getId().equals(firebaseUser.getUid())){
//            holder.btnFollow.setVisibility(View.GONE);
//        }
//
//        holder.btnFollow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (holder.btnFollow.getText().toString().equals(("follow"))){
//                    FirebaseDatabase.getInstance().getReference().child("Follow").
//                            child((firebaseUser.getUid())).child("following").child(user.getId()).setValue(true);
//
//                    FirebaseDatabase.getInstance().getReference().child("Follow").
//                            child(user.getId()).child("followers").child(firebaseUser.getUid()).setValue(true);
//
//                    addNotification(user.getId());
//                } else {
//                    FirebaseDatabase.getInstance().getReference().child("Follow").
//                            child((firebaseUser.getUid())).child("following").child(user.getId()).removeValue();
//
//                    FirebaseDatabase.getInstance().getReference().child("Follow").
//                            child(user.getId()).child("followers").child(firebaseUser.getUid()).removeValue();
//                }
//            }
//        });
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isFragment) {
//                    mContext.getSharedPreferences("PROFILE", Context.MODE_PRIVATE).edit().putString("profileId", user.getId()).apply();
//
//                    ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
//                } else {
//                    Intent intent = new Intent(mContext, MainActivity.class);
//                    intent.putExtra("publisherId", user.getId());
//                    mContext.startActivity(intent);
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView image;
        public TextView name;
        public TextView desc;
        public Button btnFollow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image_profile);
            name = itemView.findViewById(R.id.username);
            desc = itemView.findViewById(R.id.description);
            btnFollow = itemView.findViewById(R.id.btn_buy);
        }
    }
}
