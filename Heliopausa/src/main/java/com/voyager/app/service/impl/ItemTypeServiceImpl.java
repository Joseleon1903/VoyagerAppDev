package com.voyager.app.service.impl;

import com.voyager.app.entity.ItemType;
import com.voyager.app.repository.ItemTypeRepository;
import com.voyager.app.service.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


/**
 * Created by jose on 9/1/2019.
 */
@Service
public class ItemTypeServiceImpl implements ItemTypeService{

    @Autowired
    private ItemTypeRepository itemTypeRepository;


    @Override
    public Iterable<ItemType> findAllItemsType() {
        return null;
    }

    @Override
    public Optional<ItemType> findByName(String name) {
        return null;
    }
}
