package com.voyager.app.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class CorrelationComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date creationDate;
    private String content;
    @OneToOne
    private User referenceUser;

    public CorrelationComments(){}

    public CorrelationComments(long id){
        this.id =id;
    }


}
