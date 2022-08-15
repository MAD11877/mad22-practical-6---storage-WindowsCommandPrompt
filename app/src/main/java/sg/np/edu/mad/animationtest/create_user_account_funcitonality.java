package sg.np.edu.mad.animationtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class create_user_account_funcitonality extends AppCompatActivity {
    //this java file controls 'create_user_account.xml'

    public static final String TAG = "TAG";

    private void Wait(long millis){
        try{
            Thread.sleep(millis);
        }
        catch (InterruptedException ie){
            Log.d(TAG, "Process killed!");
        }
    }

    public DBHandler dbHandler = new DBHandler(this);

    private boolean hasDuplicate(String username){
        FirebaseFirestore RESTdb = FirebaseFirestore.getInstance();
        AtomicBoolean booleanValue = new AtomicBoolean(true);
        RESTdb.collection("LoginInformation").document("LoginInformation").get().addOnCompleteListener(process -> {
            if (process.isSuccessful()){
                //Arrays.asList(process.getResult().getData().values()) actually stores a user object
                for (int i = 0; i < (Arrays.asList(process.getResult().getData().values())).size(); i++){
                    if(!((((User) (Arrays.asList(process.getResult().getData().values())).get(i))).username).equals(username)){
                        booleanValue.set(false);
                    }
                    else {
                        Toast.makeText(this, "Sorry, this username has already been taken.", Toast.LENGTH_SHORT);
                        booleanValue.set(true);
                    }
                }
            }
        });
        return booleanValue.get();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user_account);

        //extract information from the SharedPreferences
        //check for duplicate username

        //if there is a duplicate username then raise an error....
        //can just take the method from DBHandler.java
        //the name of the method is FindUser()

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        (findViewById(R.id.confirmationSignUpButton)).setOnClickListener(andThen -> {
            EditText usernameField = (findViewById(R.id.setUsernameFieldText));
            String usernameInput = usernameField.getText().toString(); //Extract the username string from the username field
            EditText passwordField = (findViewById(R.id.setPasswordFieldText));
            String passwordInput = passwordField.getText().toString(); //Extract the password string from the password field
            if (usernameInput.length() != 0 && passwordInput.length() != 0) {
                if (hasDuplicate(usernameInput)) {
                    //if there is a duplicate....
                    alertDialogBuilder
                        .setTitle("Duplicate user error")
                        .setMessage(usernameInput + " is already taken. Please try again with another username.")
                        .setPositiveButton(
                            "Understood",
                            (DialogInterface di, int i) -> {
                                di.dismiss();
                            }
                        );
                    AlertDialog messageBox = alertDialogBuilder.create();
                    messageBox.show();
                } else {
                    //create a new user object and then send it into Google FirebaseFirestore
                    HashMap<String, User> newUser = new HashMap<String, User>();
                    //int id, String name, String description, String username, String password, ArrayList<String> followedWho
                    //Go into the firebase database and find the last id number
                    int newID; String newName, newDescription, newUsername, newPassword; ArrayList<String> newFollowedWhoList; boolean newFollowedStatus = false;

                    Toast.makeText(this, "New account has been created....", Toast.LENGTH_SHORT);
                    Wait(1000);
                    Intent thenRedirect = new Intent();
                    thenRedirect.setClassName(this, "sg.np.edu.mad.animationtest.user_landing");
                    startActivity(thenRedirect);
                    //start the activity, and then terminate this one
                    finish();
                }
            }
            else {
                Toast.makeText(this, "Please enter a username and / or password", Toast.LENGTH_SHORT).show();
            }
        });

        //What if the user does not want to sign up anymore, and want to return to the previous page
        (findViewById(R.id.cancelSignUpButton)).setOnClickListener(andThen -> {
            alertDialogBuilder
                    .setTitle("Confirm exit?")
                    .setMessage("Are you sure you want to quit this process, all the values will be lost the moment you exit this page")
                    .setPositiveButton(
                            "YES",
                            (DialogInterface di, int i) -> {
                                Intent teleporter = new Intent();
                                teleporter.setClassName(this, "sg.np.edu.mad.animationtest.user_landing");
                                startActivity(teleporter);
                                finish();
                            }
                    )
                    .setNegativeButton(
                            "NO",
                            (DialogInterface di, int i) -> {
                                di.dismiss();
                            }
                    );

            //make the dialog box appear
            AlertDialog messageBox = alertDialogBuilder.create();
            messageBox.show();
        });
    }
}
