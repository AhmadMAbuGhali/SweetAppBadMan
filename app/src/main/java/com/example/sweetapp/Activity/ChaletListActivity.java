package com.example.sweetapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sweetapp.Adapter.AdapterChaletlistOwner;
import com.example.sweetapp.Adapter_Iteam.AdapterIteamChaletList;
import com.example.sweetapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChaletListActivity extends AppCompatActivity {
    FloatingActionButton AddChalet, btn_EditChalet, btn_DeleteChalet;
    RecyclerView recyclerView;
    AdapterIteamChaletList iteamChaletList;
    AdapterChaletlistOwner adapterChaletlistOwner;
    DatabaseReference ChaletsRef;
    ArrayList<AdapterIteamChaletList> adapterIteamChaletLists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chalet_list);

        recyclerView = findViewById(R.id.rec_chalitlist);

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
        btn_EditChalet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChaletListActivity.this, EditChaletActivity.class);
                startActivity(intent);


            }
        });


        btn_DeleteChalet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ChaletsRef.child("Users")
                        .child("Chalets").child(iteamChaletList.getPid())
                        .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(ChaletListActivity.this, "تمت عملية حذف الشاليه ", Toast.LENGTH_SHORT).show();

                        }


                    }
                });


            }
        });


        ChaletsRef = FirebaseDatabase.getInstance().getReference().child("Chalets");


        ArrayList<AdapterIteamChaletList> models = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ChaletsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    iteamChaletList = snapshot.getValue(AdapterIteamChaletList.class);
                    models.add(iteamChaletList);

                }
                adapterChaletlistOwner = new AdapterChaletlistOwner(models);
                recyclerView.setAdapter(adapterChaletlistOwner);
                adapterChaletlistOwner.setOnItemClickListener(new AdapterChaletlistOwner.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(ChaletListActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChaletListActivity.this, DetailsChaletOwnerActivity.class);
                        intent.putExtra("Pid", models.get(position).getPid() + "");
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