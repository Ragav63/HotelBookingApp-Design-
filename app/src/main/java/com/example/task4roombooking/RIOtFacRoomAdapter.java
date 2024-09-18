package com.example.task4roombooking;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RIOtFacRoomAdapter extends RecyclerView.Adapter<RIOtFacRoomAdapter.ItemViewHolder> {
    private Context context;
    private List<RecItOtFacRoom> recItOtFacRoomList;
    private int selectedItemPosition; // Initialize with an invalid position

    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {

        void onItemClick(RecItOtFacRoom itemRoom, boolean isChecked);
    }

    public RIOtFacRoomAdapter(Context context, List<RecItOtFacRoom> recItOtFacRoomList, OnItemClickListener itemClickListener) {
        this.context = context;
        this.recItOtFacRoomList = recItOtFacRoomList;
        this.itemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_fac_rec_items, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        RecItOtFacRoom itemRoom = recItOtFacRoomList.get(position);
        holder.bind(itemRoom);
        holder.roomOtFacCheckbox.setText(itemRoom.getRoomOFCBox());
        holder.roomOtFacCheckbox.setChecked(itemRoom.isChecked());

//        // Set background color based on selected item position
//        if (position == selectedItemPosition) {
//
//        } else {
//
//        }

        holder.roomOtFacCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            itemRoom.setChecked(isChecked);
            selectedItemPosition = position;
                notifyDataSetChanged();
            itemClickListener.onItemClick(itemRoom, isChecked);
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Update selected item position and notify data set changed
//                selectedItemPosition = position;
//                notifyDataSetChanged();
//                itemClickListener.onItemClick(itemRoom);
//            }
//        });

    }

    private void displayItemDetails(RecItOtFacRoom item) {
        // Display details in a Toast, you can modify this to suit your needs
        String details = "Facility: " + item.getRoomOFCBox() + "\nPrice: Rs. " + item.getRoomOFPrice();
        Toast.makeText(context, details, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return recItOtFacRoomList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        CheckBox roomOtFacCheckbox;
        TextView roomOtFacPrice;
        TextView roomOtFacValue;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            roomOtFacCheckbox = itemView.findViewById(R.id.roomOFCBox);
            roomOtFacPrice = itemView.findViewById(R.id.roomOFPrice);
            roomOtFacValue=itemView.findViewById(R.id.roomOFVal);

        }

        public void bind(RecItOtFacRoom recItOccRoom) {
            roomOtFacCheckbox.setText(recItOccRoom.getRoomOFCBox());
            roomOtFacPrice.setText(recItOccRoom.getRoomOFPrice());
//            roomOtFacValue.setText(recItOccRoom.getRoomOFPrice());
        }
    }
}
