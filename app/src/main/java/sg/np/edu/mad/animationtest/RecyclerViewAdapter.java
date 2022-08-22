package sg.np.edu.mad.animationtest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Array;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    //NameHolder
    //DescriptionHolder

    Context context;
    String[] userList; String[] followedWhoList; String[] nameList; String[] descriptionList; int targetIndex; String currUsername;

    public RecyclerViewAdapter(Context context, String[] userList, String[] followedWhoList, String[] nameList, String[] descriptionList, int targetIndex, String currUsername){
        this.context = context;
        this.userList = userList;
        this.followedWhoList = followedWhoList;
        this.nameList = nameList;
        this.descriptionList = descriptionList;
        this.targetIndex = targetIndex;
        this.currUsername = currUsername;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = null;
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_viewholder_layout, parent, false);
        vh = new LayoutInflaterRecyclerView(item);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LayoutInflaterRecyclerView vh = (LayoutInflaterRecyclerView) holder;
        if (position != targetIndex) {
            vh.tv1.setText(userList[position]);
            vh.tv2.setText(descriptionList[position]);
            vh.tv3.setText(nameList[position]);
            vh.ll.setOnClickListener(andThen -> {
                Intent intent = new Intent();
                String internalTextUsername = vh.tv1.getText().toString();
                String internalTextDescription = vh.tv2.getText().toString();
                String internalTextName = vh.tv3.getText().toString();
                intent.putExtra("ReturnUserList", userList);
                intent.putExtra("ReturnNameList", nameList);
                intent.putExtra("ReturnDescriptionList", descriptionList);
                intent.putExtra("ReturnCurrUsername", currUsername);
                intent.putExtra("InternalUsername", internalTextUsername);
                intent.putExtra("InternalDescription", internalTextDescription);
                intent.putExtra("InternalName", internalTextName);
                intent.putExtra("ForwardFollowedWhoList", followedWhoList);
                intent.setClassName("sg.np.edu.mad.animationtest", "sg.np.edu.mad.animationtest.user_profile_display_and_handling");
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return userList.length - 1; //return the size of the array...
    }
}
