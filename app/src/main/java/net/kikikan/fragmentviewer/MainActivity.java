package net.kikikan.fragmentviewer;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Fragment[] fragments = new Fragment[] {new FragmentOne(), new FragmentTwo(), new FragmentThree(), new FragmentFour(),
            new FragmentFive(), new FragmentSix(), new FragmentSeven(), new FragmentEight(), new FragmentNine(), new FragmentTen()};

    private int currentF = 0;
    private SensorManager sm;
    private Sensor gyroscopeSensor;
    private SensorEventListener gyroListener;
    private boolean turned = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if (gyroscopeSensor == null)
            Toast.makeText(getApplicationContext(), "Ennek az eszköznek nincsen gyroscopeja.", Toast.LENGTH_SHORT).show();
        else
            gyroListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {

                    float y = sensorEvent.values[2];
                    if (y < -1f && !turned) {
                        setCurrentF(currentF + 1);
                        turned = true;
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                turned = false;
                            }
                        }, 1000);
                    }
                    else if (y > 1f && !turned) {
                        setCurrentF(currentF - 1);
                        turned = true;
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                turned = false;
                            }
                        }, 1000);
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };
    }

    public void setCurrentF(int index) {
        if (index > 9 || index < 0)
            return;
        currentF = index;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragments[index]).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(gyroListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected  void onPause() {
        super.onPause();
        sm.unregisterListener(gyroListener);
    }
}
