package su.zencode.testapp03;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class PrnkTestAppPresenter extends MvpPresenter<PrnkTestAppView> {
    public PrnkTestAppPresenter() {
        getViewState().showTextBlock();
        new PrnkFetchr(this).fetchData();
    }

    public void setupTextBlock(String text){
        getViewState().showTextBlock();
        getViewState().setTextBlock(text);
    }
}
