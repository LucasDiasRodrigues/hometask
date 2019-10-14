package com.lucas.hometask.casa;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class CasaViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] pages;
    private Context context;

    public CasaViewPagerAdapter(@NonNull FragmentManager fm, String[] pages, Context context) {
        super(fm);
        this.context = context;
        this.pages = pages;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (pages[position]) {
            case "Tarefas":
                return new TarefasFragment();
            case "Regras":
                return new RegrasFragment();
            case "Moradores":
                return new MoradoresFragment();
            default:
                return new TarefasFragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pages[position];
    }

    @Override
    public int getCount() {
        return pages.length;
    }
}
