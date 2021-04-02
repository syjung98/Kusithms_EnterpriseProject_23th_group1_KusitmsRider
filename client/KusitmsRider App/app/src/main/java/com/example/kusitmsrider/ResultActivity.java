package com.example.kusitmsrider;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;


public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressDialog customProgressDialog;
    static File txtfile;
    LinearLayout button1;
    LinearLayout king;
    TextView textview1;
    TextView textview2;
    TextView textview3;
    TextView textview4;


    TextView happyking;
    TextView sadking;
    TextView qking;
    TextView exking;
    TextView emoking;


    ImageView imageview1;
    private static String SERVER_IP = "18.218.55.54";
    private static String CONNECT_MSG = "connect";
    private Socket client;
    private static DataInputStream dataInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        king = findViewById(R.id.king);
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);

        imageview1 = findViewById(R.id.imageview1);
        textview1 = findViewById(R.id.textview1);
        textview2 = findViewById(R.id.textview2);
        textview3 = findViewById(R.id.textview3);
        textview4 = findViewById(R.id.textview4);

        happyking = findViewById(R.id.happyking);
        sadking = findViewById(R.id.sadking);
        qking = findViewById(R.id.qking);
        exking = findViewById(R.id.exking);
        emoking = findViewById(R.id.emoking);

        king.setVisibility(View.GONE);
        customProgressDialog = new ProgressDialog(this);
        customProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    }

    private class Connect extends AsyncTask< String , String,Void > {
        private String input_message;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                client = new Socket(SERVER_IP, 8080);
                dataInput = new DataInputStream(client.getInputStream());

            } catch (UnknownHostException e) {
                String str = e.getMessage().toString();
                Log.w("discnt", str + " 1");
            } catch (IOException e) {
                String str = e.getMessage().toString();
                Log.w("discnt", str + " 2");
            }
            while(true){
                try{
                    byte[] buf = new byte[100];
                    int read_Byte  = dataInput.read(buf);
                    input_message = new String(buf, 0, read_Byte);
                    if(input_message != null){
                        FirebaseStorage storage = FirebaseStorage.getInstance("gs://kusitmsrider.appspot.com");
                        StorageReference storageRef = storage.getReference();
                        StorageReference pathReference = storageRef.child("result_chart.png");

                        try {

                            //로컬에 저장할 폴더의 위치
                            File path = new File(getFilesDir().getPath());
                            //저장하는 파일의 이름
                            final File file = new File(path, "result_chart.png");
                            try {
                                //저장할 폴더가 없으면 생성
                                if (!path.exists()) path.mkdirs();

                                file.createNewFile();

                                //파일을 다운로드하는 Task 생성, 비동기식으로 진행
                                final FileDownloadTask fileDownloadTask = pathReference.getFile(file);
                                fileDownloadTask.addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {


                                        king.setVisibility(View.VISIBLE);
                                        DatabaseReference mRootRef2 = FirebaseDatabase.getInstance().getReference();
                                        mRootRef2.child("분석 결과(글)").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                                if (!task.isSuccessful()) {
                                                    Log.e("firebase", "Error getting data", task.getException());
                                                }
                                                else {

                                                    String[] result_split = String.valueOf(task.getResult().getValue()).split("순위");
                                                    String result_name = result_split[2].substring(6,result_split[2].length()-2);
                                                    String result_ratio = result_split[1].substring(7,result_split[1].length()-3);

                                                    textview3.setText("이름 : " + result_name);
                                                    textview4.setText("비율 : " + result_ratio);



                                                    mRootRef2.child("분석 결과(글)").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                                                            if (!task.isSuccessful()) {
                                                                Log.e("firebase", "Error getting data", task.getException());
                                                            }
                                                            else {
                                                                String[] result_king = String.valueOf(task.getResult().getValue()).split(",");
                                                                exking.setText(result_king[0].substring(5));
                                                                happyking.setText(result_king[1].substring(6));
                                                                qking.setText(result_king[2].substring(5));
                                                                sadking.setText(result_king[3].substring(4));
                                                                emoking.setText(result_king[4].substring(4));

                                                            }
                                                        }
                                                    });

                                                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                                                }
                                            }
                                        });

                                        imageview1.setImageURI(Uri.fromFile(file));
                                        customProgressDialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "분석 완료!", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        //다운로드 실패 후 할 일
                                    }
                                }).addOnProgressListener(new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
                                    @Override
                                    //진행상태 표시
                                    public void onProgress(FileDownloadTask.TaskSnapshot taskSnapshot) {

                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                                Log.w("에러-------", "파일없음" + " 1");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.w("result","success");
                        break;
                    }
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
    public void onClick(View v) {
        if (v.getId() == R.id.button1) {

            DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference conditionRef_name = mRootRef.child("switch");
            conditionRef_name.setValue("false");

            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("text/plain");
            startActivityForResult(intent, 10);
            button1.setVisibility(View.GONE);
        }

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {
            Toast.makeText(getApplicationContext(), "분석 중...", Toast.LENGTH_SHORT).show();
            customProgressDialog.show();
            Uri uri = data.getData();FirebaseStorage storage = FirebaseStorage.getInstance("gs://kusitmsrider.appspot.com");
            StorageReference storageRef = storage.getReference().child("index.txt");
            storageRef.putFile(uri);
            readTextFile(uri);

        }
    }

    private String readTextFile(Uri uri)
    {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(getContentResolver().openInputStream(uri)));

            String line = "";
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

            DatabaseReference conditionRef = mRootRef.child("카톡 데이터(before)");
            conditionRef.setValue(builder.toString());



            Connect connect = new Connect(); // 올렸다고 서버에 알림
            connect.execute(CONNECT_MSG);
            reader.close();

        }
        catch (IOException e) {e.printStackTrace();}
        return builder.toString();
    }

}
