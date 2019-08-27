package com.voyager.app.repository;

import com.voyager.app.entity.EmailTemplate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EmailTemplateRepository extends CrudRepository<EmailTemplate, Long> {

    @Query("select e from EmailTemplate e where e.code like ?1")
    EmailTemplate findByCode(String code);

}