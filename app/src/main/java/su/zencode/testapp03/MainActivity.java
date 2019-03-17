package su.zencode.testapp03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView mMessageTextView;
    private Button mStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMessageTextView = findViewById(R.id.message_text_view);
        mStartButton = findViewById(R.id.make_call_button);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String response = ;
                new PrnkFetchr().fetchData(mMessageTextView);
                //mMessageTextView.setText(response);
            }
        });
    }
}
