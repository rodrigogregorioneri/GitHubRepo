package net.paulacr.githubrepo.data;

public class Repository {

    private static Repository instance;
    private Repositories repositories;
    private long currentPage = 1;

    public static Repository getInstance() {
        if(instance == null) {
          instance = new Repository();
        }
        return instance;
    }

    private Repository(){

    }

    public Repositories getRepositories() {
        return repositories;
    }

    public void setRepositories(Repositories repositories) {
        this.repositories = repositories;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }
}
