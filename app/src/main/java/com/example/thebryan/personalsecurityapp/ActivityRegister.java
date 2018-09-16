package com.example.thebryan.personalsecurityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thebryan.personalsecurityapp.Util.Aplication;
import com.example.thebryan.personalsecurityapp.Models.User;
import com.example.thebryan.personalsecurityapp.Util.ConnectionToFireBase;

public class ActivityRegister extends AppCompatActivity {

    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    private EditText userNametxt;
    private EditText mPasswordView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userNametxt = findViewById(R.id.userName);
        mPasswordView = findViewById(R.id.password);
        final EditText phoneNumbertxt = findViewById(R.id.mobileNumber);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {


                if(userNametxt.getText().toString().equals("")||mPasswordView.getText().toString().equals("")||phoneNumbertxt.getText().toString().equals("")){

                    Toast.makeText(ActivityRegister.this,"Hay alguun campo vacio", Toast.LENGTH_LONG).show();
                }else{

                    String username = userNametxt.getText().toString();
                    String pass=mPasswordView.getText().toString();
                    String mobileNumber=phoneNumbertxt.getText().toString();
                    User user  = new User();
                    user.setMobileNumber(mobileNumber);
                    user.setPass(pass);
                    user.setUsername(username);
                    ConnectionToFireBase
                            .getConnectionToFireBaseInstance()
                            .setObjectOnFireDatabase(ConnectionToFireBase.TYPE_USER,user,"none");
                    Aplication.init(user);
                startActivity(new Intent(ActivityRegister.this,ActivityMain.class));
                }




            }
        });

    }

}

