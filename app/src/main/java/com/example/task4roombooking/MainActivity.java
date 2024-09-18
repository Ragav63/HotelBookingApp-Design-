package com.example.task4roombooking;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem sDates, sRooms, cDetails, confirmDetails, mPayment;
//    PagerAdapter pagerAdapter;

//    private FrameLayout fragmentContainer;
    private FragmentManager fragmentManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sDates=findViewById(R.id.selectDates);
        sRooms=findViewById(R.id.selectRooms);
        cDetails=findViewById(R.id.contactDetails);
        confirmDetails=findViewById(R.id.confirmDetails);
        mPayment=findViewById(R.id.makePayment);

        tabLayout = findViewById(R.id.include);
//        fragmentContainer = findViewById(R.id.fragmentcontainer);
        fragmentManager = getSupportFragmentManager();

        // Replace the initial fragment
        replaceFragment(new DateFragment());

//        // Set the default fragment
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,
//                new DateFragment()).commit();

//        ViewPager viewPager=findViewById(R.id.fragmentcontainer);
//        tabLayout=findViewById(R.id.include);


//
//        pagerAdapter=new PagerAdapter(getSupportFragmentManager(),5);
//        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment changeFragment=null;
//                viewPager.setCurrentItem(tab.getPosition());

                int position=tab.getPosition();

                switch (position){
                    case 0:
                        replaceFragment(new DateFragment());
                        tabLayout.getTabAt(0).select();
                        break;
                    case 1:
                        replaceFragment(new RoomFragment());
                        tabLayout.getTabAt(1).select();
                        break;
                    case 2:
                        replaceFragment(new ContactDetailsFragment());
                        tabLayout.getTabAt(2).select();
                        break;
                    case 3:
                        replaceFragment(new ConfirmDetailsFragment());
                        tabLayout.getTabAt(3).select();
                        break;
                    default:
                        replaceFragment(new PaymentFragment());
                        break;
                }

//                if(tab.getPosition()==0 || tab.getPosition()==1 || tab.getPosition()==2 || tab.getPosition()==3 || tab.getPosition()==4){
//                    pagerAdapter.notifyDataSetChanged();
//                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }
    // Method to replace fragment in the FrameLayout
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentcontainer, fragment);
        transaction.commit();


        // Get the tab position based on the fragment
        int tabPosition = getTabPosition(fragment);

        selectTabByFragment(fragment);
        // Select the corresponding tab
        if (tabPosition != -1) {
            tabLayout.getTabAt(tabPosition).select();
        }
    }

    public void selectTabByFragment(Fragment fragment) {
        int tabPosition = getTabPosition(fragment);
        if (tabPosition != -1) {
            tabLayout.getTabAt(tabPosition).select();
        }
    }

    // Method to get the tab position based on the fragment
    private int getTabPosition(Fragment fragment) {
        if (fragment instanceof DateFragment) {
            return 0;
        } else if (fragment instanceof RoomFragment) {
            return 1;
        } else if (fragment instanceof ContactDetailsFragment) {
            return 2;
        } else if (fragment instanceof ConfirmDetailsFragment) {
            return 3;
        } else if (fragment instanceof PaymentFragment) {
            return 4;
        }
        return -1;
    }

}