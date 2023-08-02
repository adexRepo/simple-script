package com.projects.simplescript.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projects.simplescript.dao.MenuItemRepository;
import com.projects.simplescript.model.MenuItem;
import com.projects.simplescript.services.MenuService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService{

    private final MenuItemRepository menuRepo;

    @Override
    public List<MenuItem> getAllMenu() {
        List<MenuItem> items = menuRepo.findAll();
        return items;
    }
}
