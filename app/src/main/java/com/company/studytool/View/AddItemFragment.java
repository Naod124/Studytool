package com.company.studytool.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddItemFragment extends Fragment {

    private TextInputEditText name, quantity, price, url, desc, section;
    private MaterialButton buttonAdd;

    public AddItemFragment() {
        // Required empty public constructor
    }

    public static com.example.firebasepractise.AddItemFragment newInstance() {
        com.example.firebasepractise.AddItemFragment fragment = new com.example.firebasepractise.AddItemFragment();
//        Bundle args = new Bundle();
//          here we can add parameters and retrieve them from
//                      @Override
//                   public void onCreate(Bundle savedInstanceState){...}
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);
        name = view.findViewById(R.id.additems_item_name);
        quantity = view.findViewById(R.id.additems_item_quantity);
        price = view.findViewById(R.id.additems_item_price);
        url = view.findViewById(R.id.additems_item_url);
        desc = view.findViewById(R.id.additems_item_description);
        buttonAdd = view.findViewById(R.id.additems_button_add);
        section = view.findViewById(R.id.additems_item_section);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!areEmpty()){
                    HashMap<String, Object> itemProps = new HashMap<>(5);
                    itemProps.put("Name", name.getText().toString());
                    itemProps.put("Quantity", quantity.getText().toString());
                    itemProps.put("Price", Integer.parseInt(price.getText().toString()));
                    itemProps.put("URL", url.getText().toString());
                    itemProps.put("Description", desc.getText().toString());

                    FirebaseDatabase.getInstance().getReference().child(section.getText().toString()).push().updateChildren(itemProps);
                }
            }
        });

        return view;
    }

    private boolean areEmpty(){
        return section.getText().length() == 0 || name.getText().length() == 0 || quantity.getText().length() == 0 || price.getText().length() == 0 || desc.getText().length() == 0;
    }
}