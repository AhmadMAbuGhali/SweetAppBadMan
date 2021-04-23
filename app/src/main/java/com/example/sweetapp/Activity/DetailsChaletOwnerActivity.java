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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailsChaletOwnerActivity extends AppCompatActivity {
    String productsId;
    AdapterIteamChaletList list;
    ImageView iv_details;
    FirebaseAuth mAuth;
    TextView txt_name_details,txt_address_details,txt_numberisPhone_details,
            txt_salary_details,txt_numOfHours_details;
    String Uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_chalet_owner);
        mAuth = FirebaseAuth.getInstance();

        Uid = mAuth.getCurrentUser().getUid();
        iv_details=findViewById(R.id.image_detalis);
        txt_name_details=findViewById(R.id.name_details);
        txt_address_details=findViewById(R.id.address_details);
        txt_numberisPhone_details=findViewById(R.id.txt_numberisPhone_details);
        txt_salary_details=findViewById(R.id.salary_details);
        txt_numOfHours_details=findViewById(R.id.txt_numOfHours_details);


//        productsId = getIntent().getExtras().getString("Pid");

        getProductsDetails(Uid);









    }
    private void getProductsDetails(String Uid)
    {

        DatabaseReference ProductsRef = FirebaseDatabase.getInstance().getReference("Sweet App");
        ProductsRef.child("Users").child("Chalet Owner").child(Uid).child("MyChalte").addValueEventListener(new ValueEventListener() {
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
                    Toast.makeText(DetailsChaletOwnerActivity.this, "Pid: "+list.getPid(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}