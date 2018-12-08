package net.kikikan.fragmentviewer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ChangeFragment(View view) {
        if (view == findViewById(R.id.button1)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new FragmentOne()).commit();
        }
        else
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new FragmentTwo()).commit();
    }
}
