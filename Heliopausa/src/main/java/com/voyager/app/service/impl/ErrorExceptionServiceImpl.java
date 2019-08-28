package com.voyager.app.service.impl;

import com.voyager.app.entity.ErrorException;
import com.voyager.app.repository.ErrorExceptionRepository;
import com.voyager.app.service.ErrorExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ErrorExceptionServiceImpl implements ErrorExceptionService {

    @Autowired
    private ErrorExceptionRepository errorExceptionRepository;

    @Override
    public ErrorException findByCode(long code) {
        return errorExceptionRepository.findByCode(code);
    }

    @Override
    public List<ErrorException> findAll() {
        return (List<ErrorException>) errorExceptionRepository.findAll();
    }

    @Override
    public List<ErrorException> findAllActive() {
        return errorExceptionRepository.findAllActive();
    }

    @Override
    public ErrorException save(ErrorException errorException) {
        return errorExceptionRepository.save(errorException);
    }

}
