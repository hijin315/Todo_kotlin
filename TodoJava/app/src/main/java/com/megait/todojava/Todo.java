package com.megait.todojava;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Todo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    // To-do의 내용
    private String content;

    // 완료 여부
    private boolean completed;
}
