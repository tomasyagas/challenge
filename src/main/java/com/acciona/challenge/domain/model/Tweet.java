package com.acciona.challenge.domain.model;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Builder
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tweets")
public class Tweet {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @Column(nullable = false, columnDefinition = "VARCHAR(250)")
    private String user;
    @Column(nullable = false, columnDefinition = "VARCHAR(1500)")
    private String text;
    @Column(columnDefinition = "VARCHAR(100)")
    private String location;
    private boolean validated;
}
