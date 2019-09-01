package com.voyager.app.service;

import com.voyager.app.entity.ItemType;

import java.util.Optional;

/**
 * Created by jose on 9/1/2019.
 */
public interface ItemTypeService {

    Iterable<ItemType> findAllItemsType();

    Optional<ItemType> findByName(String name);
}
