package com.acciona.challenge.domain.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hashtags")
public class Hashtag {

    @Id
    private String name;
    @Column(nullable = false)
    private Integer counter;
}
