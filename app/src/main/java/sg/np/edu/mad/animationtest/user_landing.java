package sg.np.edu.mad.animationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;
import java.util.concurrent.atomic.*;

import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.Callables;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.*;

public class user_landing extends AppCompatActivity {

    //Thread pause method
    private void Wait(long millis){
        try{
            Thread.sleep(millis);
        }
        catch (InterruptedException ie){
            Log.d(TAG, "Process killed!");
        }
    }

    public final String TAG = "TAG";
    public final String USERNAME = "Username";
    public final String PASSWORD = "Password";
    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<String> passwordList = new ArrayList<>(); //pass this array list into the method named 'PasswordGenerator_x20'
    public static ArrayList<String> usernameList = new ArrayList<>();

    public user_landing() { }

    private HashMap<String, Object> a;

    public void setTempHashMap(HashMap<String, Object> a){
        this.a = a;
    }

    public HashMap<String, Object> getTempHashMap(){
        return this.a;
    }

    public static String S(){
        final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
        final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String DIGITS = "0123456789";
        final String SYMBOLS = "~!@#$%^&*()_+`-={}|:<>?[];',./";
        final String STRING_POOL = LOWER_CASE_LETTERS + UPPER_CASE_LETTERS + DIGITS + SYMBOLS;
        Random generator = new Random();
        String element = "";
        int passwordLength = Math.abs(generator.nextInt(30));
        do{
            int index = Math.abs(generator.nextInt(STRING_POOL.length() - 1));
            char target = STRING_POOL.charAt(index);
            element += target;
        }
        while (element.length() < 20);
        return element;
    }

    public static String U(){
        String a = "";
        ArrayList<String> descriptiveListForGeneralPositive = new ArrayList<>(
                Arrays.asList(
                        "Powerful",
                        "Invincible",
                        "Fearless",
                        "Untouchable",
                        "Undestructable",
                        "Impregnable",
                        "Inviolable",
                        "Unassailable",
                        "Solid",
                        "Rugged",
                        "Tough",
                        "Vigorous",
                        "Sturdy",
                        "Robust",
                        "Substantial",
                        "Durable",
                        "Energetic",
                        "Speedy",
                        "Wise",
                        "Euphoric",
                        "Inspirational",
                        "Bright",
                        "Thoughtful",
                        "Diligent",
                        "Amiable",
                        "Hardworking",
                        "Studious",
                        "Clever",
                        "Brilliant",
                        "EasyGoing",
                        "Mindful",
                        "Adventurous",
                        "Enchanting",
                        "Wise",
                        "Exceptional",
                        "Determined",
                        "Thoughtful",
                        "Enthusiastic"
                )
        );
        ArrayList<String> nameListPerson = new ArrayList<>(
                Arrays.asList(
                        "Professional",
                        "Wiz",
                        "User",
                        "Wizard",
                        "Killer",
                        "Wiper"
                )
        );
        ArrayList<String> nameListAnimal = new ArrayList<String>(
                Arrays.asList(
                        "Impala",
                        "Jaguar",
                        "Cheetah",
                        "Lion",
                        "Tiger"
                )
        );
        if ((new Random()).nextInt(2) == 0){
            //take from the nameListPerson
            a += descriptiveListForGeneralPositive.get(new Random().nextInt(descriptiveListForGeneralPositive.size() - 1))
                    + "_"
                    + ((Math.abs((new Random()).nextInt(3)) == 0)
                        ? ReturnCharacterInterval()
                        : (Math.abs((new Random()).nextInt(3)) == 1)
                        ? 2 * ReturnCharacterInterval()
                        : (Math.abs((new Random()).nextInt(3)) == 2)
                        ? 3 * ReturnCharacterInterval()
                        : (Math.abs((new Random()).nextInt(3)) == 3)
                        ? 4 * ReturnCharacterInterval()
                        : "");
        }
        else if ((new Random()).nextInt(2) == 1){
            //take from the nameListPerson
            a += descriptiveListForGeneralPositive.get(new Random().nextInt(descriptiveListForGeneralPositive.size() - 1))
                    + "_"
                    + ((Math.abs((new Random()).nextInt(3)) == 0)
                        ? ReturnCharacterInterval()
                        : (Math.abs((new Random()).nextInt(3)) == 1)
                        ? 2 * ReturnCharacterInterval()
                        : (Math.abs((new Random()).nextInt(3)) == 2)
                        ? 3 * ReturnCharacterInterval()
                        : (Math.abs((new Random()).nextInt(3)) == 3)
                        ? 4 * ReturnCharacterInterval()
                        : "");
        }
        return a;
    }


    //generate a password...
    private static void PasswordGenerator_x20length(ArrayList<String> password) {
        final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
        final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String DIGITS = "0123456789";
        final String SYMBOLS = "~!@#$%^&*()_+`-={}|:<>?[];',./";
        final String STRING_POOL = LOWER_CASE_LETTERS + UPPER_CASE_LETTERS + DIGITS + SYMBOLS;
        Random generator = new Random();
        String element = "";
        int passwordLength = Math.abs(generator.nextInt(30));
        do{
            int index = Math.abs(generator.nextInt(STRING_POOL.length() - 1));
            char target = STRING_POOL.charAt(index);
            element += target;
        }
        while (element.length() < 20);
        password.add(element);
    }

    //this method will always return one character from the string.
    private static char ReturnCharacterInterval(){
        final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
        final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String DIGITS = "0123456789";
        final String STRING_POOL = LOWER_CASE_LETTERS + UPPER_CASE_LETTERS + DIGITS;
        return STRING_POOL.charAt((new Random().nextInt(STRING_POOL.length() - 1)));
    }

    private static void UsernameGenerator_x100Quantity(ArrayList<String> username){
        ArrayList<String> descriptiveListForGeneralPositive = new ArrayList<String>(
                Arrays.asList(
                        "Powerful",
                        "Invincible",
                        "Fearless",
                        "Untouchable",
                        "Undestructable",
                        "Impregnable",
                        "Inviolable",
                        "Unassailable",
                        "Solid",
                        "Rugged",
                        "Tough",
                        "Vigorous",
                        "Sturdy",
                        "Robust",
                        "Substantial",
                        "Durable",
                        "Energetic",
                        "Speedy",
                        "Wise",
                        "Euphoric",
                        "Inspirational",
                        "Bright",
                        "Thoughtful",
                        "Diligent",
                        "Amiable",
                        "Hardworking",
                        "Studious",
                        "Clever",
                        "Brilliant",
                        "EasyGoing",
                        "Mindful",
                        "Adventurous",
                        "Enchanting",
                        "Wise",
                        "Exceptional",
                        "Determined",
                        "Thoughtful",
                        "Enthusiastic"
                )
        );
        ArrayList<String> nameListPerson = new ArrayList<String>(
                Arrays.asList(
                        "Professional",
                        "Wiz",
                        "User",
                        "Wizard",
                        "Killer",
                        "Wiper"
                )
        );
        ArrayList<String> nameListAnimal = new ArrayList<String>(
                Arrays.asList(
                        "Impala",
                        "Jaguar",
                        "Cheetah",
                        "Lion",
                        "Tiger"
                )
        );
        if ((new Random()).nextInt(2) == 0){
            //take from the nameListPerson
            username.add(    descriptiveListForGeneralPositive.get((Math.abs((new Random()).nextInt(descriptiveListForGeneralPositive.size() - 1)))) +
                             nameListPerson.get(Math.abs((new Random()).nextInt(nameListPerson.size() - 1))) + "_" +
                             ((Math.abs((new Random()).nextInt(3)) == 0)
                                     ? ReturnCharacterInterval()
                                     : (Math.abs((new Random()).nextInt(3)) == 1)
                                            ? 2 * ReturnCharacterInterval()
                                            : (Math.abs((new Random()).nextInt(3)) == 2)
                                                    ? 3 * ReturnCharacterInterval()
                                                    : (Math.abs((new Random()).nextInt(3)) == 3)
                                                            ? 4 * ReturnCharacterInterval()
                                                            : "")
            );
        }
        else if ((new Random()).nextInt(2) == 1){
            //take from the nameListAnimal
            username.add(    descriptiveListForGeneralPositive.get((Math.abs((new Random()).nextInt(descriptiveListForGeneralPositive.size() - 1)))) +
                             nameListAnimal.get(Math.abs((new Random()).nextInt(nameListAnimal.size() - 1))) + "_" +
                            ((Math.abs((new Random()).nextInt(3)) == 0)
                                    ? ReturnCharacterInterval()
                                    : (Math.abs((new Random()).nextInt(3)) == 1)
                                            ? 2 * ReturnCharacterInterval()
                                            : (Math.abs((new Random()).nextInt(3)) == 2)
                                                    ? 3 * ReturnCharacterInterval()
                                                    : (Math.abs((new Random()).nextInt(3)) == 3)
                                                            ? 4 * ReturnCharacterInterval()
                                                            : "")
            );
        }
    }

    public DBHandler dbHandler = new DBHandler(this);

    // use the 'FirebaseFirestore' to write to the Firebase storage...
    public static FirebaseFirestore RESTdb = FirebaseFirestore.getInstance();

    //check for whether the username exists in the database. false
    //Firestorage (root) -> collection node -> document node
    public Boolean isAnEntryInDatabase(String username){
        boolean existance = false;
        Log.d("isAnEntryInDatabase", "Function begins execution....");
        for (User u : ConvertToUser()){
            Log.d("User", u.username);
            if (u.username.equals(username)) {
                existance = true;
            }
        }
        return existance;
    }

    //Extract all the information from the database...
    public static HashMap<String, Object> ExtractAllFromDatabase(){
        DocumentReference df = RESTdb.collection("LoginInformation").document("LoginInformation");
        HashMap<String, Object>[] abc = new HashMap[]{ new HashMap<String, Object>() };
        df.get().addOnSuccessListener(process-> {
            final HashMap<String, Object> a = ((HashMap<String, Object>)process.getData()); //all the data stored in a variable named a..
            //DO SOMETHING TO MAKE THE VALUE EXIT THE LAMBDA HOWWWWW
            //put a into something???
            Log.d("Data size", "" + a.size()); //99
            Log.d("Current Data", "" + a);
            //Conclusion: You cannot set anything from within a lambda.

        });

        Log.d("Return Result", "" + abc[0]);

        return new HashMap<String, Object>();
    }

    //Convert the ExtractAllFromDatabase() into an HashMap that stores User objects
    //unpackage the object and form a user
    public static ArrayList<User> ConvertToUser(){
        ArrayList<Integer> idList = new ArrayList<Integer>();
        ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<String> descriptionList = new ArrayList<String>();
        ArrayList<String> usernameList = new ArrayList<String>();
        ArrayList<String> passwordList = new ArrayList<String>();
        ArrayList<Boolean> followedList = new ArrayList<Boolean>();
        ArrayList<ArrayList<String>> followedWhoList = new ArrayList<ArrayList<String>>();
        ArrayList<String> hashMapKeys = new ArrayList<String>();
        for (String s : ExtractAllFromDatabase().keySet()){
            hashMapKeys.add(s); //store all the keys into this array.
        }
        ArrayList<Object> rawUserData = new ArrayList<Object>();
        for (Object obj : ExtractAllFromDatabase().values()){
            rawUserData.add(obj);
            Log.d("Raw data", obj.toString());
        }
        for (Object obj : rawUserData){
            String[] a = obj.toString().split("=");
            for (int i = 0; i < a.length; i++){
                if (i + 1 < a.length){
                    if (a[i].contains("name")){
                        //then the next line must contain the required data.
                        //a[i+1].split(",")[0] will contain the result..
                        nameList.add(a[i+1].split(",")[0]);
                    }
                    else if (a[i].contains("description")){
                        //then the next line must contain the required data
                        descriptionList.add(a[i+1].split(",")[0]);
                    }
                    else if (a[i].contains("followed")){
                        //then the next line must contain the required data
                        followedList.add(Boolean.parseBoolean(a[i+1].split(",")[0]));
                    }
                    else if (a[i].contains("followedWho")){
                        //then the next line must contain the required data
                        followedWhoList.add(new ArrayList<String>(Arrays.asList(a[i+1].split(",")[0])));
                    }
                    else if (a[i].contains("username")){
                        //then the next line must contain the required data
                        usernameList.add(a[i+1].split(",")[0]);
                    }
                    else if (a[i].contains("password")){
                        //then the next line must contain the required data
                        passwordList.add(a[i+1].split(",")[0]);
                    }
                    else if (a[i].contains("id")){
                        //then the next line must contain the required data
                        idList.add(Integer.parseInt(a[i+1].split(",")[0]));
                    }
                }
            }
        }
        ArrayList<User> userUserList = new ArrayList<User>();
        for (int i = 0; i < usernameList.size(); i++){
            userUserList.add(new User(idList.get(i), nameList.get(i), descriptionList.get(i), usernameList.get(i), passwordList.get(i), followedList.get(i), followedWhoList.get(i)));
        }
        return userUserList;
    }

    //Find the target user
    //param1 contains the updated userList, param2 is the username of the user.....
    public void UserDatabaseUpdateHandling(ArrayList<String> followed, String username){
        ArrayList<User> extractedUserList = new ArrayList<User>();
        ArrayList<String> keyPair = new ArrayList<String>();
        User user = new User();
        int targetIndex = 0;
        for (String s : ExtractAllFromDatabase().keySet()){
            keyPair.add(s);
        }
        for (Object u : ExtractAllFromDatabase().values()){
            extractedUserList.add((User) u);
        }
        for (User u : extractedUserList){
            if (u.username.equals(username)){
                targetIndex = extractedUserList.indexOf(u);
                user.setId(u.id);
                user.setName(u.name);
                user.setDescription(u.description);
                user.setFollowed(u.followed);
                user.setUsername(u.username);
                user.setPassword(u.password);
                user.setFollowedWho(u.followedWho);
            }
        }
        String requiredKeyPair = keyPair.get(targetIndex);
        //new user built
        //update that field...
        /*
        RESTdb.collection("LoginInformation").document("LoginInformation")
                .update
                .addOnCompleteListener(Void -> {

                })
                .addOnCancelListener(Void -> {

                });
                
         */
    }

    //
    public int GenerateNumOfFollowers(){
        Random generator = new Random();
        //number generated from the random number generator will exclude the value included in the bound. Therefore
        return Math.abs(generator.nextInt((userList.size()) + 1));
    }

    public int GenerateRandomIndex(){
        Random generator = new Random();
        //excludes the overall size of the data stored within the database.....
        return Math.abs(generator.nextInt((userList.size())) - 1);
    }

    //substitute int numOfFollowers with GenerateNumofFollowers() : int
    public String[] AppendFollowerList(){
        ArrayList<String> usernameStringList = new ArrayList<>();
        ArrayList<String> tempHolder = new ArrayList<>();
        if (GenerateNumOfFollowers() != 0) {
            do {
                usernameStringList.add(userList.get(GenerateRandomIndex()).username);
            }
            while (usernameStringList.size() < GenerateNumOfFollowers());
            for (String s : new HashSet<String>(usernameStringList)){
                //remove duplicates
                tempHolder.add(s);
            }
            return tempHolder.toArray(new String[]{ });
        }
        else {
            return new String[]{ };
        }
    }

    public ArrayList<User> AppendIntoActualArray(){
        for (int i = 0; i < userList.size(); i++){
            userList.get(i).followedWho = new ArrayList<String>(Arrays.asList(AppendFollowerList()));
            if (userList.get(i).followedWho.contains(userList.get(i).username)){
                userList.get(i).followedWho.remove(userList.get(i).username);
            }
        }
        return userList;
    }

    //Retrieve the required user from the database
    public User RetrieveTargetUser(String username){
        HashMap<String, Object> firebaseFirestore = ExtractAllFromDatabase();
        //returns a user object...
        User user = new User();
        if (firebaseFirestore.size() != 0) {
            if ((((User) firebaseFirestore.values()).username).equals(username)) {
                user.setId(((User) firebaseFirestore.values()).id);
                user.setUsername(((User) firebaseFirestore.values()).username);
                user.setName(((User) firebaseFirestore.values()).name);
                user.setPassword(((User) firebaseFirestore.values()).password);
                user.setFollowed(((User) firebaseFirestore.values()).followed);
                user.setDescription(((User) firebaseFirestore.values()).description);
                user.setFollowedWho(((User) firebaseFirestore.values()).followedWho);
            } else {
                return null;
            }
        }
        else {
            return null;
        }
        return user;
    }

    //dangerous, use this method with care
    public static void DeleteEntiresFirebaseFirestore(){
        RESTdb.collection("LoginInformation").document("LoginInformation")
                .delete()
                .addOnSuccessListener((Void)->{
                    Log.d("SDF", "Database successfully deleted!");
                })
                .addOnFailureListener((Void)->{
                    Log.d("SDF", "Database failed to delete. Please try again...");
                });
    }

    public static boolean databaseHas100Entries(){
        return ExtractAllFromDatabase().size() < 100;
    }

    //dangerous, use this method with care
    public static void WriteToFirebaseFirestore(HashMap<String, User> userData){
        if (databaseHas100Entries()) {
            Log.d("Datasize", Integer.toString(ExtractAllFromDatabase().size()));
            Log.d("Function entry", "Starting to write");
            RESTdb.collection("LoginInformation").document("LoginInformation")
                    .set(userData)
                    .addOnSuccessListener((Void) -> {
                        Log.d("DocumentUpdateStatus", "Data append successful");
                    })
                    .addOnFailureListener((Void) -> {
                        Log.d("DocumentUpdateStatus", "Data append failed");
                    });
        }
        else {
            Log.d("Message", "Has 100 entries!");
            return;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Repeat this process for 100 times
        do {
            PasswordGenerator_x20length(passwordList);
        }
        while (passwordList.size() < 100);

        //Repeat this process for 100 times....
        do {
            UsernameGenerator_x100Quantity(usernameList);
        }
        while (usernameList.size() < 100);

        //Generate 100 user data and append it into the database.
        int tag = 1;
        do {
            Random generator = new Random();
            int descriptionIntegerSegment = Math.abs(generator.nextInt());
            int nameIntegerSegment = Math.abs(generator.nextInt());
            String name = "Name-" + nameIntegerSegment;
            String description = "Description-" + descriptionIntegerSegment;
            //put all the information in a user object.
            userList.add(new User(
                            tag,
                            name,
                            description,
                            usernameList.get(tag - 1),
                            passwordList.get(tag - 1),
                            false,
                            new ArrayList<>() //add a blank ArrayList
                    )
            );
            tag++;
        }
        while (tag < 100);

        Log.d(TAG, "Code reached this checkpoint: Checkpoint 1");

        /*
        //READ LOG.D to check for password existance.
        for (int i = 0; i < userList.size(); i++) {
            Log.d(USERNAME, (userList.get(i).username));
            Log.d(PASSWORD, (userList.get(i).password) + "\n========================================================================");
        }
         */

        //run AppendFollowerList()
        for (int i = 0; i < AppendIntoActualArray().size(); i++){
            Log.d(USERNAME, (userList.get(i).username));
            Log.d(PASSWORD, (userList.get(i).followedWho) + "\n========================================================================");
        }

        //append the list of objects directly into Google Firebase...
        HashMap<String, User> userData = new HashMap<>();
        for (int i = 0; i < userList.size(); i++){
            userData.put("User " + i, userList.get(i)); //the whole document stores a map, consisting of a key that has been declared as a string and the value which has been declared as a userObject
        }

        //READ LOG.D to check for password existance.
        for (int i = 0; i < userList.size(); i++) {
            Log.d(USERNAME, (userList.get(i).username));
            Log.d(PASSWORD, (userList.get(i).followedWho) + "\n========================================================================");
        }

        //Add into the database...
        WriteToFirebaseFirestore(userData);

        //DeleteEntiresFirebaseFirestore();
        //dbHandler.ClearDatabase();

        //run through the entire SQLite database again......
        if (dbHandler.GetAllUsers().size() == 0) {
            Log.d("Redirect to login", "Going to user login activity");
            setContentView(R.layout.practical5_loginpage); //Set this content view if SharedPreferences contains NO DATA
        }
        else {
            Log.d(TAG, "Else statement executed");
            //straight away go to user_main.java
            Intent transporter = new Intent();
            transporter.setClassName("sg.np.edu.mad.animationtest.user_landing", "sg.np.edu.mad.animationtest.user_main");
            startActivity(transporter);
            finish();
        }

        Button myLoginButton = (Button) findViewById(R.id.myLoginButton);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        //Create a new user account
        TextView newUser = (TextView) findViewById(R.id.createNewUser);

        //if the user taps on the 'New User?' text
        newUser.setOnTouchListener((View v, MotionEvent m) -> {
            //go to another java class
            Intent andThenRedirect = new Intent(user_landing.this, create_user_account_funcitonality.class);
            startActivity(andThenRedirect);
            return m.isButtonPressed(R.id.createNewUser);
        });

        myLoginButton.setOnClickListener(ThenFunction -> {
            //Take in the password.
            EditText passwordTextField = (EditText) findViewById(R.id.passwordField);
            //then take in the username
            EditText usernameTextField = (EditText) findViewById(R.id.usernameField);
            Log.d(TAG, "The code reached here");
            if (passwordTextField.getText().length() != 0 || usernameTextField.getText().length() != 0) {
                Log.d("Account verification", "Verification started....");
                if (!isAnEntryInDatabase(usernameTextField.getText().toString())) {
                    alertDialog
                            .setTitle("User not found")
                            .setMessage("The entered user cannot be found. Do you want to create a new account?")
                            .setPositiveButton(
                                    "Yes",
                                    (DialogInterface di, int i) -> {
                                        di.dismiss();
                                        //then redirect to another activity...
                                        Intent andThenRedirect = new Intent(user_landing.this, create_user_account_funcitonality.class);
                                        startActivity(andThenRedirect);
                                    }
                            )
                            .setNegativeButton(
                                    "No",
                                    (DialogInterface di, int i) -> {
                                        di.dismiss();
                                    }
                            );

                    //launch the dialog
                    AlertDialog message = alertDialog.create();
                    message.show();
                }
                else {
                    //TODO: Need to check this segment
                    //perform login authentication. Check if the corresponding password is correct
                    //values() stores all the User Objects
                    //since FindUser( ) returns a user object, we just need to extract the password information and do a quick comparison..
                    for (int i = 0; i < ConvertToUser().size(); i++){
                        if ((ConvertToUser().get(i).password).equals(passwordTextField.getText().toString())) {
                            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "User login is successful at this point");
                            //Add the user object into the SQLDB..
                            //dbHandler.AddUser(RetrieveTargetUser(usernameTextField.getText().toString()));
                            Wait(1500);
                            Intent andThenRedirect = new Intent();
                            andThenRedirect.putExtra("Username", usernameTextField.getText().toString());
                            andThenRedirect.putExtra("Password", passwordTextField.getText().toString());
                            //tell user_landing.java to pass the task to user_main.java
                            andThenRedirect.setClassName("sg.np.edu.mad.animationtest.user_landing", "sg.np.edu.mad.animationtest.user_main");
                            startActivity(andThenRedirect);
                            finish();
                        }
                        else {
                            alertDialog
                                    .setTitle("User authentication has failed.")
                                    .setMessage("Your password is incorrect. Please check your username and password and try logging in again into the system.")
                                    .setPositiveButton(
                                            "OK",
                                            (DialogInterface di, int j) -> {
                                                di.dismiss();
                                            }
                                    );
                            AlertDialog message = alertDialog.create();
                            message.show();
                        }
                    }
                }
            }
            else {
                //make a toast message that tells the user that the username and the password field has been left blank
                Toast.makeText(this, "Please enter your username and / or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}