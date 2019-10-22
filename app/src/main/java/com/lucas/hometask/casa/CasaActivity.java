package com.lucas.hometask.casa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.lucas.hometask.LiveDatabase;
import com.lucas.hometask.R;
import com.lucas.hometask.model.Casa;

public class CasaActivity extends AppCompatActivity {

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView imageToolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FloatingActionButton fabRegras;
    private FloatingActionButton fabMoradores;

    private CasaViewPagerAdapter adapter;

    private Casa casa = LiveDatabase.getInstance().getCasa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casa);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

        collapsingToolbar = findViewById(R.id.collapsingToolbar);
        collapsingToolbar.setTitle(casa.getNome());

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new CasaViewPagerAdapter(getSupportFragmentManager(),
                getResources().getStringArray(R.array.casa_abas),
                this)
        );
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeActiveFab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager, true);

        fabRegras = findViewById(R.id.fabRegras);
        fabMoradores = findViewById(R.id.fabMoradores);
    }


    public void changeActiveFab(int position) {
        switch (position) {
            case 0:
                fabRegras.hide();
                fabMoradores.hide();
                break;
            case 1:
                fabRegras.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (viewPager.getCurrentItem() == 1) fabRegras.show();
                    }
                }, 300);
                fabMoradores.hide();
                break;
            case 2:
                fabRegras.hide();
                fabMoradores.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (viewPager.getCurrentItem() == 2) fabMoradores.show();
                    }
                }, 300);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
