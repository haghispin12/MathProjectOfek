package com.example.mathprojectofek;

import static android.app.Activity.RESULT_OK;
import static android.widget.Toast.LENGTH_SHORT;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 .
 */
public class fragment_showusers extends Fragment {
    private EditText user;
    private TextView score;
    private TextView rate;
    private Button addPicture;
    private Button addUser;
    private ImageView image;
    private Button backToMain;
    Uri uri;
    MainViewModel mainViewModel;

    ActivityResultLauncher<Intent>startCamera=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()==RESULT_OK){
                image.setImageURI(uri);
            }
        }
    });
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initView(View view) {
        user =view.findViewById(R.id.user);
        score=view.findViewById(R.id.score);
        rate=view.findViewById(R.id.rate);
        addPicture=view.findViewById(R.id.addPicture);
        image=view.findViewById(R.id.image);
        addUser=view.findViewById(R.id.addUser);
        backToMain=view.findViewById(R.id.backToMain);

        addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues values=new ContentValues();
                values.put(MediaStore.Images.Media.TITLE,"new Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION,"From Camera");
                uri=requireContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
                Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                startCamera.launch(cameraIntent);
            }
        });
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                return inflater.inflate(R.layout.fragment_showusers,container,false);
            }
        });
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long id = 0;
                if (getActivity() != null) {
                }id = mainViewModel.dbAddUser(getActivity());
                Toast.makeText(getActivity(), id + "", Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fruit.xml for this fragment
        View view = inflater.inflate(R.layout.fragment_showusers, container, false);
        initView(view);
        mainViewModel=new ViewModelProvider(getActivity()).get(MainViewModel.class);
        this.score.setText("score: "+mainViewModel.getUser().getScore()+"");
        this.user.setText("name: "+mainViewModel.getUser().getName());
        this.rate.setText("rate: "+mainViewModel.getUser().getRate()+"");
        mainViewModel.users.observe(this , new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                userAdapter userAdapter;
                userAdapter = new userAdapter(users, new userAdapter.OnItemClicklListener1() {
                    @Override
                    public void OnItemClick(User item) {

                    }
                });
            }
        });

        return  view;

    }
}