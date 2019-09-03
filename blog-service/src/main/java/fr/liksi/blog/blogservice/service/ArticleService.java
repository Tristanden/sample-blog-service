package fr.liksi.blog.blogservice.service;

import fr.liksi.blog.blogservice.domain.Account;
import fr.liksi.blog.blogservice.domain.Article;
import fr.liksi.blog.blogservice.exception.ObjectNotFound;
import fr.liksi.blog.blogservice.repository.ArticleRepository;
import fr.liksi.blog.blogservice.service.connector.IAMConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    private IAMConnector iamConnector;

    @Autowired
    public ArticleService(ArticleRepository articleRepository,IAMConnector iamConnector) {
        this.articleRepository = articleRepository;
        this.iamConnector = iamConnector;
    }

    public Article createArticle(Article article, String email) {
        Account account = iamConnector.getAccount(email).orElseThrow(() -> new ObjectNotFound("No account found for email " + email));
        article.setAccount(account);
        articleRepository.addArticle(article);
        return article;
    }

    public Optional<Article> getArticle(String articleId) {
        return articleRepository.getArticle(articleId);
    }
}
