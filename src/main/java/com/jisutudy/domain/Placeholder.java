package com.jisutudy.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Placeholder {

    @Id @GeneratedValue
    @Column(name = "plhd_id")
    private Long id;
    private String enText;
    private String koText;

}
