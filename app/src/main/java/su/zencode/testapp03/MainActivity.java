package su.zencode.testapp03;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.HashMap;

import su.zencode.testapp03.PrnkRepositories.Picture;
import su.zencode.testapp03.PrnkRepositories.Selector;
import su.zencode.testapp03.PrnkRepositories.TextBlock;

public class MainActivity extends MvpAppCompatActivity implements PrnkTestAppView{
    @InjectPresenter
    PrnkTestAppPresenter mPrnkTestAppPresenter;

    private HashMap<String, View> mPictures;
    private ViewGroup mRootView;
    private ViewGroup mContainerView;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRootView = findViewById(R.id.activity_main);
        CardView cardView = (CardView) getCardViewInstance(mRootView);
        mContainerView = cardView.findViewById(R.id.container_view);
        mRootView.addView(cardView);

        mPictures = new HashMap<>();
        }

    @Override
    public void showTextBlock(TextBlock textBlock) {
        //ViewGroup rootView = findViewById(R.id.activity_main);
        //View cardView = getCardViewInstance(rootView);
        TextView textView = getTextViewInstance(textBlock);
        attachViewsToRoot(mRootView, mContainerView, textView);
    }

    @Override
    public void showSelector(Selector selector) {
        //ViewGroup rootView = findViewById(R.id.activity_main);
        //View cardView = getCardViewInstance(rootView);
        //todo update spinner generation
        /**
        TextView textView = new TextView(this);
        textView.setText(selector.getId());
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);*/
        //todo </update>
        Spinner spinner = getSpinnerInstance(selector);
        spinner.setSelection(selector.getSelectedId());

        attachViewsToRoot(mRootView, mContainerView, spinner);
    }

    @Override
    public void showPicture(Picture picture) {
        //ViewGroup rootView = findViewById(R.id.activity_main);
        //View cardView = getCardViewInstance(rootView);
        ImageView imageView = getImageViewInstance(picture);
        mPictures.put(picture.getId(), imageView);
        attachViewsToRoot(mRootView, mContainerView, imageView);
    }

    private void attachViewsToRoot(ViewGroup rootView, View cardView, View textView) {
        ((ViewGroup) cardView).addView(textView);
        //rootView.addView(cardView);
    }

    @NonNull
    private TextView getTextViewInstance(TextBlock textBlock) {
        TextView textView = new TextView(this);
        textView.setText(textBlock.getText());
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        return textView;
    }

    private Spinner getSpinnerInstance(Selector selector) {
        Spinner spinner = new Spinner(this);
        //todo update spinner
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_spinner_item,
                        selector.getVariants()
                );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return spinner;
    }

    private ImageView getImageViewInstance(Picture picture) {
        ImageView imageView = new ImageView(this);
        imageView.setContentDescription(picture.getDescription());
        if(picture.getBitmap() == null) {
            Drawable drawable = getResources().getDrawable(R.drawable.in_progress);
            imageView.setImageDrawable(drawable);
        }
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
    public void updatePictureDrawable(String id, Bitmap bitmap) {
        ImageView imageView = (ImageView) mPictures.get(id);
        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
        imageView.setImageDrawable(drawable);
    }

    @Override
    public void hideSelector() {

    }
}
