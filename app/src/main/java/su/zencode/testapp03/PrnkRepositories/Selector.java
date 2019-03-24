package su.zencode.testapp03.PrnkRepositories;

public class Selector {
    private String mId;
    private int mSelectedId;
    private String[] variants;

    public Selector(String id, int selectedId, String[] variants) {
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

    public String[] getVariants() {
        return variants;
    }

    public void setVariants(String[] variants) {
        this.variants = variants;
    }
}
