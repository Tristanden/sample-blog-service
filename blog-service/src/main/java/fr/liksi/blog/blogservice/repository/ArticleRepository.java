package fr.liksi.blog.blogservice.repository;

import fr.liksi.blog.blogservice.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ArticleRepository {

    private Map<String, Article> articleMap = new HashMap<>();

    public void addArticle(Article article) {
        article.setId(UUID.randomUUID().toString());
        articleMap.put(article.getId(), article);
    }

    public Optional<Article> getArticle(String id) {
        return Optional.ofNullable(articleMap.get(id));
    }


}
