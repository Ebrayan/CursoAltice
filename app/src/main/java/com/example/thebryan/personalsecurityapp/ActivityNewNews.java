package com.example.thebryan.personalsecurityapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.thebryan.personalsecurityapp.Util.Aplication;
import com.example.thebryan.personalsecurityapp.Models.News;
import com.example.thebryan.personalsecurityapp.Util.ConnectionToFireBase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.List;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class ActivityNewNews extends AppCompatActivity {
    static  ImageView Viewnewsimage;
    static  File Myfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_news);

        Button btnPublish = findViewById(R.id.btnPublish);
        Viewnewsimage = findViewById(R.id.news_image);
        final EditText txtTitle= findViewById(R.id.new_news_title);
        final EditText txtContent= findViewById(R.id.new_news_content);
        Viewnewsimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 EasyImage.openChooserWithGallery(ActivityNewNews.this,"Selecciona una imagen",0);
            }
        });



        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtContent.getText().toString().trim().equals("") || txtTitle.getText().toString().trim().equals("")){
                    Toast.makeText(ActivityNewNews.this,"Asegurese de no dejar ningun campo vacio",Toast.LENGTH_SHORT).show();
                }else{
                    if(Myfile ==null){
                        Toast.makeText(ActivityNewNews.this," La imagen no debe estar vacia",Toast.LENGTH_SHORT).show();
                    }else if(txtContent.getText().toString().trim().equals("")||txtTitle.getText().toString().trim().equals("")){
                        Toast.makeText(ActivityNewNews.this," El titulo o el contenido esta vacio",Toast.LENGTH_SHORT).show();
                    }else{
                        final StorageReference reference = FirebaseStorage.getInstance().getReference().
                                child("newImages").child(Myfile.getName());
                        UploadTask uploadTask = reference.putFile(Uri.fromFile(Myfile));
                        uploadTask.addOnCompleteListener(ActivityNewNews.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        News news= new News();
                                        news.setContent(txtContent.getText().toString().trim());
                                        news.setTittle(txtTitle.getText().toString().trim());
                                        news.setUserName(Aplication.getUserActives().getUsername());
                                        news.setUser(Aplication.getUserActives().getUserID());
                                        news.setImage(uri.toString());
                                        Toast.makeText(ActivityNewNews.this,"La subida Finalizo",Toast.LENGTH_SHORT).show();
                                        ConnectionToFireBase.getConnectionToFireBaseInstance()
                                                .setObjectOnFireDatabase(ConnectionToFireBase.TYPE_NEWS,news,"nada" );
                                    }
                                });

                            }
                        });
                        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(ActivityNewNews.this,"La subida esta en progreso",Toast.LENGTH_SHORT).show();

                            }
                        });

                        uploadTask.addOnFailureListener(ActivityNewNews.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("imge","La excepcion es"+e.toString());
                                Toast.makeText(ActivityNewNews.this,"Error al subir ",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }


                                    }




            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagesPicked(@NonNull List<File> imageFiles, EasyImage.ImageSource source, int type) {
                for (File file : imageFiles){
                    ActivityNewNews.Viewnewsimage.setImageURI(Uri.fromFile(file));
                    Toast.makeText(ActivityNewNews.this,file.getAbsolutePath(),Toast.LENGTH_SHORT).show();
                    Myfile = file;
                }

            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                super.onCanceled(source, type);

                Toast.makeText(ActivityNewNews.this,"Seleccion cancelada",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                super.onImagePickerError(e, source, type);
                e.printStackTrace();
                Toast.makeText(ActivityNewNews.this,"Error al seleccionar la Image",Toast.LENGTH_SHORT).show();

            }
        });

    }


}
