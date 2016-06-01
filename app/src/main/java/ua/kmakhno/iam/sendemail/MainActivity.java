package ua.kmakhno.iam.sendemail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnJM;
    private Button btnWithIntent;

    private EditText email;
    private EditText subject;
    private EditText txtPost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        email = (EditText)findViewById(R.id.email);
        subject = (EditText)findViewById(R.id.post);
        txtPost = (EditText)findViewById(R.id.editTextPost);

        btnJM = (Button)findViewById(R.id.jmBtn);
        btnWithIntent = (Button)findViewById(R.id.button2);

        btnJM.setOnClickListener(this);
        btnWithIntent.setOnClickListener(this);
    }

    private void send(){
        String mEmail = email.getText().toString().trim();
        String mSubject = subject.getText().toString().trim();
        String mTxtPost = txtPost.getText().toString().trim();

        SendEmail sendEmail = new SendEmail(this, mEmail, mSubject, mTxtPost);
        sendEmail.execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jmBtn:
                send();
                break;
            case R.id.button2:
                sendDefault();
                break;
        }
    }

    private void sendDefault(){
        String mEmail = email.getText().toString().trim();
        String mSubject = subject.getText().toString().trim();
        String mTxtPost = txtPost.getText().toString().trim();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{mEmail});
        intent.putExtra(Intent.EXTRA_SUBJECT, mSubject);
        intent.putExtra(Intent.EXTRA_TEXT, mTxtPost);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Выбирите Email клиента:"));
    }
}
