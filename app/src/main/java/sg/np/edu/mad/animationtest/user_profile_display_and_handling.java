package sg.np.edu.mad.animationtest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

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
        List<User> userList = Arrays.asList((User) main.ExtractAllFromDatabase().values()); //the database has been fully extracted returns a HashMap<String, Object> Object

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
        ((Button) findViewById(R.id.followUnfollowButton)).setOnClickListener(thenFunctionAs -> {
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
        });

        //tempList.get() gives the entire array....
        //usernameString.get() gives the username identifier
        dbHandler.UpdateUser(tempList.get(), usernameString.get());
    }
}
