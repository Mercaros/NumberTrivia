package hva.numbertrivia;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.MyViewHolder> {
    private List<Number> numberList;

    public NumberAdapter(List<Number> numberList) {
        this.numberList = numberList;
    }

    @NonNull
    @Override
    public NumberAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trivia_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberAdapter.MyViewHolder holder, int position) {
        Number number = numberList.get(position);
        if (position % 2 == 0) {
            holder.triviaNumber.setText(String.valueOf(number.getNumber()));
            holder.description.setText(number.getText());
            holder.triviaNumber.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);
            holder.description.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        } else {
            holder.triviaNumber.setText(number.getText());
            holder.description.setText(String.valueOf(number.getNumber()));
            holder.triviaNumber.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            holder.description.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);
        }
    }

    @Override
    public int getItemCount() {
        return numberList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView triviaNumber;
        public TextView description;

        public MyViewHolder(View view) {
            super(view);
            triviaNumber = view.findViewById(R.id.textview_left);
            description = view.findViewById(R.id.textview_right);
        }
    }

    public void swapList(List<Number> newList) {
        numberList = newList;
        if (newList != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }
}
