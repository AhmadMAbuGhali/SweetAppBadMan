package com.example.sweetapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sweetapp.Adapter_Iteam.AdapterIteamChaletList;
import com.example.sweetapp.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;


public class AddChaletActivity extends AppCompatActivity {

    Button Services;
    private String saveCurrentDate, saveCurrentTime, NameChalet, Address, numberisPhone, salary, numOfHours,Uid, Services1, Services2, Services3, Services4, Services5, Services6, Services7, Services8, Services9;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String chaletRandomKey, downloadImageUrl;
    private StorageReference ProductImagesRef;
    private DatabaseReference ProductsRef;
    private ProgressDialog loadingBar;
    FirebaseAuth mAuth;
    AdapterIteamChaletList list;



    ImageView imgChalet;
    TextView txtServices1, txtServices2, txtServices3, txtServices4, txtServices5, txtServices6,
            txtServices7, txtServices8, txtServices9;
    EditText edNameChalet, edaddress, edsalary, ednumberisPhone, ednumOfHours;
    Button btn_add;

//    boolean swimming_pool_men, swimming_pool_small, stadium, wifi, billiard,
//            kitchen, garage, stereo, tennis_table;

    String SwimmingPoolMen = "غير موجود",
            SwimmingPoolSmall = "غير موجود",
            Stadium = "غير موجود",
            Wifi = "غير موجود",
            Billiard = "غير موجود",
            Kitchen = "غير موجود",
            Garage = "غير موجود",
            Stereo = "غير موجود",
            TennisTable = "غير موجود";
    ArrayList arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chalet);
        Services = findViewById(R.id.Services);
        mAuth = FirebaseAuth.getInstance();


//        list.setPid(Uid);
//        swimming_pool_men = getIntent().getExtras().getBoolean("checkbox_swimming_pool_men");
//        swimming_pool_small = getIntent().getExtras().getBoolean("checkbox_swimming_pool_small");
//        stadium = getIntent().getExtras().getBoolean("checkbox_stadium");
//        wifi = getIntent().getExtras().getBoolean("checkbox_Wifi");
//        billiard = getIntent().getExtras().getBoolean("checkbox_billiard");
//        kitchen = getIntent().getExtras().getBoolean("checkbox_kitchen");
//        garage = getIntent().getExtras().getBoolean("checkbox_garage");
//        stereo = getIntent().getExtras().getBoolean("checkbox_stereo");
//        tennis_table = getIntent().getExtras().getBoolean("checkbox_tennis_table");


        Services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openPopUpWindow();
            }
        });


//        CategoryName = getIntent().getExtras().get("category").toString();
        ProductImagesRef = FirebaseStorage.getInstance().getReference().child("Chalet Images");
        ProductsRef = FirebaseDatabase.getInstance().getReference("Sweet App");


        btn_add = (Button) findViewById(R.id.btn_addchalet);
        imgChalet = (ImageView) findViewById(R.id.Img_chalet);
        edNameChalet = (EditText) findViewById(R.id.ed_nameChalet);
        edaddress = (EditText) findViewById(R.id.ed_nameaddress);
        edsalary = (EditText) findViewById(R.id.ed_salarychalet);
        ednumberisPhone = (EditText) findViewById(R.id.ed_numberisPhone);
        ednumOfHours = (EditText) findViewById(R.id.ed_numOfHours);


        txtServices1 = (TextView) findViewById(R.id.txt_Services1);
        txtServices2 = (TextView) findViewById(R.id.txt_Services2);
        txtServices3 = (TextView) findViewById(R.id.txt_Services3);
        txtServices4 = (TextView) findViewById(R.id.txt_Services4);
        txtServices5 = (TextView) findViewById(R.id.txt_Services5);
        txtServices6 = (TextView) findViewById(R.id.txt_Services6);
        txtServices7 = (TextView) findViewById(R.id.txt_Services7);
        txtServices8 = (TextView) findViewById(R.id.txt_Services8);
        txtServices9 = (TextView) findViewById(R.id.txt_Services9);



//
//        if (swimming_pool_men == true) {
//
//            SwimmingPoolMen = txtServices1.getText().toString();
//            txtServices1.setBackgroundColor(Color.GREEN);
//
//        }
//
//        txtServices1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SwimmingPoolMen = txtServices1.getText().toString();
//                txtServices1.setBackgroundColor(Color.GREEN);
//
//
//            }
//        });
//        if (swimming_pool_small == true) {
//
//            SwimmingPoolSmall = txtServices2.getText().toString();
//            txtServices2.setBackgroundColor(Color.GREEN);
//
//        }
//
//        txtServices2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SwimmingPoolSmall = txtServices2.getText().toString();
//                txtServices2.setBackgroundColor(Color.GREEN);
//
//
//            }
//        });
//        if (stadium == true) {
//
//            Stadium = txtServices3.getText().toString();
//            txtServices3.setBackgroundColor(Color.GREEN);
//
//        }
//
//        txtServices3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Stadium = txtServices3.getText().toString();
//                txtServices3.setBackgroundColor(Color.GREEN);
//
//
//            }
//        });
//
//
//        if (wifi == true) {
//
//            Wifi = txtServices4.getText().toString();
//            txtServices4.setBackgroundColor(Color.GREEN);
//        }
//
//        txtServices4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Wifi = txtServices4.getText().toString();
//                txtServices4.setBackgroundColor(Color.GREEN);
//
//
//            }
//        });
//        if (billiard == true) {
//
//            Billiard = txtServices5.getText().toString();
//            txtServices5.setBackgroundColor(Color.GREEN);
//
//        }
//
//        txtServices5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Billiard = txtServices5.getText().toString();
//                txtServices5.setBackgroundColor(Color.GREEN);
//
//
//            }
//        });
//        if (kitchen == true) {
//
//            Kitchen = txtServices6.getText().toString();
//            txtServices6.setBackgroundColor(Color.GREEN);
//
//        }
//
//        txtServices6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Kitchen = txtServices6.getText().toString();
//                txtServices6.setBackgroundColor(Color.GREEN);
//
//
//            }
//        });
//        if (garage == true) {
//
//            Garage = txtServices7.getText().toString();
//            txtServices7.setBackgroundColor(Color.GREEN);
//
//        }
//
//        txtServices7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Garage = txtServices7.getText().toString();
//                txtServices7.setBackgroundColor(Color.GREEN);
//
//
//            }
//        });
//        if (stereo == true) {
//
//            Stereo = txtServices8.getText().toString();
//            txtServices8.setBackgroundColor(Color.GREEN);
//
//        }
//
//        txtServices8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Stereo = txtServices8.getText().toString();
//                txtServices8.setBackgroundColor(Color.GREEN);
//
//
//            }
//        });
//        if (tennis_table == true) {
//
//            TennisTable = txtServices9.getText().toString();
//            txtServices9.setBackgroundColor(Color.GREEN);
//
//        }
//
//        txtServices9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TennisTable = txtServices9.getText().toString();
//                txtServices9.setBackgroundColor(Color.GREEN);
//
//
//            }
//        });
//
//        arrayList = new ArrayList();
//        arrayList.add(SwimmingPoolMen);
//        arrayList.add(SwimmingPoolSmall);
//        arrayList.add(Kitchen);
//        arrayList.add(Garage);
//        arrayList.add(TennisTable);
//        arrayList.add(Stereo);
//        arrayList.add(Stadium);
//        arrayList.add(Wifi);
//        arrayList.add(Billiard);


        loadingBar = new ProgressDialog(this);


        imgChalet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateProductData();
            }
        });
    }


    private void OpenGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null) {
            ImageUri = data.getData();
            imgChalet.setImageURI(ImageUri);
        }
    }


    private void ValidateProductData() {
        NameChalet = edNameChalet.getText().toString();
        Address = edaddress.getText().toString();
        salary = edsalary.getText().toString();
        numberisPhone = ednumberisPhone.getText().toString();
        numOfHours = ednumOfHours.getText().toString();
        Services1 = txtServices1.getText().toString();
        Services2 = txtServices1.getText().toString();
        Services3 = txtServices3.getText().toString();
        Services4 = txtServices4.getText().toString();
        Services5 = txtServices5.getText().toString();
        Services6 = txtServices6.getText().toString();
        Services7 = txtServices7.getText().toString();
        Services8 = txtServices8.getText().toString();
        Services9 = txtServices9.getText().toString();


        if (ImageUri == null) {
            Toast.makeText(this, "Product image is mandatory...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(NameChalet)) {
            Toast.makeText(this, "Please write Name Chalet...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Address)) {
            Toast.makeText(this, "Please write product Price...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(salary)) {
            Toast.makeText(this, "Please write product name...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(numberisPhone)) {
            Toast.makeText(this, "Please write product name...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(numOfHours)) {
            Toast.makeText(this, "Please write product name...", Toast.LENGTH_SHORT).show();
        } else {
            StoreProductInformation();
        }
    }


    private void StoreProductInformation() {
        loadingBar.setTitle("Add New Product");
        loadingBar.setMessage("Dear Admin, please wait while we are adding the new product.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        chaletRandomKey = saveCurrentDate + " " + saveCurrentTime;


        final StorageReference filePath = ProductImagesRef.child(ImageUri.getLastPathSegment() + chaletRandomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);


        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(AddChaletActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AddChaletActivity.this, "Product Image uploaded Successfully...", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(AddChaletActivity.this, "got the Product image Url Successfully...", Toast.LENGTH_SHORT).show();

                            SaveProductInfoToDatabase();
                        }
                    }
                });
            }
        });
    }


    private void SaveProductInfoToDatabase() {
        HashMap<String, Object> ChaletMap = new HashMap<>();
        ChaletMap.put("pid", chaletRandomKey);
        ChaletMap.put("date", saveCurrentDate);
        ChaletMap.put("time", saveCurrentTime);
        ChaletMap.put("name_Chalet", NameChalet);
        ChaletMap.put("image", downloadImageUrl);
        ChaletMap.put("address", Address);
        ChaletMap.put("price", salary);
        ChaletMap.put("phone", numberisPhone);
        ChaletMap.put("num Of Hours", numOfHours);
//        ChaletMap.put("Services", arrayList);
//        Bundle extras = getIntent().getExtras();
//        String Uid = extras.getString("Uid");
        ProductsRef.child("Users").child("Chalet Owner").child(Uid).child("MyChalte").child(chaletRandomKey).updateChildren(ChaletMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(AddChaletActivity.this, DetailsChaletOwnerActivity.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(AddChaletActivity.this, "Chalet is added successfully..", Toast.LENGTH_SHORT).show();
                        } else {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(AddChaletActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void openPopUpWindow() {

        Intent PopUpWindow = new Intent(AddChaletActivity.this, PopUpWindowChalet.class);
    }

}