package su.zencode.testapp03;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import su.zencode.testapp03.PrnkRepositories.Picture;
import su.zencode.testapp03.PrnkRepositories.Selector;
import su.zencode.testapp03.PrnkRepositories.TextBlock;

public class MainActivity extends MvpAppCompatActivity implements PrnkTestAppView{
    @InjectPresenter
    PrnkTestAppPresenter mPrnkTestAppPresenter;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        }

    @Override
    public void showTextBlock(TextBlock textBlock) {
        ViewGroup rootView = findViewById(R.id.activity_main);
        View cardView = getCardViewInstance(rootView);
        TextView textView = getTextViewInstance(textBlock);
        attachViewsToRoot(rootView, cardView, textView);
    }

    @Override
    public void showSelector(Selector selector) {
        ViewGroup rootView = findViewById(R.id.activity_main);
        View cardView = getCardViewInstance(rootView);
        //todo update spinner generation
        TextView textView = new TextView(this);
        textView.setText(selector.getId());
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        //todo </update>
        attachViewsToRoot(rootView, cardView, textView);
    }

    @Override
    public void showPicture(Picture picture) {
        ViewGroup rootView = findViewById(R.id.activity_main);
        View cardView = getCardViewInstance(rootView);
        ImageView imageView = getImageViewInstance(picture);
        attachViewsToRoot(rootView, cardView, imageView);
    }

    private void attachViewsToRoot(ViewGroup rootView, View cardView, View textView) {
        ((ViewGroup) cardView).addView(textView);
        rootView.addView(cardView);
    }

    @NonNull
    private TextView getTextViewInstance(TextBlock textBlock) {
        TextView textView = new TextView(this);
        textView.setText(textBlock.getText());
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        return textView;
    }

    private Spinner getTextViewInstance(Selector selector) {
        Spinner spinner = new Spinner(this);
        //todo update spinner
        return spinner;
    }

    private ImageView getImageViewInstance(Picture picture) {
        ImageView imageView = new ImageView(this);
        imageView.setContentDescription(picture.getDescription());
        Drawable drawable = getResources().getDrawable(R.drawable.in_progress);
        imageView.setImageDrawable(drawable);
        return imageView;
    }

    private View getCardViewInstance(ViewGroup rootView) {
        return LayoutInflater.from(this)
                    .inflate(R.layout.item_card_view,rootView,false);
    }

    @Override
    public void hideTextBlock() {

    }

    @Override
    public void hidePicture() {

    }

    @Override
    public void updatePictureDrawable(String id, Drawable drawable) {

    }

    @Override
    public void hideSelector() {

    }
}
