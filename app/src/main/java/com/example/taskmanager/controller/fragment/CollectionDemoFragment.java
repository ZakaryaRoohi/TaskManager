package com.example.taskmanager.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taskmanager.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class CollectionDemoFragment extends Fragment {



    DemoCollectionAdapter demoCollectionAdapter;
    ViewPager2 viewPager;

    public CollectionDemoFragment() {
        // Required empty public constructor
    }

    public static CollectionDemoFragment newInstance() {
        CollectionDemoFragment fragment = new CollectionDemoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collection_demo, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        demoCollectionAdapter = new DemoCollectionAdapter(this);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(demoCollectionAdapter);
//
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText("OBJECT " + (position + 1))
        ).attach();


    }


    public class DemoCollectionAdapter extends FragmentStateAdapter {
        public DemoCollectionAdapter(Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // Return a NEW fragment instance in createFragment(int)
//            Fragment fragment = new DemoObjectFragment();
//            Bundle args = new Bundle();
//            // Our object is just an integer :-P
//            args.putInt(DemoObjectFragment.ARG_OBJECT, position + 1);
//            fragment.setArguments(args);
//            return fragment;
            return DemoObjectFragment.newInstance(position+1);
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
//    public  class DemoObjectFragment extends Fragment {
//        public static final String ARG_OBJECT = "object";
//
//
//        @Nullable
//        @Override
//        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                                 @Nullable Bundle savedInstanceState) {
//            return inflater.inflate(R.layout.fragment_collection_object, container, false);
//        }
//
//        @Override
//        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//            Bundle args = getArguments();
//            ((TextView) view.findViewById(android.R.id.text1))
//                    .setText(Integer.toString(args.getInt(ARG_OBJECT)));
//        }
//    }


}