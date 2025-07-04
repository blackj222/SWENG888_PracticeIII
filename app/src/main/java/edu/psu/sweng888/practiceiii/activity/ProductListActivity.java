package edu.psu.sweng888.practiceiii.activity;

import androidx.fragment.app.Fragment;

import edu.psu.sweng888.practiceiii.model.ProductListFragment;

public class ProductListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ProductListFragment();
    }
}
