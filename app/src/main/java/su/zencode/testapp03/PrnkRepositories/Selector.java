package su.zencode.testapp03.PrnkRepositories;

import java.util.ArrayList;

public class Selector {
    private String mId;
    private int mSelectedId;
    private ArrayList<String> variants;

    public Selector(String id, int selectedId, ArrayList<String> variants) {
        mId = id;
        mSelectedId = selectedId;
        this.variants = variants;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public int getSelectedId() {
        return mSelectedId;
    }

    public void setSelectedId(int selectedId) {
        mSelectedId = selectedId;
    }

    public ArrayList<String> getVariants() {
        return variants;
    }

    public void setVariants(ArrayList<String> variants) {
        this.variants = variants;
    }
}
