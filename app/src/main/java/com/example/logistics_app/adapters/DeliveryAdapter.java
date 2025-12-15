package com.example.logistics_app.adapters; // CHECK PACKAGE NAME

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.logistics_app.R; // CHECK IMPORT
import com.example.logistics_app.activities.DeliveryDetailActivity; // CHECK IMPORT
import com.example.logistics_app.models.DeliveryOrder; // CHECK IMPORT
import java.util.List;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder> {

    private final Context context;
    private final List<DeliveryOrder> orderList;

    public DeliveryAdapter(Context context, List<DeliveryOrder> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public DeliveryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_delivery_order, parent, false);
        return new DeliveryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryViewHolder holder, int position) {
        DeliveryOrder order = orderList.get(position);
        holder.tvOrderId.setText(order.getOrderId());
        holder.tvFromInfo.setText("FROM: " + order.getFromLocation());
        holder.tvToInfo.setText("TO: " + order.getToLocation());
        holder.tvArrivalTime.setText("EST. ARRIVAL: " + order.getEstimatedArrival());

        holder.checkboxStatus.setChecked(order.isSent());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DeliveryDetailActivity.class);
            intent.putExtra("ORDER_ID", order.getOrderId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class DeliveryViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderId, tvFromInfo, tvToInfo, tvArrivalTime;
        CheckBox checkboxStatus;

        public DeliveryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tv_order_id);
            tvFromInfo = itemView.findViewById(R.id.tv_from_info);
            tvToInfo = itemView.findViewById(R.id.tv_to_info);
            tvArrivalTime = itemView.findViewById(R.id.tv_arrival_time);
            checkboxStatus = itemView.findViewById(R.id.checkbox_status);
        }
    }
}