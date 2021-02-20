package com.megait.todojava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
public class TodoRecyclerViewAdapter
        extends RecyclerView.Adapter<TodoRecyclerViewAdapter.MyViewHolder> {

    private List<Todo> list;

    public void updateList(List<Todo> list){
        this.list = list;
        notifyDataSetChanged(); // 요걸 실행해야 Adapter가 아이템의 변경을 감지하고
                                // 뷰를 업데이트한다.
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.getMCheckbox().setText(list.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Getter @Setter
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private CheckBox mCheckbox;
        public MyViewHolder(View view){
            super(view);
            mCheckbox = view.findViewById(R.id.cb_complete);
        }
    }
}
