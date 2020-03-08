package com.example.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class Profile extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, mref;
    FirebaseStorage firebaseStorage;
    private static int PICK_IMAGE=123;
    Uri imagePath;
    Model_Signup user1 = new Model_Signup();

    ValueEventListener eventListener;


    ImageView image;
    private  TextView txt_email;
    Button btnupdate, btnchangePassowrd,btnupload;
    EditText email, number,name;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null){
            imagePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = (EditText) findViewById(R.id.profileName);
        txt_email = (TextView) findViewById(R.id.tv_profilemail);
        image = (ImageView)findViewById(R.id.avatarIv);
        btnupdate = (Button)findViewById(R.id.btn_profileUpdate);
        //btnchangePassowrd = (Button) findViewById(R.id.btn_profilechangePassword);
        email = (EditText) findViewById(R.id.profileEmail);
        number = (EditText)findViewById(R.id.profileNumber);
        //btnupload = (Button)findViewById(R.id.profileupload);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


         final StorageReference storageReference = firebaseStorage.getReference();
//        storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                Picasso.get().load(uri).fit().centerCrop().into(image);

         //   }
       // });

        //StorageReference myref = storageReference.child(firebaseAuth.getUid());

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select image"),PICK_IMAGE);
            }
        });

        String currentuser = firebaseAuth.getInstance().getCurrentUser().getUid();
        user= firebaseAuth.getCurrentUser();
        databaseReference = firebaseDatabase.getReference("USERS");
        mref =databaseReference.child(currentuser);



        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user1 = dataSnapshot.getValue(Model_Signup.class);
                name.setText(user1.getFullname());
                email.setText(user1.getEmail());
                number.setText(user1.getPhone());

//                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()) {
//
//                    //Model_Signup user1;//= new Model_Signup();
//                   //user1 = dataSnapshot.getValue(Model_Signup.class);//new Model_Signup()
//
//          user1 = itemSnapshot.getValue(Model_Signup.class);
//                    name.setText(user1.getFullname());
//                    email.setText(user1.getEmail());
//                    //mber.setText(user1.getPhone());
//                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Profile.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             String Name = name.getText().toString();
                                             String Email = email.getText().toString();
                                             String PhoneNo = number.getText().toString();

                                             Model_Signup user = new Model_Signup(Name, Email, PhoneNo);

                                             databaseReference.setValue(user);
                                             Toast.makeText(Profile.this, "Successfully saved!", Toast.LENGTH_SHORT).show();

                                             StorageReference imageRefernece = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic");
//                                             UploadTask uploadTask = imageRefernece.putFile(imagePath);
//
//                                             uploadTask.addOnFailureListener(new OnFailureListener() {
//                                                 @Override
//                                                 public void onFailure(@NonNull Exception e) {
//                                                     Toast.makeText(Profile.this, "Upload failed!", Toast.LENGTH_SHORT).show();
//                                                 }
//                                             }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                                                 @Override
//                                                 public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                                                     Toast.makeText(Profile.this, "Upload Successful!", Toast.LENGTH_SHORT).show();
//                                                 }
//
//                                             });
                                         }
                                     });
//
//     btnchangePassowrd.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View view) {
//             startActivity(new Intent(Profile.this , ChangePassword.class));
//         }
//     });
//
//
//        btnupload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(
//                        new Intent(Profile.this , uploadNgoList.class)
//                );
//            }
//        });

}
}

