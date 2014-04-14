package com.acsalu.fragmenttransaction.app;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

enum FragmentType {
    FRAGMENT_ONE, FRAGMENT_TWO
}

public class MainActivity extends Activity {

    Button mButton;
    FragmentType currentFragmentType;
    FragmentOne fragmentOne;
    FragmentTwo fragmentTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentFragmentType = FragmentType.FRAGMENT_ONE;

        // Find views.
        mButton = (Button) findViewById(R.id.button_switch);
        fragmentOne = new FragmentOne();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragmentOne);
        ft.commit();

        // Set listeners.
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Button Pressed", Toast.LENGTH_SHORT);
                Log.d("xxx", "button clicked");
                switchFragment();
            }
        });
    }

    protected void switchFragment() {

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        if (currentFragmentType == FragmentType.FRAGMENT_ONE) {
            Toast.makeText(this, "Switching to FragmentTwo", Toast.LENGTH_SHORT).show();
            currentFragmentType = FragmentType.FRAGMENT_TWO;
            if (fragmentTwo == null) {
                Log.d("Fragment", "new FragmentTwo");
                fragmentTwo = new FragmentTwo();
            }
            ft.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            ft.replace(R.id.fragment_container, fragmentTwo, "fragmentTwo");
            ft.commit();

        } else {
            Toast.makeText(this, "Switching to FragmentOne", Toast.LENGTH_SHORT).show();
            currentFragmentType = FragmentType.FRAGMENT_ONE;
            ft.setCustomAnimations(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
            ft.replace(R.id.fragment_container, fragmentOne, "fragmentOne");
            ft.commit();

        }



    }
}
