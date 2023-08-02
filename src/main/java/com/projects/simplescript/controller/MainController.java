package com.projects.simplescript.controller;


import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.projects.simplescript.constant.DataStorage;
import com.projects.simplescript.model.MenuItem;
import com.projects.simplescript.services.MenuService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/Main.fxml")
@RequiredArgsConstructor
public class MainController implements Initializable {

    private final MenuService menuService;

    // private final FxWeaver fxWeaver;

    @FXML
    private MenuBar menuBar;

    @FXML
    private TreeView<String> tree;

    @FXML
    private ImageView img;

    @FXML
    private TabPane tab;

    DataStorage storage = DataStorage.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image(getClass().getResourceAsStream("/images/logo.png"));
        img.setImage(image);

        insertDataToTreeView(menuService.getAllMenu());
        setupTreeViewItemClickHandler();
    }

    private void insertDataToTreeView(List<MenuItem> menuItems) {
        /* ---------------------------- set singleton var --------------------------- */
        storage.setMenuItems(menuItems);

        Map<String, TreeItem<String>> treeItemMap = new HashMap<>();
        TreeItem<String> rootItem = new TreeItem<>("Menu"); // Create a root item with a dummy name

        for (MenuItem menuItem : menuItems) {
            ImageView imgView = new ImageView(
                    new Image(getClass().getResourceAsStream("/images/" + menuItem.getImage())));

            TreeItem<String> treeItem = new TreeItem<>(menuItem.getName(), imgView);
            treeItemMap.put(menuItem.getId(), treeItem);

            String parentId = menuItem.getParent();
            if (parentId != null && treeItemMap.containsKey(parentId)) {
                TreeItem<String> parentItem = treeItemMap.get(parentId);
                parentItem.getChildren().add(treeItem);
            } else {
                rootItem.getChildren().add(treeItem); // Add to the root item
            }
        }

        tree.setRoot(rootItem); // Set the root of the tree view

        // Expand the root tree item and its children
        rootItem.setExpanded(true);
        for (TreeItem<String> child : rootItem.getChildren()) {
            child.setExpanded(true);
        }
    }

    private void setupTreeViewItemClickHandler() {
        tree.setOnMouseClicked(event -> {
            TreeItem<String> selectedItem = tree.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                String itemName = selectedItem.getValue();
                // Open a new tab or perform any other desired action
                openNewTab(itemName);
            }
        });
    }

    private void openNewTab(String tabName) {
        TreeItem<String> selectedItem = tree.getSelectionModel().getSelectedItem();

        if (selectedItem != null && selectedItem.getChildren().isEmpty()) {
            // Check if a tab with the same name already exists
            Optional<Tab> existingTab = tab.getTabs().stream()
                    .filter(t -> t.getText().equals(tabName))
                    .findFirst();

            if (existingTab.isPresent()) {
                // If the tab already exists, select it
                tab.getSelectionModel().select(existingTab.get());
            } else {
                Tab newTab = new Tab(tabName);
                boolean isGrid = storage.getMenuItems().stream()
                        .anyMatch(val -> val.getName().equals(tabName) && val.getGridId() != null);
                // if (isGrid) {
                //     storage.getCache().put("tabName", tabName);
                //     Parent root = fxWeaver.loadView(FrameGridController.class);
                //     newTab.setContent(root);
                // } else {

                //     if(tabName.equals("Isi Evaluasi Kompetensi")){
                //         Parent root = fxWeaver.loadView(CompetencyBasicController.class);
                //         newTab.setContent(root);
                //     }else if (tabName.equals("Isi Sasaran Kerja")){
                //         Parent root = fxWeaver.loadView(WorkGoalsController.class);
                //         newTab.setContent(root);
                //     }else if (tabName.equals("Reports")){
                //         Parent root = fxWeaver.loadView(ReportController.class);
                //         newTab.setContent(root);
                //     }else if (tabName.equals("Atur Evaluasi")){
                //         Parent root = fxWeaver.loadView(EvaluationMgmtController.class);
                //         newTab.setContent(root);
                //     }
                //     else{
                //         Label root = new Label("Logout OK");
                //         newTab.setContent(root);
                //     }
                // }
                tab.getTabs().add(newTab);
                tab.getSelectionModel().select(newTab);
            }
        }
    }

}
