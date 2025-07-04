package edu.psu.sweng888.practiceiii.activity;

import androidx.fragment.app.Fragment;

import edu.psu.sweng888.practiceiii.model.*;

public class ProductActivity extends SingleFragmentActivity {
    /** Called when the activity is first created. */
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment);
//
//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//
//        if (fragment == null) {
//            fragment = new ProductFragment();
//            fm.beginTransaction()
//                    .add(R.id.fragment_container, fragment)
//                    .commit();
//        }
//    }

    @Override
    protected Fragment createFragment() {
        return new ProductFragment();
    }
}
