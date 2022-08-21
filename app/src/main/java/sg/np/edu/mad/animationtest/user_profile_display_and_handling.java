package sg.np.edu.mad.animationtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class user_profile_display_and_handling extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        AtomicReference<ArrayList<String>> tempList = new AtomicReference<>(); //stores the followedWho array
        AtomicReference<String> usernameString = new AtomicReference<>();

        //controls all the actions in user_profile.xml
        //read the data from google firebase firestore
        user_landing main = new user_landing();

        DBHandler dbHandler = new DBHandler(this);

        //Redirect to the message button
        ((Button) findViewById(R.id.messageButton)).setOnClickListener(thenFunctionAs -> {
            Intent transporter = new Intent();
            //go to another activity....
            transporter.setClassName("sg.np.edu.mad.ipptready.user_profile_display_and_handling", "sg.np.edu.mad.ipptready.message_controller");
            startActivity(transporter);
            finish();
        });

        //If the user attempts to follow or unfollow the user, then it will trigger a function which would in turn update the information in the database in such a way that it will append the username into the ArrayList that is bounded to the
      /*  ((Button) findViewById(R.id.followUnfollowButton)).setOnClickListener(thenFunctionAs -> {
            //get the username of the profile.
            usernameString.set((((TextView) findViewById(R.id.userProfileNameHolder)).getText()).toString());
            for (int i = 0; i < userList.size(); i++){
                if ((userList.get(i).username).equals(((TextView) findViewById(R.id.userProfileNameHolder)).getText().toString())){ //take note of the username
                    if (((Button) findViewById(R.id.followUnfollowButton)).getText().toString().equals("FOLLOW")){ //to unfollow the user, just simply remove the user from the array list
                        tempList.set(dbHandler.GetAllUsers().get(0).followedWho);
                        tempList.get().add(((TextView) findViewById(R.id.userProfileNameHolder)).getText().toString());
                    }
                    else if (((Button) findViewById(R.id.followUnfollowButton)).getText().toString().equals("UNFOLLOW")){
                        tempList.set(dbHandler.GetAllUsers().get(0).followedWho);
                        tempList.get().remove(((TextView) findViewById(R.id.userProfileNameHolder)).getText().toString());
                    }
                }
            }
        });*/

        //tempList.get() gives the entire array....
        //usernameString.get() gives the username identifier
        dbHandler.UpdateUser(tempList.get(), usernameString.get());

        //Allow the user to be able to sign out of the account
        ((LinearLayout) findViewById(R.id.signOut)).setOnClickListener(function -> {
            AlertDialog.Builder messageBuilder = new AlertDialog.Builder(this);
            messageBuilder
            .setTitle("Confirm sign out?")
            .setMessage("Are you sure you would like to sign out of this application? You will be required to sign in again later")
            .setPositiveButton(
                    "YES",
                    (DialogInterface di, int i) -> {
                        //DELETE THE INFORMATION FROM THE SQLITE DB

                        //Where do you want to go?
                        Intent intent = new Intent();
                        intent.setClassName("sg.np.edu.mad.animationtest.user_profile_display_and_handling", "sg.np.edu.mad.animationtest.user_landing");
                        startActivity(intent);
                        finish();
                    }
            )
            .setNegativeButton(
                    "NO",
                    (DialogInterface di, int i) -> {
                        di.dismiss();
                    }
            )
            .setCancelable(false);
        });
    }
}
