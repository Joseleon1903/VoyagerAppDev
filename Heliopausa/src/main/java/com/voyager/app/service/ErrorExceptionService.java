package com.voyager.app.service;

import com.voyager.app.entity.ErrorException;
import java.util.List;

public interface ErrorExceptionService {

    ErrorException findByCode(long code);

    List<ErrorException> findAll();

    List<ErrorException> findAllActive();

    ErrorException save(ErrorException errorException);

}
