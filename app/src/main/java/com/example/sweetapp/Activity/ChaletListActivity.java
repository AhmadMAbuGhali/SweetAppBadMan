    package com.example.sweetapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sweetapp.Adapter.AdapterChaletlistOwner;
import com.example.sweetapp.Model.ChaletListIteamModel;
import com.example.sweetapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChaletListActivity extends AppCompatActivity {
    FloatingActionButton AddChalet, btn_EditChalet, btn_DeleteChalet;
    RecyclerView recyclerView;
    ChaletListIteamModel iteamChaletList;
    AdapterChaletlistOwner adapterChaletlistOwner;
    DatabaseReference ChaletsRef;
    ArrayList<ChaletListIteamModel> adapterIteamChaletLists;
    FirebaseAuth mAuth;
    ChaletListIteamModel list;
     private  String NameChalet,uid,chaletId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chalet_list);

        recyclerView = findViewById(R.id.rec_chalitlist);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();

        btn_EditChalet = findViewById(R.id.btn_EditChalet);
        btn_DeleteChalet = findViewById(R.id.btn_DeleteChalet);
        AddChalet = findViewById(R.id.AddChalet);
        AddChalet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChaletListActivity.this, AddChaletActivity.class);
                startActivity(intent);


            }
        });


        ChaletsRef = FirebaseDatabase.getInstance().getReference().child("Sweet App");


        ArrayList<ChaletListIteamModel> models = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        Bundle extras = getIntent().getExtras();
//        String Uid = extras.getString("Uid");
        Intent intent = getIntent();
        chaletId = intent.getStringExtra("chaletId");
//        NameChalet = getIntent().getExtras().getString("NameChalet");
        ChaletsRef.child("Chalet")
        .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    iteamChaletList = snapshot.getValue(ChaletListIteamModel.class);
                    models.add(iteamChaletList);
//                    ChaletListIteamModel products = snapshot.getValue(ChaletListIteamModel.class);
//                    ChaletListIteamModel model = new ChaletListIteamModel();
//                    model.setName_Chalet(iteamChaletList.getName_Chalet());
//                    model.setSalary("1000");
//                    model.setName_Chalet(iteamChaletList.getName_Chalet());
//                    model.setName_Chalet(iteamChaletList.getName_Chalet());


                }
                adapterChaletlistOwner = new AdapterChaletlistOwner(models,ChaletListActivity.this);
                recyclerView.setAdapter(adapterChaletlistOwner);
                adapterChaletlistOwner.notifyDataSetChanged();

                adapterChaletlistOwner.setOnItemClickListener(new AdapterChaletlistOwner.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(ChaletListActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChaletListActivity.this, DetailsChaletOwnerActivity.class);
                        intent.putExtra("chaletId", models.get(position).getChaletId() + "");
//                        intent.putExtra("NameChalet", models.get(position).getName_Chalet() + "");
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}