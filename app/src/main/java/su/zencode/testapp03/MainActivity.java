package su.zencode.testapp03;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.CardView;
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
        CardView cardView = (CardView) LayoutInflater.from(this)
                .inflate(R.layout.container_card_view,mRootView,false);
        mContainerView = cardView.findViewById(R.id.container_root_view);
        mRootView.addView(cardView);

        mPictures = new HashMap<>();
        }

    @Override
    public void showTextBlock(TextBlock textBlock) {
        TextView textView = getTextViewInstance(textBlock);
        attachViewsToRoot(mContainerView, textView);
    }

    @Override
    public void showSelector(Selector selector) {
        Spinner spinner = getSpinnerInstance(selector);
        spinner.setSelection(selector.getSelectedId());
        attachViewsToRoot(mContainerView, spinner);
    }

    @Override
    public void showPicture(Picture picture) {
        ImageView imageView = getImageViewInstance(picture);
        mPictures.put(picture.getId(), imageView);
        attachViewsToRoot(mContainerView, imageView);
    }

    private void attachViewsToRoot(View cardView, View textView) {
        ((ViewGroup) cardView).addView(textView);
    }

    @NonNull
    private TextView getTextViewInstance(TextBlock textBlock) {
        TextView textView = (TextView) LayoutInflater.from(this)
                .inflate(R.layout.item_text_block,mContainerView,false);
        textView.setText(textBlock.getText());
        return textView;
    }

    private Spinner getSpinnerInstance(Selector selector) {
        Spinner spinner = (Spinner) LayoutInflater.from(this)
                .inflate(R.layout.item_spinner,mContainerView,false);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        selector.getVariants()
                );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return spinner;
    }

    private ImageView getImageViewInstance(Picture picture) {
        ImageView imageView = (ImageView) LayoutInflater.from(this)
                .inflate(R.layout.item_image,mContainerView,false);
        imageView.setContentDescription(picture.getDescription());
        if(picture.getBitmap() == null) {
            Drawable drawable = getResources().getDrawable(R.drawable.in_progress);
            imageView.setImageDrawable(drawable);
        }
        return imageView;
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
