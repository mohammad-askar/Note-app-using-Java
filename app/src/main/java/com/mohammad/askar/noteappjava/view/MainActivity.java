package com.mohammad.askar.noteappjava.view;

import static com.mohammad.askar.noteappjava.uitls.Constants.MY_TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mohammad.askar.noteappjava.R;
import com.mohammad.askar.noteappjava.data.local.entity.Note;
import com.mohammad.askar.noteappjava.databinding.ActivityMainBinding;
import com.mohammad.askar.noteappjava.viewModel.NoteViewModel;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    NoteViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(NoteViewModel.class);
    }
}