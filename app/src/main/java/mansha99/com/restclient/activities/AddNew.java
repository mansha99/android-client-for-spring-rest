package mansha99.com.restclient.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import mansha99.com.androidrestclient.R;
import mansha99.com.restclient.adapters.ContactAdapter;
import mansha99.com.restclient.calls.CallFetch;
import mansha99.com.restclient.calls.CallSave;
import mansha99.com.restclient.dto.SaveContactDTO;
import mansha99.com.restclient.models.Contact;
import mansha99.com.restclient.utils.AppHelper;

public class AddNew extends AppCompatActivity {
    ImageView button_home;
    EditText textName;
    EditText textEmail;
    Button buttonSave;
    Contact model=new Contact();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        button_home = (ImageView) findViewById(R.id.button_home);
        textEmail = (EditText) findViewById(R.id.textEmail);
        textName = (EditText) findViewById(R.id.textName);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new SaveListener());
        button_home.setOnClickListener(new HomeListener());
    }
    private class SaveContacTask extends AsyncTask<Void,Void,SaveContactDTO> {
        Contact mModel;
        SaveContacTask(Contact model){
            this.mModel=model;
        }
        @Override
        protected SaveContactDTO doInBackground(Void... voids) {
            try{
                return CallSave.get(mModel);
            }catch(Exception ex){
                AppHelper.LogStack(ex);
                return null;
            }
        }
        @Override
        public void onPostExecute(SaveContactDTO _dto) {
            if(_dto!=null){
               model=_dto.getModel();
                textName.setError(_dto.getErrors().get("name"));
                textEmail.setError(_dto.getErrors().get("email"));
                if(_dto.getStatus().equals("success")){
                    AppHelper.showDialog(getApplication(),"Record Saved");
                }

            }
        }

    }
    class SaveListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            model.setEmail(textEmail.getText().toString());
            model.setName(textName.getText().toString());
            new SaveContacTask(model).execute();
        }
    }
        class HomeListener implements View.OnClickListener{
            @Override
        public void onClick(View view) {
            startActivity(new Intent(AddNew.this,MainActivity.class));
        }
    }

}
