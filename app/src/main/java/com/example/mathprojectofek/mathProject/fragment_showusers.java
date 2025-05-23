package com.example.mathprojectofek.mathProject;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathprojectofek.R;

import java.util.ArrayList;

/**
 .
 */
public class fragment_showusers extends Fragment implements MenuProvider{
    private EditText userName;
    private TextView score;
    private TextView rate;
    private Button addPicture;
    private Button addUser;
    private RecyclerView rcUsers;
    private ImageView image;
    private Button backToMain;
    private MenuItem itemDelete;
    private MenuItem itemEdit;
    Uri uri;
    MainViewModel mainViewModel;
    User currentUser=new User();
    ActivityResultLauncher<Intent>startCamera=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()==RESULT_OK){
                image.setImageURI(uri);
                mainViewModel.getUser().setUri(uri);
            }
        }
    });
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initView(View view) {
        userName =view.findViewById(R.id.userName);
        score=view.findViewById(R.id.score);
        rate=view.findViewById(R.id.rate);
        addPicture=view.findViewById(R.id.addPicture);
        image=view.findViewById(R.id.image);
        addUser=view.findViewById(R.id.addUser);
        rcUsers=view.findViewById(R.id.rcUsers);
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
                if (getActivity() != null)
                    id = mainViewModel.dbAddUser(getActivity());
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
        this.userName.setText(mainViewModel.getUser().getName());
        this.rate.setText("rate: "+mainViewModel.getUser().getRate()+"");
        requireActivity().addMenuProvider(this);
        mainViewModel.users.observe(requireActivity() , new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                userAdapter userAdapter = new userAdapter(users, new userAdapter.OnItemClicklListener1() {
                    @Override
                    public void OnItemClick(User item) {
                        Toast.makeText(requireActivity(), item.getName(), Toast.LENGTH_SHORT).show();
                        userName.setText(item.getName());
                        score.setText("score:"+item.getScore());
                        rate.setText("rate:"+item.getRate());
                        currentUser=item;
                        itemDelete.setVisible(true);
                        itemEdit.setVisible(true);
                    }
                });
                rcUsers.setLayoutManager(new LinearLayoutManager(requireActivity()));
                rcUsers.setAdapter(userAdapter);
                rcUsers.setHasFixedSize(true);
            }
        });
        mainViewModel.getUsers(getActivity());
        return  view;
    }
    @Override
    public void onCreateMenu(@NonNull Menu menu,@NonNull MenuInflater menuInflater){
        menuInflater.inflate(R.menu.main_menu,menu);
        itemDelete= menu.findItem(R.id.action_delete);
        itemDelete.setVisible(false);
        itemEdit=menu.findItem(R.id.action_edit);
        itemEdit.setVisible(false);
        super.onCreateOptionsMenu(menu,menuInflater);
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        int id=menuItem.getItemId();
        switch (id){
            case R.id.action_delete:
                //do something
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(requireActivity());
                alertDialog.setTitle("Delete");
                alertDialog.setMessage("Do you want to delete?");
                alertDialog.setCancelable(true);
                alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mainViewModel.dbDelete(getActivity(),currentUser);
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();
                return true;
            case R.id.action_edit:
                //do something
                currentUser.setName(userName.getText().toString());
                mainViewModel.dbUpdate(getActivity(),currentUser);
                return true;
        }
        return false;
    }

}