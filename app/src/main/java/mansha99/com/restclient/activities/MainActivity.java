package mansha99.com.restclient.activities;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import mansha99.com.androidrestclient.R;
import mansha99.com.restclient.adapters.ContactAdapter;
import mansha99.com.restclient.calls.CallFetch;
import mansha99.com.restclient.dto.ContactListDTO;
import mansha99.com.restclient.utils.AppHelper;
public class MainActivity extends AppCompatActivity {
    Activity self;
    private ListView listView;
    ContactListDTO dto;
    ImageView button_add_new;
    ImageView button_next;
    ImageView button_previous;

    Integer page=0;
    Integer size=10;
    String sort="id";
    String dir="desc";
    ContactAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        self=this;
        listView=(ListView)findViewById(R.id.listView);
        button_add_new=(ImageView)findViewById(R.id.button_add_new);
        button_next=(ImageView)findViewById(R.id.button_next);
        button_previous=(ImageView)findViewById(R.id.button_previous);
        button_next.setOnClickListener(new ShowNextListener());
        button_previous.setOnClickListener(new ShowPreviousListener());
        button_add_new.setOnClickListener(new AddNewListener());
        new FetchContacTask().execute();
    }
    private class ShowNextListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            page++;
            new FetchContacTask().execute();
        }
    }
    private class AddNewListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            startActivity(new Intent(self,AddNew.class));
        }
    }
    private class ShowPreviousListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            page--;
            new FetchContacTask().execute();
        }
    }


    private class FetchContacTask extends AsyncTask<Void,Void,ContactListDTO>{
        @Override
        protected ContactListDTO doInBackground(Void... voids) {
            try{
                return CallFetch.get(page,size,sort,dir);
            }catch(Exception ex){
                AppHelper.LogStack(ex);
                return null;
            }
        }
        @Override
        public void onPostExecute(ContactListDTO _dto) {
            if(_dto!=null){
                dto=_dto;
                adapter = new ContactAdapter(getApplication(),android.R.layout.simple_list_item_1,dto.getContent());
                listView.setAdapter(adapter);
                updateUi();
            }
        }

    }
    void updateUi(){
        if(dto.getFirst().equals("true")){
            button_previous.setAlpha(.5f);
            button_previous.setClickable(false);
        }else{
            button_previous.setAlpha(1.0f);
            button_previous.setClickable(true);
        }
        if(dto.getLast().equals("true")){
            button_next.setAlpha(.5f);
            button_next.setClickable(false);
        }else{
            button_next.setAlpha(1.0f);
            button_next.setClickable(true);
        }


    }
}
