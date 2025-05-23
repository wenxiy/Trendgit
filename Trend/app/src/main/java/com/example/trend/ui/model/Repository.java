package com.example.trend.ui.model;

import java.util.Comparator;
import java.util.List;

public class Repository {
    /**
     * author : xingshaocheng
     * name : architect-awesome
     * avatar : https://github.com/xingshaocheng.png
     * url : https://github.com/xingshaocheng/architect-awesome
     * description : 后端架构师技术图谱
     * language : Go
     * languageColor : #3572A5
     * stars : 7333
     * forks : 1546
     * currentPeriodStars : 1528
     * builtBy : [{"href":"https://github.com/viatsko","avatar":"https://avatars0.githubusercontent.com/u/376065","username":"viatsko"}]
     */

    private String author;
    private String name;
    private String avatar;
    private String url;
    private String description;
    private String language;
    private String languageColor;
    private int stars;
    private int forks;
    private int currentPeriodStars;
    private List<Repositories.BuiltByBean> builtBy;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguageColor() {
        return languageColor;
    }

    public void setLanguageColor(String languageColor) {
        this.languageColor = languageColor;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public int getCurrentPeriodStars() {
        return currentPeriodStars;
    }

    public void setCurrentPeriodStars(int currentPeriodStars) {
        this.currentPeriodStars = currentPeriodStars;
    }

    public List<Repositories.BuiltByBean> getBuiltBy() {
        return builtBy;
    }

    public void setBuiltBy(List<Repositories.BuiltByBean> builtBy) {
        this.builtBy = builtBy;
    }

    public static Comparator<Repository> starComparator = new Comparator<Repository>() {
        @Override
        public int compare(Repository o1, Repository o2) {
            int star1 = o1.getStars();
            int star2 = o2.getStars();
            return star2-star1;
        }
    };
    public static Comparator<Repository> namesComparator = new Comparator<Repository>() {
        @Override
        public int compare(Repository o1, Repository o2) {
            String name1 = o1.getName();
            String name2 = o2.getName();

            return name2.length() - name1.length();

        }
    };

    public static class BuiltByBean {
        /**
         * href : https://github.com/viatsko
         * avatar : https://avatars0.githubusercontent.com/u/376065
         * username : viatsko
         */

        private String href;
        private String avatar;
        private String username;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
