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
    String[] userList; String[] followedWhoList; String[] nameList; String[] descriptionList;

    public RecyclerViewAdapter(Context context, String[] userList, String[] followedWhoList, String[] nameList, String[] descriptionList){
        this.context = context;
        this.userList = userList;
        this.followedWhoList = followedWhoList;
        this.nameList = nameList;
        this.descriptionList = descriptionList;
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
        vh.tv1.setText(userList[position]);
        vh.tv2.setText(descriptionList[position]);
        vh.tv3.setText(nameList[position]);
    }

    @Override
    public int getItemCount() {
        return userList.length; //return the size of the array...
    }
}
