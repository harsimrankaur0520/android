package com.example.signup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class uploadNgoList extends AppCompatActivity {

    ImageView imageview;
    Uri uri;
    EditText txt_name, txt_description;
    public static final int Pick_Image = 1;
    String imageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_ngo_list);
    imageview = (ImageView)findViewById(R.id.uploadImage);
    txt_name = (EditText) findViewById(R.id.uploadngoname);
    txt_description = (EditText)findViewById(R.id.uploadDesciption);
    }

    public void btnSelectImage(View view) {

        Intent photoPicker = new Intent();
        photoPicker.setType("image/*");
        photoPicker.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(photoPicker, "Select Picture"),Pick_Image);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == Pick_Image){

            uri = data.getData();
            imageview.setImageURI(uri);
            Toast.makeText(this, "Image is selected ", Toast.LENGTH_SHORT).show();


        }
        else
            Toast.makeText(this, "You haven't picked image", Toast.LENGTH_SHORT).show();
    }


    public void uploadImage(){

        StorageReference storageReference = FirebaseStorage.getInstance()
                .getReference().child("NGO-Image").child(uri.getLastPathSegment());

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();

                imageUrl = urlImage.toString();
                uploadNgo();
                progressDialog.dismiss();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
            }
        });

    }

    public void btn_upload(View view) {
        uploadImage();
    }


    public void uploadNgo(){


        Model model = new Model(
                txt_name.getText().toString(),
                txt_description.getText().toString(),
                imageUrl);

        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("NGO")
                .child(myCurrentDateTime).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(uploadNgoList.this, "Uploaded", Toast.LENGTH_SHORT).show();

                    finish();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(uploadNgoList.this, e.getMessage().toString(),Toast.LENGTH_SHORT).show();

            }
        });

    }


}
