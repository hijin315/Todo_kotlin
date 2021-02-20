package com.megait.todojava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private Button mSaveButton;
    private RecyclerView mRecyclerView;
    private EditText mTodoEditText;
    private AppDatabase db;

    private RecyclerView.LayoutManager mLayoutManager;
    private TodoRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSaveButton = findViewById(R.id.btn_save);
        mRecyclerView = findViewById(R.id.rv_result);
        mTodoEditText = findViewById(R.id.et_todo);

        // DB 로드하기
        db = Room.databaseBuilder(this, AppDatabase.class, "todo-db")
                .allowMainThreadQueries() // 메인스레드에서도 디비 실행이 가능하도록 설정
                                          // 디비 처리와 네트워크 통신(업로드,다운로드) 등의
                                          // 대규모 데이터 처리가 필요한 경우는
                                          // 메인스레드에서 실행을 못하게 막아놨다.
                .build();

        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new TodoRecyclerViewAdapter(db.todoDao().select());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 빈 Todo 객체 생성
                Todo todo = new Todo();

                // editText에 들어있는 입력 값을 String형으로 content 에 저장
                todo.setContent(mTodoEditText.getText().toString());

                // to_do 객체를 DB에 저장
                db.todoDao().insert(todo);

                //////////////////////////////////////////////////
                // DB 에서 To_do 객체들을 모두 조회 후
                // 1개의 String 으로 content들을 모은다.
                // String result = getString();

                // 모은 String을 결과뷰에 출력한다.
                // mRecyclerView.setText(result);
                /////////////////////////////////////////////////
                mAdapter.updateList(db.todoDao().select());


                // 입력란 텍스트를 지워준다.
                mTodoEditText.setText(null);
            }
        });

    }

    private String getString() {
        return db.todoDao().select()
                .stream().map(Todo::getContent)
                .collect(Collectors.joining("\n"));
    }


}