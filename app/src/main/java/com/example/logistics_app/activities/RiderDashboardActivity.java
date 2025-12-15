package com.example.logistics_app.activities; // CHECK PACKAGE NAME

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.logistics_app.R; // CHECK IMPORT
import com.example.logistics_app.adapters.DeliveryAdapter; // CHECK IMPORT
import com.example.logistics_app.models.DeliveryOrder; // CHECK IMPORT
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class RiderDashboardActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    private RecyclerView recyclerView;
    private DeliveryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_dashboard);

        recyclerView = findViewById(R.id.recycler_view_deliveries);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add the custom white line separator
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_white_line));
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new DeliveryAdapter(this, getDummyCurrentOrders());
        recyclerView.setAdapter(adapter);

        checkAndRequestLocationPermissions();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    adapter = new DeliveryAdapter(RiderDashboardActivity.this, getDummyCurrentOrders());
                } else {
                    adapter = new DeliveryAdapter(RiderDashboardActivity.this, getDummyPastOrders());
                }
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void checkAndRequestLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Location Access Granted.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Location permission is required for dispatching and accurate tracking.", Toast.LENGTH_LONG).show();
        }
    }

    // --- Mock Data Functions ---
    private List<DeliveryOrder> getDummyCurrentOrders() {
        List<DeliveryOrder> orders = new ArrayList<>();
        orders.add(new DeliveryOrder("#12345", "Logistics Hub B", "123 Oak St, City A", "10:30 AM", false));
        orders.add(new DeliveryOrder("#12346", "Warehouse A", "45 Elm Dr, City B", "11:45 AM", true));
        orders.add(new DeliveryOrder("#12347", "Depot C", "78 Pine Ave, City C", "01:00 PM", false));
        return orders;
    }
    private List<DeliveryOrder> getDummyPastOrders() {
        List<DeliveryOrder> orders = new ArrayList<>();
        orders.add(new DeliveryOrder("#12344", "Warehouse Z", "101 River Rd", "Yesterday", true));
        orders.add(new DeliveryOrder("#12343", "Store Q", "30 Sunkist Blvd", "Yesterday", true));
        return orders;
    }
}