package com.projects.simplescript.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

// import com.projects.simplescript.dao.MenuItemRepository;
import com.projects.simplescript.model.MenuItem;
import com.projects.simplescript.model.Storage;
import com.projects.simplescript.services.MenuService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService{

    // private final MenuItemRepository menuRepo;

    @Override
    public List<MenuItem> getAllMenu() {
                        Storage.checkConfig();
        MenuItem m1 = new MenuItem("2", null, "Kriteria", "invoice_16x16.png", null);
        MenuItem m2 = new MenuItem("4", null, "Anggota", "customers_16x16.png", null);
        MenuItem m3 = new MenuItem("5", null, "Perhitungan", "category_16x16.png", null);
        MenuItem m4 = new MenuItem("6", "5", "Alternatif", "invoice_16x16.png", null);
        MenuItem m5 = new MenuItem("7", "5", "Perbandingan", "invoice_16x16.png", null);
        MenuItem m6 = new MenuItem("8", "5", "Hasil Perhitungan", "invoice_16x16.png", null);
        MenuItem m7 = new MenuItem("9", null, "Report", "category_16x16.png", null);
        
        // Adding the created MenuItems to a list if needed
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(m1);
        menuItems.add(m2);
        menuItems.add(m3);
        menuItems.add(m4);
        menuItems.add(m5);
        menuItems.add(m6);
        menuItems.add(m7);
        return menuItems;
    }

}
