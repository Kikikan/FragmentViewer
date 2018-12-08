package net.kikikan.fragmentviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Fragment[] fragments = new Fragment[] {new FragmentOne(), new FragmentTwo(), new FragmentThree(), new FragmentFour(),
            new FragmentFive(), new FragmentSix(), new FragmentSeven(), new FragmentEight(), new FragmentNine(), new FragmentTen()};

    private int currentF = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ChangeFragment(View view) {
        if (view == findViewById(R.id.nextButton)) {
            setCurrentF(currentF + 1);
        }
        else
            setCurrentF(currentF - 1);
    }

    public void setCurrentF(int index) {
        if (index > 9 || index < 0)
            return;
        currentF = index;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragments[index]).commit();
    }
}
