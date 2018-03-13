package com.bryonnicoson.wordlistloader;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by bryon on 3/13/18.
 */

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    public static final String TAG = WordListAdapter.class.getSimpleName();

    private final LayoutInflater mInflater;
    private Context mContext;
    private Cursor mCursor = null;

    public WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView wordItemView;

        public WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
        }
    }

    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(WordListAdapter.WordViewHolder holder, int position) {
        String word = "";

        if (mCursor != null) {
            if (mCursor.moveToPosition(position)){
                int indexWord = mCursor.getColumnIndex(Contract.WordList.KEY_WORD);
                word = mCursor.getString(indexWord);
                holder.wordItemView.setText(word);
            } else {
                holder.wordItemView.setText(R.string.error_no_word);
            }
        } else {
            // todo: Notify user
            Log.e(TAG, "onBindViewHolder: Cursor is null.");
        }
    }

    @Override
    public int getItemCount() {
        if (mCursor != null) {
            return mCursor.getCount();
        } else {
            return -1;
        }
    }

    public void setData(Cursor cursor) {
        mCursor = cursor;
        notifyDataSetChanged();
    }
}
