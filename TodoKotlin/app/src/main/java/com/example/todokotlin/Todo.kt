package com.example.todojava

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Todo {
    @PrimaryKey(autoGenerate = true) //autoGenerate = 엔티티가 추가될때마다 번호를 자동으로 지정하겠다.
    private val id = 0

    //Todo 의 내용
    private val content: String? = null

    //boolean타입은 사용못하지만 사용가능하게 해주는게 room
    private val completed = false //id가 private이니 게터세터 만들자~
}