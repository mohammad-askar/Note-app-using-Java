package com.mohammad.askar.noteappjava.view;

import static com.mohammad.askar.noteappjava.uitls.Constants.MY_TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.drawable.DrawableUtils;
import com.mohammad.askar.noteappjava.R;
import com.mohammad.askar.noteappjava.data.local.entity.Note;
import com.mohammad.askar.noteappjava.databinding.ActivityMainBinding;
import com.mohammad.askar.noteappjava.view.adapter.NoteAdapter;
import com.mohammad.askar.noteappjava.viewModel.NoteViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);
    }
}