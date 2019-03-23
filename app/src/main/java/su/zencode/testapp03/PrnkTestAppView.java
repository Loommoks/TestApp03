package su.zencode.testapp03;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

public interface PrnkTestAppView extends MvpView {
    void showTextBlock();
    void hideTextBlock();
    void setTextBlock(String text);

    void showPicture();
    void hidePicture();
    void setPicture(String url, String description);

    void showSelector();
    void hideSelector();
    void setSelector(ArrayList<String> listItems);

}
