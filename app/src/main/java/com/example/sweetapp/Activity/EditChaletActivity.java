package com.example.sweetapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sweetapp.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class EditChaletActivity extends AppCompatActivity {
    ImageView iv_Edit;

    EditText txt_name_Edit, txt_address_Edit, txt_numberisPhone_Edit,
            txt_salary_Edit, txt_numOfHours_Edit;
    Button btn_edit;
    DatabaseReference ChaletsRef;
    String pid, name_Chalet, address, phone, numOfHour, price, image;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String  downloadImageUrl;
    private StorageReference ProductImagesRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProductImagesRef = FirebaseStorage.getInstance().getReference().child("Chalet Images");

        setContentView(R.layout.activity_edit_chalet);
        txt_name_Edit = findViewById(R.id.ed_nameChalet_edit);
        txt_address_Edit = findViewById(R.id.ed_nameaddress_edit);
        txt_numberisPhone_Edit = findViewById(R.id.ed_nameaddress_edit);
        txt_salary_Edit = findViewById(R.id.ed_salarychalet_edit);
        txt_numOfHours_Edit = findViewById(R.id.ed_numOfHours_edit);
        iv_Edit = findViewById(R.id.Img_chalet_edit);

        name_Chalet = txt_name_Edit.getText().toString();
        address = txt_address_Edit.getText().toString();
        phone = txt_numberisPhone_Edit.getText().toString();
        numOfHour = txt_numOfHours_Edit.getText().toString();
        price = txt_salary_Edit.getText().toString();


        btn_edit = findViewById(R.id.btn_edit);


        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edit_data_Chalet();
            }

        });
        iv_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });


    }

    private void edit_data_Chalet() {
        ChaletsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    pid = snapshot.child("pid").getValue().toString();
                    String name_Chalet = snapshot.child("name_Chalet").getValue().toString();
                    String address = snapshot.child("address").getValue().toString();
                    String price = snapshot.child("price").getValue().toString();
                    String phone = snapshot.child("phone").getValue().toString();
                    String numOfHour = snapshot.child("num Of Hours").getValue().toString();
                    String image = snapshot.child("image").getValue().toString();

                    txt_name_Edit.setText(name_Chalet);
                    txt_address_Edit.setText(address);
                    txt_salary_Edit.setText(price);
                    txt_numberisPhone_Edit.setText(phone);
                    txt_numOfHours_Edit.setText(numOfHour);
                    Picasso.get().load(image).into(iv_Edit);
                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        SaveProductInfoToDatabase();
        StoreProductInformation();

    }


    private void OpenGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GalleryPick  &&  resultCode==RESULT_OK  &&  data!=null)
        {
            ImageUri = data.getData();
            iv_Edit.setImageURI(ImageUri);
        }
    }


    private void StoreProductInformation()
    {

        final StorageReference filePath = ProductImagesRef.child(ImageUri.getLastPathSegment()  + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);


        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(EditChaletActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(EditChaletActivity.this, "Product Image uploaded Successfully...", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        if (task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(EditChaletActivity.this, "got the Product image Url Successfully...", Toast.LENGTH_SHORT).show();

                            SaveProductInfoToDatabase();
                        }
                    }
                });
            }
        });
    }



    private void SaveProductInfoToDatabase()
    {
        HashMap<String, Object> ChaletMap = new HashMap<>();
        ChaletMap.put("pid", pid);

        ChaletMap.put("name_Chalet", name_Chalet);
        ChaletMap.put("image", downloadImageUrl);
        ChaletMap.put("address", address);
        ChaletMap.put("price", price);
        ChaletMap.put("phone", phone);
        ChaletMap.put("num Of Hours", numOfHour);

        ChaletsRef.updateChildren(ChaletMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(EditChaletActivity.this, "تم التعديل", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}