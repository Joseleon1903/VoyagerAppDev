package com.voyager.app.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class CorrelationLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String linkAdders;

    public CorrelationLink(){}

    public CorrelationLink(long id){
        this.id= id;
    }


}
