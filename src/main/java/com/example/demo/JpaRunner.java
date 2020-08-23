package com.example.demo;

import javafx.geometry.Pos;
import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Post post = new Post();
        post.setTitle("spring data jpa 언제보냐");

        Comment comment = new Comment();
        comment.setComment("빨리 보고 싶어요");
        post.addCommnet(comment);

        Comment comment1 = new Comment();
        comment1.setComment("곧 보여줄게요");
        post.addCommnet(comment1);


        Session session = entityManager.unwrap(Session.class);
        session.save(post);

        //Post post = session.get(Post.class , 1L);
        System.out.println("===============================");
        System.out.println(post.getTitle());


        /*Account account = new Account();
        account.setUsername("Keesun2");
        account.setPassword("jpa");

        Study study = new Study();
        study.setName("Spring Data jpa");

        account.addStudy(study);


        Session session = entityManager.unwrap(Session.class);
        session.save(account);
        session.save(study);*/
    }
}
