package com.example.task4roombooking;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RIOccRoomAdapter extends RecyclerView.Adapter<RIOccRoomAdapter.ItemViewHolder> {
    private Context context;
    private List<RecItOccRoom> recItOccRoomList;

    private int selectedItemPosition = 0; // Initialize with an invalid position


    public RIOccRoomAdapter(Context context, List<RecItOccRoom> recItOccRoomList) {
        this.context = context;
        this.recItOccRoomList = recItOccRoomList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_occ_rec_items, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        RecItOccRoom itemRoom = recItOccRoomList.get(position);
        holder.bind(itemRoom);
        //holder.roomOccType.setTag(position);

        // Set background color based on selected item position
//        if (position == selectedItemPosition) {
//            holder.roomOccType.setChecked(true);
//        } else {
//            holder.roomOccType.setChecked(false);
//        }
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Update selected item position and notify data set changed
//                selectedItemPosition = position;
//                notifyDataSetChanged();
//            }
//        });
        // Set text values for RadioButton and TextView
//        holder.roomOccType.setText(itemRoom.getRoomOccType());
//        holder.roomOccPrice.setText("Rs. " + itemRoom.getRoomOccPrice());
//        holder.roomOccTax.setText(itemRoom.getRoomOccTax() + "%");
//
//        // Set checked state for RadioButton based on lastCheckedPosition
//        holder.roomOccType.setOnCheckedChangeListener(null); // Clear listener to avoid triggering unwanted checks
//        holder.roomOccType.setChecked(position == selectedItemPosition);
//
//        // Set OnCheckedChangeListener for RadioButton
//        holder.roomOccType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    if (selectedItemPosition != position) {
//                        notifyItemChanged(selectedItemPosition); // Uncheck previous item
//                        selectedItemPosition = position;
//                        String typeval= itemRoom.getRoomOccType();
//                        String typeprice= itemRoom.getRoomOccPrice();
//                        String typetax= itemRoom.getRoomOccTax();
//
//                        holder.roomOccTypeVal.setText(typeval);
//                        holder.roomPrice.setText(typeprice);
//                        holder.roomTax.setText(typetax);
//
//                    }
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return recItOccRoomList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        RadioButton roomOccType;
        TextView roomOccPrice, roomOccTax;

        TextView roomOccTypeVal, roomPrice, roomTax;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            roomOccType = itemView.findViewById(R.id.rbroomOccType);
            roomOccPrice = itemView.findViewById(R.id.roomOccPrice);
            roomOccTax = itemView.findViewById(R.id.roomOccTax);

            roomOccTypeVal=itemView.findViewById(R.id.roomTypeVal);
            roomPrice=itemView.findViewById(R.id.roomPriceVal);
            roomTax=itemView.findViewById(R.id.roomTaxVal);
        }

        public void bind(RecItOccRoom recItOccRoom) {
            roomOccType.setText(recItOccRoom.getRoomOccType());
            roomOccPrice.setText(recItOccRoom.getRoomOccPrice());
            roomOccTax.setText(recItOccRoom.getRoomOccTax());
        }
    }
}
