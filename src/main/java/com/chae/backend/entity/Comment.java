package com.chae.backend.entity;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@Entity
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    //게시물 하나에 달라는 댓글
    @ManyToOne
    private Question question;

    //대댓글의 부모댓글
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Comment parent;

    //대댓글 (자식댓글; 그래서 댓글한개당 set으로 달리는거 )
    @ToString.Exclude
    @OneToMany(mappedBy = "parent")
    private Set<Comment> children = new LinkedHashSet<>();

    @ManyToOne
    private SiteUser author;

    private LocalDateTime modifyDate;

    @ManyToMany
    Set<SiteUser> voter;

    //메소드 

    public void addChild(Comment child){
        children.add(child);
        child.setParent(this);
    }

    public void removeChild(Anwer child){
        children.remove(child);
        child.setParent(null);
    }
}
