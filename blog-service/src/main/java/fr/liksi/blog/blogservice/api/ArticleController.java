package fr.liksi.blog.blogservice.api;

import fr.liksi.blog.blogservice.api.bean.ApiArticle;
import fr.liksi.blog.blogservice.domain.Article;
import fr.liksi.blog.blogservice.exception.ObjectNotFound;
import fr.liksi.blog.blogservice.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
@RequestMapping("/api/v1/article")
public class ArticleController {

    private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @PostMapping
    @ApiOperation(value = "Create an article")
    public ResponseEntity<ApiArticle> createArticle(
            @ApiParam(name = "article", required = true) @Valid @RequestBody ApiArticle apiArticle) {
        LOG.info("Create article with email {}", apiArticle.getEmail());
        Article article = new Article();
        article.setTitle(apiArticle.getTitle());
        article.setContent(apiArticle.getContent());

        Article created = articleService.createArticle(article, apiArticle.getEmail());
        apiArticle.setId(created.getId());
        ControllerLinkBuilder controllerLinkBuilder = linkTo(methodOn(getClass()).getArticle(apiArticle.getId()));
        return ResponseEntity.created(controllerLinkBuilder.toUri()).body(apiArticle);
    }


    @GetMapping(path = "/{articleId}")
    @ApiOperation(value = "Get an article")
    public ResponseEntity<ApiArticle> getArticle(
            @ApiParam(name = "articleId", required = true) @PathVariable String articleId) {
        LOG.info("Get article with id {}", articleId);
        Article article = articleService.getArticle(articleId).orElseThrow(() -> new ObjectNotFound("Article " + articleId + " does not exist"));
        ApiArticle apiArticle = new ApiArticle();
        apiArticle.setId(article.getId());
        apiArticle.setContent(article.getContent());
        apiArticle.setTitle(article.getTitle());
        apiArticle.setEmail(article.getAccount().getEmail());
        return ResponseEntity.ok(apiArticle);
    }

}
