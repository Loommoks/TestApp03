package su.zencode.testapp03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

public class MainActivity extends MvpAppCompatActivity implements PrnkTestAppView{
    @InjectPresenter
    PrnkTestAppPresenter mPrnkTestAppPresenter;

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
                //new PrnkFetchr().fetchData(mMessageTextView);
                //mMessageTextView.setText(response);
            }
        });
    }

    @Override
    public void showTextBlock() {
        mMessageTextView.setText("Now visible :))");
    }

    @Override
    public void hideTextBlock() {

    }

    @Override
    public void setTextBlock(String text) {
        mMessageTextView.setText(text);
    }

    @Override
    public void showPicture() {

    }

    @Override
    public void hidePicture() {

    }

    @Override
    public void setPicture(String url, String description) {

    }

    @Override
    public void showSelector() {

    }

    @Override
    public void hideSelector() {

    }

    @Override
    public void setSelector(ArrayList<String> listItems) {

    }
}
