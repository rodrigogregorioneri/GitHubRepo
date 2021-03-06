package net.paulacr.githubrepo.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Repositories {

    @SerializedName("total_count")
    private long totalCount;
    @SerializedName("incomplete_results")
    boolean isIncompleteResults;
    @SerializedName("items")
    private List<Item> items;


    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncompleteResults() {
        return isIncompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        isIncompleteResults = incompleteResults;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
