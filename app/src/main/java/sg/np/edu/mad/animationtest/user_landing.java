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

import com.google.android.gms.common.util.ArrayUtils;
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

    //generate a password...
    private static void PasswordGenerator_x20length(ArrayList<String> password) {
        final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
        final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String DIGITS = "0123456789";
        final String SYMBOLS = "~!@#$%^&*()+`-|:<>?;'./";
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
        return new user_landing().ExtractAllFromDatabase().size() < 100;
    }

    //dangerous, use this method with care
    public static void WriteToFirebaseFirestore(HashMap<String, User> userData) {
        if (databaseHas100Entries()) {
            Log.d("Datasize", Integer.toString(new user_landing().ExtractAllFromDatabase().size()));
            Log.d("Function entry", "Starting to write");
            RESTdb.collection("LoginInformation").document("LoginInformation")
            .set(userData)
            .addOnSuccessListener((Void) -> {
                Log.d("DocumentUpdateStatus", "Data append successful");
            })
            .addOnFailureListener((Void) -> {
                Log.d("DocumentUpdateStatus", "Data append failed");
            });
        } else {
            Log.d("Message", "Has 100 entries!");
            return;
        }
    }

    public void Intermediary(){
        DocumentReference df = RESTdb.collection("LoginInformation").document("LoginInformation");
        HashMap<String, Object>[] abc = new HashMap[]{ new HashMap<String, Object>() };
        df.get().addOnSuccessListener(process-> {
            final HashMap<String, Object> a = ((HashMap<String, Object>)process.getData()); //all the data stored in a variable named a..
            Log.d("Data size", "" + a.size()); //99
            Log.d("Current Data", "" + a);
            ((TextView) findViewById(R.id.tempString)).setText(a.toString());
            ((TextView) findViewById(R.id.databaseStringLength)).setText(Integer.toString(a.size()));
        });
    }

    //Extract all the information from the database...
    public HashMap<String, Object> ExtractAllFromDatabase() {
        //Full firebase firestore document represented as a string
        HashMap<String, Object> a = new HashMap<>();
        /*String tempText = ((TextView) findViewById(R.id.tempString)).getText().toString();
        a.put("IDK", tempText);*/
        return new HashMap<>();
    }

    // use the 'FirebaseFirestore' to write to the Firebase storage...
    public static FirebaseFirestore RESTdb = FirebaseFirestore.getInstance();

    //check for whether the username exists in the database, false if it does not exist
    //Firestorage (root) -> collection node -> document node
    public Boolean isAnEntryInDatabase(String username, String string, String dataStrLen, String dataRawStrLen){
        boolean existance = false;
        Log.d("isAnEntryInDatabase", "Function begins execution....");
        Log.d("Size", "" + ConvertToUser(string, dataStrLen, dataRawStrLen).size());
/*        for (User u : ConvertToUser(string, dataStrLen, dataRawStrLen)){
            if (u.username.equals(username)){
                existance = true;
            }
        }*/
        return existance;
    }

    //This method is only used to log extremely long messages
    public static void DebugLog(String tag, String msg){
        int maxLogSize = 1000;
        for(int i = 0; i <= msg.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i+1) * maxLogSize;
            end = end > msg.length() ? msg.length() : end;
            Log.d(tag, msg.substring(start, end));
        }
    }

    //Convert the ExtractAllFromDatabase() into an HashMap that stores User objects
    //unpackage the object and form a user
    public static ArrayList<User> ConvertToUser(String string, String dataStrLen, String dataRawStrLen) {
        Log.d("ConvertingToUser", "Converting to user..");
        Log.d("NumOfEntries", dataStrLen);
        Log.d("NumRawEntries", dataRawStrLen);
        ArrayList<Integer> idList = new ArrayList<Integer>();
        ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<String> descriptionList = new ArrayList<String>();
        ArrayList<String> usernameList = new ArrayList<String>();
        ArrayList<String> passwordList = new ArrayList<String>();
        ArrayList<Boolean> followedList = new ArrayList<Boolean>();
        ArrayList<ArrayList<String>> followedWhoList = new ArrayList<ArrayList<String>>();
        String[] a = string.split("="); //split
        String intermediate = "";
        String tertiary = "";
        String beyondTertiary = "";
        for (int i = 0; i < a.length; i++) {
            intermediate += a[i];
        }
        String[] b = intermediate.split("\\{");
        for (int i = 0; i < b.length; i++) {
            tertiary += b[i];
        }
        String step = tertiary.replace("password", "").replace("name", "").replace("description", "").replace("followedWho", "").replace("followed", "").replace("id", "");
        String[] c = step.split("\\}");
        for (int i = 0; i < c.length; i++) {
            beyondTertiary += c[i];
        }
        String step2 = beyondTertiary.replace("user", "");
        DebugLog("STEP2", step2);
        String[] d = step2.split(" ");
        String step3 = "";
        for (int i = 0; i < d.length; i++) {
            Log.d("SplitStringStage4", "" + d[i]);
            step3 += d[i];
        }
        DebugLog("TAGMESSAGE", "" + step3); //NO problem till this point
        String[] e = step3.split(",");
        ArrayList<String> innerArray = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < e.length; i++) {
            DebugLog("SplitStringStage9", e[i]);
            if (e[i].startsWith("User")){ //Password go here
                passwordList.add(
                e[i]
                .replace("User", "")
                .replace(
                ((Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9").contains(Character.toString(e[i].charAt(5)))) && (Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9").contains(Character.toString(e[i].charAt(4))))
                ? e[i].substring(4, 6)
                : Character.toString(e[i].charAt(4)))
                , "")
                );
            }
            if ( //followedWho list go here   FIXME: INCONSISTENT ARRAY LENGTH FOR FOLLOWEDWHO
                (
                    (
                        //IMPORTANT -> '[]' OR '[Username_key?]' instead of '[Username1_key?, Username2_key?]
                        (
                            //Each username has a underscore character inside
                            //length of the string after and including the underscore character greater than 1
                            (e[i].contains("_") && (e[i].substring(e[i].lastIndexOf("_")).length() > 1))
                            //might end with the terminating character
                            ? e[i].endsWith("]") ||
                            //last character is 0 to 9 OR
                            (e[i].substring(e[i].lastIndexOf("_")).charAt(1) >= '0' && e[i].substring(e[i].lastIndexOf("_")).charAt(1) <= '9') ||
                            (
                                //last character is any small letter alphabet from 'a' to 'z' OR
                                (e[i].substring(e[i].lastIndexOf("_")).charAt(1) >= 'a' && e[i].substring(e[i].lastIndexOf("_")).charAt(1) <= 'z') ||
                                //last character is any capital letter alphabet from 'A' to 'Z'
                                (e[i].substring(e[i].lastIndexOf("_")).charAt(1) >= 'A' && e[i].substring(e[i].lastIndexOf("_")).charAt(1) <= 'Z')
                            )
                            //if length is not 1 then check whether the string ends of with an underscore character
                            //BUT WHAT IF the element is just simply '[]'???
                            : e[i].endsWith("_") || e[i].contains("[]")
                        )
                    )
                )
            ) {
                //If e[i].startsWith("[") then add a breakpoint, terminate when the loop meets a string that contains "]" inside
                //Keep adding the items that is between strings that have met the e[i].startsWith("]") condition and the e[i].endsWith("]") condition
                innerArray.add(e[i]);
            }
            if (i - 1 >= 0) { //Check the last character whether does it equate to a single underscore character, if not check the character to the right of the underscore
                if (e[i].contains("_") && (e[i-1].equals("true") || e[i-1].equals("false"))){
                    usernameList.add(e[i]);
                }
                //DO NOT USE OVERLAPPING CONDITIONS
                if (e[i].contains("Name-")) {
                    nameList.add(e[i]);
                }
                if (e[i].contains("Description-")) {
                    descriptionList.add(e[i]);
                }
                if (e[i].equals("true") || e[i].equals("false")) {
                    followedList.add(Boolean.parseBoolean(e[i]));
                }
                if (e[i].length() <= 2 && (e[i].charAt(0) >= '0' && e[i].charAt(0) <= '9')){
                    idList.add(Integer.parseInt(e[i]));
                }
            }
        }
        //Remove sandwiched usernames
        for (int j = 0; j < innerArray.size(); ++j){
            //Perform string manipulation and slicing '(internal array string grouping)'
            if (j + 1 < innerArray.size() && j - 1 >= 0){
                if (innerArray.get(j+1).contains("[") && innerArray.get(j-1).contains("]")){
                    innerArray.remove(j);
                }
            }
        }
        //Remove the last element from the array
        innerArray.remove(innerArray.size() - 1);
        for (int i = 0; i < innerArray.size(); i++){
            Log.d("FILTEREDELEMENTS", innerArray.get(i)); //Filtered elements return the correct result
            if (innerArray.get(i).startsWith("[")){
                followedWhoList.add(new ArrayList<>()); //Insert empty lists accordingly
            }
        }
        //innerArray.remove(innerArray.size() - 1); //Remove the last element from the array
        DebugLog("INNERARRAY", "" + innerArray);
        Log.d("INNERARRAYSIZEBEFORE", "" + innerArray.size());
        Log.d("CURRLENPASSWORD", "" + passwordList.size());
        Log.d("CURRLENNAME", "" + nameList.size());
        Log.d("CURRLENFOLLOWED", "" + followedList.size());
        Log.d("CURRLENDESCRIPTION", "" + descriptionList.size());
        Log.d("CURRLENID", "" + idList.size());
        Log.d("CURRLENUSERNAME", "" + usernameList.size());
        Log.d("CURRLENFOLLOWEDWHO", "" + followedWhoList.size());
        int j = 0;int startingIndex = 0;int endingIndex = 0;boolean onlyOneElement = false;
        //Until here everything is correct
        for (; ; ) {
            if (j < followedWhoList.size()) {
                Log.d("REPEATING", "The process is currently repeating");
                for (int i = 0; i < innerArray.size(); i++) {
                    if (innerArray.get(i).contains("[") && innerArray.get(i).contains("]")) {
                        Log.d("STARTINGINDEXNEW", "" + startingIndex);
                        Log.d("ENDINGINDEXNEW", "" + endingIndex);
                        startingIndex = i = endingIndex;
                        break;
                    }
                    if (innerArray.get(i).contains("[") && !innerArray.get(i).contains("]")) {
                        Log.d("STARTINGINDEX", "" + startingIndex);
                        startingIndex = i;
                    }
                    if (innerArray.get(i).contains("]")) {
                        endingIndex = i;
                        Log.d("ENDINGINDEX", "" + endingIndex);
                        break;
                    }
                }
                if (startingIndex == endingIndex) { //might be '[]' or '[Username]'
                    if ((innerArray.get(startingIndex).contains("[") && innerArray.get(startingIndex).contains("]")) && !innerArray.get(startingIndex).equals("[]")) {
                        onlyOneElement = true;
                    }
                }
                //Create a boundary (range)
                if (onlyOneElement || (startingIndex != endingIndex)) {
                    if (!onlyOneElement) {
                        do {
                            Log.d("BoundaryEntry", "If Statement Entry!");
                            Log.d("CURRVALJ", "" + j);
                            Log.d("LOADEDELEMENT", "" + innerArray.get(startingIndex));
                            followedWhoList.get(j).add(
                                innerArray.get(startingIndex)
                            );
                            ++startingIndex; //Move on to next element
                        } while (startingIndex <= endingIndex);
                    }
                    else {
                        followedWhoList.get(j).add(
                            innerArray.get(startingIndex)
                        );
                    }
                    //WHEN DONE: UPON SUCCESSFUL DELETION
                    startingIndex = 0; //initialize again to prepare for deletion
                    if (!onlyOneElement) {
                        //FIXME: Skipping of elements observed
                        do {
                            Log.d("INDEX", "" + startingIndex);
                            Log.d("RemovedElement", innerArray.get(startingIndex));
                            //Delete the element from the innerArray
                            innerArray.remove(startingIndex);
                            endingIndex--;
                        }
                        while (endingIndex >= 0);
                    }
                    else {
                        innerArray.remove(innerArray.get(startingIndex));
                    }
                }
                else { //Only for '[]'
                    innerArray.remove(innerArray.get(startingIndex)); //remove that element
                    //if just an empty array, add a null then skip that cell
                    followedWhoList.get(j).add(null);
                }
                j++;
                startingIndex = 0; //initialize
                endingIndex = 0; //initialize
                onlyOneElement = false;
                Log.d("ENDINGINDEXINITIALIZATION", "" + endingIndex);
            }
            else {
                Log.d("FINALINNERARRAYLENGTH", "" + innerArray.size()); //must be 0
                Log.d("BREAK", "Broke out of infinite loop");
                break;
            }
        }
        DebugLog("FINALARRAYOUTCOME", "" + followedWhoList);
        Log.d("FINALARRAYSIZE", "" + followedWhoList.size());
        //perform final array cleaning

        ArrayList<User> userUserList = new ArrayList<User>();
        if (idList.size() == followedList.size() && idList.size() == descriptionList.size() && idList.size() == nameList.size() && idList.size() == passwordList.size()  && idList.size() == usernameList.size()  && idList.size() == followedWhoList.size()) {
            //Share the same for loop if only the lengths of all the arrays involved are exactly equal to one another.
            for (int i = 0; i < followedWhoList.size(); i++) {
                userUserList.add(
                    new User(
                        idList.get(i),
                        nameList.get(i),
                        descriptionList.get(i),
                        usernameList.get(i),
                        passwordList.get(i),
                        followedList.get(i),
                        followedWhoList.get(i)
                    )
                );
            }
        }
        else {
            Log.e("FATAL EXCEPTION!", "Unbalanced array lengths");
        }
        Log.d("ReturnTheUserList", "Returning the array from the method....");
        return userUserList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //run through the entire SQLite database again......
        if (dbHandler.GetAllUsers().size() == 0) {
            Log.d("Redirect to login", "Going to user login activity");
            setContentView(R.layout.practical5_loginpage); //Set this content view if SharedPreferences contains NO DATA
            Intermediary(); //Load the information into an invisible textview LOL
        }
        else {
            Log.d(TAG, "Else statement executed");
            //straight away go to user_main.java
            Intent transporter = new Intent();
            transporter.setClassName("sg.np.edu.mad.animationtest.user_landing", "sg.np.edu.mad.animationtest.user_main");
            startActivity(transporter);
            finish();
        }

        //Repeat this process for 100 times
        do {
            PasswordGenerator_x20length(passwordList);
        }
        while (passwordList.size() <= 20);

        //Repeat this process for 100 times....
        do {
            UsernameGenerator_x100Quantity(usernameList);
        }
        while (usernameList.size() <= 20);

        //Generate 100 user data and append it into the database.
        int tag = 1;
        do {
            Random generator = new Random();
            int descriptionIntegerSegment = Math.abs(generator.nextInt());
            int nameIntegerSegment = Math.abs(generator.nextInt());
            String name = "Name-" + nameIntegerSegment;
            String description = "Description-" + descriptionIntegerSegment;
            //put all the information in a user object.
            userList.add(new User
                (
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
        while (tag <= 20);

        Log.d(TAG, "Code reached this checkpoint: Checkpoint 1");

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
            Log.d("ID", "" + userList.get(i).id);
            Log.d(USERNAME, (userList.get(i).username));
            Log.d(PASSWORD, (userList.get(i).followedWho) + "\n========================================================================");
        }

        //Add into the database...
        WriteToFirebaseFirestore(userData);

        //DeleteEntiresFirebaseFirestore();
        //dbHandler.ClearDatabase();

        Button myLoginButton = (Button) findViewById(R.id.myLoginButton);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        //Create a new user account
        TextView newUser = (TextView) findViewById(R.id.createNewUser);

        //if the user taps on the 'New User?' text
        newUser.setOnTouchListener((View v, MotionEvent m) -> {
            //go to another java class
            //Intent andThenRedirect = new Intent(user_landing.this, create_user_account_funcitonality.class);
            //startActivity(andThenRedirect);
            Toast.makeText(this, "This feature is not available as of now", Toast.LENGTH_SHORT).show();
            return m.isButtonPressed(R.id.createNewUser);
        });

        myLoginButton.setOnClickListener(ThenFunction -> {
            Log.d("DatabaseFullStringLen", "" + ((TextView) findViewById(R.id.tempString)).getText().length());
            DebugLog("DatabaseFullString", ((TextView) findViewById(R.id.tempString)).getText().toString());
            Log.d("DatabaseEntries", ((TextView) findViewById(R.id.databaseStringLength)).getText().toString());
            String result = ((TextView) findViewById(R.id.tempString)).getText().toString();
            String result1 = ((TextView) findViewById(R.id.databaseStringLength)).getText().toString();
            String result2 = Integer.toString(((TextView) findViewById(R.id.tempString)).getText().length());
            //Take in the password.
            EditText passwordTextField = (EditText) findViewById(R.id.passwordField);
            //then take in the username
            EditText usernameTextField = (EditText) findViewById(R.id.usernameField);
            Log.d(TAG, "The code reached here");
            if (passwordTextField.getText().length() != 0 && usernameTextField.getText().length() != 0) {
                Log.d("Account verification", "Verification started....");
                if (!isAnEntryInDatabase(usernameTextField.getText().toString(), result, result1, result2)) {
                    alertDialog
                    .setTitle("User not found")
                    .setMessage("The entered user cannot be found. Do you want to create a new account?")
                    .setPositiveButton(
                        "Yes",
                        (DialogInterface di, int i) -> {
                            di.dismiss();
                            //then redirect to another activity...
                            //Intent andThenRedirect = new Intent(user_landing.this, create_user_account_funcitonality.class);
                            //startActivity(andThenRedirect);
                            Toast.makeText(this, "This feature is not available as of now", Toast.LENGTH_SHORT).show();
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
                    for (int i = 0; i < ConvertToUser(result, result1, result2).size(); i++){
                        if ((ConvertToUser(result, result1, result2).get(i).password).equals(passwordTextField.getText().toString())) {
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