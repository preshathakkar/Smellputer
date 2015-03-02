package com.smell;

import javafx.scene.Parent;
import javafx.scene.image.Image;

/**
 * @author Presha Thakkar
 */
public class Tool {
    private final String name;
    private final Image icon;
    private final Parent content;

    public Tool(String name, Parent content, Image icon) {
        this.name = name;
        this.content = content;
        this.icon = icon;
    }

    public Parent getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public Image getIcon() {
        return icon;
    }
}
