package sg.np.edu.mad.animationtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class user_main extends AppCompatActivity {

    public DBHandler dbHandler = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);
        //this java file handles user_page.xml

        Intent whiteHole = getIntent();
        String name = whiteHole.getStringExtra("Name");

        //Prepare to receive the information from 'recycler_view_interface.java' source code file


        ((TextView) findViewById(R.id.nameTextBox)).setText(name);
        ((TextView) findViewById(R.id.descriptionTextBox)).setText(whiteHole.getStringExtra("Description"));

        String[] output1 = whiteHole.getStringArrayExtra("FollowedWhoList");
        String[] output2 = whiteHole.getStringArrayExtra("FullUserList");
        String[] output3 = whiteHole.getStringArrayExtra("FullNameList");
        String[] output4 = whiteHole.getStringArrayExtra("FullDescriptionList");

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        ((LinearLayout) findViewById(R.id.signOut)).setOnClickListener(
            thenFunctionAs -> {
                alertDialog
                .setTitle("Confirm Log out?")
                .setMessage("You will be required to sign in again if you decide to sign out of the system. Do you wish to proceed?")
                .setPositiveButton(
                    "YES",
                    (DialogInterface di, int i) -> {
                        dbHandler.ClearDatabase(); //the database only contains one record....
                        Intent transporter = new Intent(sg.np.edu.mad.animationtest.user_main.this, sg.np.edu.mad.animationtest.user_landing.class);
                        startActivity(transporter);
                        finish();
                    }
                )
                .setNegativeButton(
                    "No",
                    (DialogInterface di, int i) -> {
                        di.dismiss();
                    }
                );
                alertDialog.create().show();
            }
        );

        //$("LinearLayout#exploreOtherUsers").onclick(() => { });
        ((LinearLayout) findViewById(R.id.exploreOtherUsers)).setOnClickListener(thenFunctionAs -> {
            //Redirect to the recycler's view
            Intent transporter = new Intent(sg.np.edu.mad.animationtest.user_main.this, sg.np.edu.mad.animationtest.recycler_view_interface.class);
            transporter.putExtra("ForwardFollowedWhoList", output1);
            transporter.putExtra("ForwardUsersList", output2);
            transporter.putExtra("ForwardNameList", output3);
            transporter.putExtra("ForwardDescriptionList", output4);
            transporter.putExtra("Name", name);
            //Send the description over to the 'recycler_view_interface.java' source code file
            transporter.putExtra("Description", whiteHole.getStringExtra("Description"));
            startActivity(transporter);
            finish();
        });
    }
}