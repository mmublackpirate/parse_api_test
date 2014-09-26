package com.yemyatthu.notepadapp;

import android.app.ListActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class NoteApp extends ListActivity {
    private ArrayList<Note> posts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_note_app);

        posts = new ArrayList<Note>();
        refreshPosts();
        NoteAdapter adapter = new NoteAdapter(posts);
        setListAdapter(adapter);

    }


    public class NoteAdapter extends ArrayAdapter<Note>{
        public NoteAdapter(ArrayList<Note> notes){
            super(getApplicationContext(),0,notes);
        }

        @Override
        public View getView(int position, View convertView,ViewGroup container){

            if(convertView == null){
            convertView = getLayoutInflater().inflate(R.layout.list_item_layout,null);}

            TextView contentText =(TextView) convertView.findViewById(R.id.content_name);
            TextView titleText = (TextView)convertView.findViewById(R.id.title_name);
            String content = getItem(position).getContent();
            contentText.setText(content);
            String title = getItem(position).getTitle();
            titleText.setText(title);
            return convertView;

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.note_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
            case R.id.action_settings:
                return true;
            case R.id.refresh:
                refreshPosts();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    public void refreshPosts(){
        setProgressBarIndeterminateVisibility(true);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.findInBackground(new FindCallback<ParseObject>(){

            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if(e != null){
                   Toast.makeText(getApplicationContext(), "Error Loading", Toast.LENGTH_SHORT)
                           .show();
                }
                else{
                         setProgressBarIndeterminateVisibility(false);
                         posts.clear();
                     for(ParseObject post : parseObjects){

                         Note note = new Note(post.getObjectId(),post.getString("title"),post.getString("content"));
                         posts.add(note);
                     }
                    ((NoteAdapter) getListAdapter()).notifyDataSetChanged();
                }
            }
        });
    }
}
