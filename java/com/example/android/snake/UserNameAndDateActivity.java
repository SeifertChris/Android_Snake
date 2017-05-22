package com.example.android.snake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chris on 2/28/2017.
 */

public class UserNameAndDateActivity extends Activity {

    SimpleDateFormat mSimpleDateFormat;
    EditText userName;
    TextView displayForDate;
    Button createUser;
    Button beginGame;
    String pasWord = "passWord";
    //String  = "155106526324-hsbeul2j9e7llkj4u3oho3c05uj5e5l1.apps.googleusercontent.com";
    static String GoogleEmail ="";

    public static String dateFormatted = "";
    public static String userNameparse = "";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    final String TAG = "UserNameAndDateActivity";
    SignInButton googleSignInBtn;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    Button signOutGoogle;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_date);
        signOutGoogle = (Button)findViewById(R.id.sign_out_google);

        googleSignInBtn =(SignInButton)findViewById(R.id.google_sign_in_button);
        googleSignInBtn.setSize(SignInButton.SIZE_WIDE);
        beginGame = (Button) findViewById(R.id.beginGame);

        displayForDate = (TextView) findViewById(R.id.date);
        mSimpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        dateFormatted = mSimpleDateFormat.format(new Date());
        displayForDate.setText(dateFormatted);
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signOutGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
            }
        });

        googleSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.google_sign_in_button:
                        signIn();
                        break;
                }

            }
        });



        beginGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(UserNameAndDateActivity.this, ListOfScoresFromCloud.class));
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Toast.makeText(getApplicationContext(), "Signed In", Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Toast.makeText(getApplicationContext(), "Signed Out", Toast.LENGTH_SHORT).show();
                }
                // ...
            }
        };


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent();
        if(requestCode == RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()){
                //Google sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
                GoogleEmail = account.getEmail();
            }else {
                Toast.makeText(this,"Google Sign In Failed",Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct){
        Log.d(TAG,"firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG,"signInWithCredential:onComplete:" + task.isSuccessful());
                        // if sign in fails display message. If sign in succeeds the auth state listener will be notified and logic to handle the signed in user can be handled in the listener
                        if (!task.isSuccessful()){
                            Log.w(TAG,"signInWithCredential", task.getException());
                            Toast.makeText(UserNameAndDateActivity.this, "Authentication Failed.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



    private void signIn(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);


    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }

    }



    public void createAccount(String email, String password) {


        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new
                OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Authorization Failed", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
    }

    public void signInCheck(String userEmail, String userPassword) {

        mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this, new
                OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(getApplicationContext(), "Authenication Failed", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
    }


}

