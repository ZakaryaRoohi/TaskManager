package com.example.taskmanager.controller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.taskmanager.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.taskmanager.controller.fragment.TaskPagerFragment;

public class TaskPagerActivity extends AppCompatActivity {
    /**
     * Crate start activity and begin start fragment
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_pager);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if(fragment == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, TaskPagerFragment.newInstance())
                    .commit();
        }
//        CollectionDemoFragment fragment = new CollectionDemoFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_container, fragment);
//        transaction.commit();
    }
}