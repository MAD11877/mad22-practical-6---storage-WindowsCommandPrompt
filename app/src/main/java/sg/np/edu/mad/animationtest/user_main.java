package sg.np.edu.mad.animationtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class user_main extends AppCompatActivity {

    public DBHandler dbHandler = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);
        //this java file handles user_page.xml

        Intent whiteHole = getIntent();

        ((TextView) findViewById(R.id.nameTextBox)).setText(whiteHole.getStringExtra("Username"));
        ((TextView) findViewById(R.id.descriptionTextBox)).setText(whiteHole.getStringExtra("Password"));

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        (findViewById(R.id.signOut)).setOnClickListener(
            thenFunctionAs -> {
                alertDialog
                    .setTitle("Confirm Log out?")
                    .setMessage("You will be required to sign in again if you decide to sign out of the system. Do you wish to proceed?")
                    .setPositiveButton(
                        "YES",
                        (DialogInterface di, int i) -> {
                            dbHandler.ClearDatabase(); //the database only contains one record....
                            Intent transporter = new Intent();
                            transporter.setClassName("sg.np.edu.mad.animationtest.user_main", "sg.np.edu.mad.animationtest.user_landing.java");
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
            }
        );

        //$("LinearLayout#exploreOtherUsers").onclick(() => { });
        (findViewById(R.id.exploreOtherUsers)).setOnClickListener(thenFunctionAs -> {
            //Redirect to the recycler's view
            Intent teleporter = new Intent();
            teleporter.setClassName("sg.np.edu.mad.ipptready.user_main", "sg.np.edu.mad.ipptready.recycler_view_interface");
            startActivity(teleporter);
            finish();
        });
    }
}
