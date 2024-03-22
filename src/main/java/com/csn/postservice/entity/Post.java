package com.csn.postservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String textContent;
//    @ManyToOne
//    private User user;
//    @OneToMany(mappedBy = "post")
//    private List<Comment> comments;
}
