package sg.np.edu.mad.animationtest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class recycler_view_interface extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_recycler_view_other_users_exploration);

        Intent getStringArrays = getIntent();
        //Receive both arrays.
        String[] result = getStringArrays.getStringArrayExtra("ForwardFollowedWhoList");
        String[] result1 = getStringArrays.getStringArrayExtra("ForwardUsersList");
        String[] result2 = getStringArrays.getStringArrayExtra("ForwardNameList");
        String[] result3 = getStringArrays.getStringArrayExtra("ForwardDescriptionList");
        String result4 = getStringArrays.getStringExtra("Name");

        Log.d("FOLLOWEDWHOLISTCONTENTS?", result != null ? result.length > 0 ? "Length of followedWho list is greater than 0" : "followedWhoList is empty": "The list from output 1 is not even sent over to this java file");
        Log.d("FULLUSERLISTCONTENTS?", result1 != null ? result1.length > 0 ? "Length of users list is greater than 0" : "users list is empty": "The list from output 2 is not even sent over to this java file");

        //Find the index position of the current user....

        //Bind to the recycler view adapter here
        RecyclerView rv = findViewById(R.id.mainRecyclerViewUserExploration);
        RecyclerViewAdapter rva = new RecyclerViewAdapter(this, result1, result, result2, result3);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setLayoutManager(llm);
        rv.setAdapter(rva);

        //When the user
        ((LinearLayout) findViewById(R.id.baseLinearLayout)).setOnClickListener(andThen -> {
            Intent intent = new Intent();

        });
    }
}