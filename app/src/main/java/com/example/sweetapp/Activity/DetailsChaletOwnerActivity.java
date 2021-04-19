package com.example.sweetapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sweetapp.Adapter.AdapterChaletlistOwner;
import com.example.sweetapp.Adapter_Iteam.AdapterIteamChaletList;
import com.example.sweetapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailsChaletOwnerActivity extends AppCompatActivity {
    String productsId;
    ImageView iv_details;
    TextView txt_name_details,txt_address_details,txt_numberisPhone_details,
            txt_salary_details,txt_numOfHours_details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_chalet_owner);



        iv_details=findViewById(R.id.image_detalis);
        txt_name_details=findViewById(R.id.name_details);
        txt_address_details=findViewById(R.id.address_details);
        txt_numberisPhone_details=findViewById(R.id.txt_numberisPhone_details);
        txt_salary_details=findViewById(R.id.salary_details);
        txt_numOfHours_details=findViewById(R.id.txt_numOfHours_details);


        productsId = getIntent().getExtras().getString("Pid");

        getProductsDetails(productsId);









    }
    private void getProductsDetails(String productsId)
    {
        DatabaseReference ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        ProductsRef.child(productsId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    AdapterIteamChaletList products = snapshot.getValue(AdapterIteamChaletList.class);
                    txt_name_details.setText(products.getName_Chalet());
                    txt_salary_details.setText(products.getSalary());
                    txt_address_details.setText(products.getAddress_Chalet());
                    txt_numberisPhone_details.setText(products.getNumofphone());
                    txt_numOfHours_details.setText(products.getNumOfHours());
                    Picasso.get().load(products.getImg_Chalet()).into(iv_details);
                    Toast.makeText(DetailsChaletOwnerActivity.this, "Pid: "+productsId, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}