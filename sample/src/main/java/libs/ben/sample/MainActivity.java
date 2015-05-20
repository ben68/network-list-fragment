package libs.ben.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment f = SampleListFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, f)
                        .commit();
    }

}
