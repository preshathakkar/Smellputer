/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smell.component;

import com.smell.component.controls.DeleteSmell;
import com.smell.component.controls.EditSmell;
import com.smell.component.controls.InsertSmell;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuBarBuilder;
import javafx.scene.control.MenuBuilder;
import javafx.scene.control.MenuItemBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCombination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.smell.component.controls.MenuControls;


/**
 *
 * @author Presha Thakkar
 */
public class Menu {
      
public static MenuBar createMenus() {

MenuBar menuBar = MenuBarBuilder.create()
.menus(
MenuBuilder.create()
.text("File")
.items(
MenuItemBuilder.create()
.text("New")
.graphic((new ImageView(
new Image(Menu.class.getResourceAsStream("icons/new.png"))))
)
.accelerator(KeyCombination.keyCombination("Ctrl+N"))
.onAction(new EventHandler<ActionEvent>() {
@Override public void handle(ActionEvent e) {
    MenuControls.open();
}
})
.build(),
MenuItemBuilder.create()
.text("Exit")
.onAction(new EventHandler<ActionEvent>() {
@Override public void handle(ActionEvent e) {
       System.exit(0);
}
})
.build()
)
.build(),
MenuBuilder.create()
.text("Smell")
.items(
MenuItemBuilder.create()
.text("Add Smell")
.graphic((new ImageView(
new Image(Menu.class.getResourceAsStream("icons/open.png"))))
)
.onAction(new EventHandler<ActionEvent>() {
@Override public void handle(ActionEvent e) {
    InsertSmell.addSmell();
}
})
.build(),
MenuItemBuilder.create()
.text("Edit Smell")
.graphic((new ImageView(
new Image(Menu.class.getResourceAsStream("icons/saveIcon.png"))))
)
.onAction(new EventHandler<ActionEvent>() {
@Override public void handle(ActionEvent e) {
    EditSmell.editSmell();
}
})
.build(),
MenuItemBuilder.create()
.text("Delete Smell")
.graphic((new ImageView(
new Image(Menu.class.getResourceAsStream("icons/delete.png"))))
)
.onAction(new EventHandler<ActionEvent>() {
@Override public void handle(ActionEvent e) {
    DeleteSmell.deleteSmell();
}
})
.build()
)
.build(),
MenuBuilder.create()
.text("Help")
.items(
MenuItemBuilder.create()
.text("About Us")
.graphic((new ImageView(
new Image(Menu.class.getResourceAsStream("icons/help.png"))))
)
.onAction(new EventHandler<ActionEvent>() {
@Override public void handle(ActionEvent e) {
    MenuControls.aboutUs();
}
})
.build()
)
.build()
)
.build();
return menuBar;
}
}
