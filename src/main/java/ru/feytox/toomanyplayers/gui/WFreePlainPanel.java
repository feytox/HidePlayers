package ru.feytox.toomanyplayers.gui;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.WWidget;

public class WFreePlainPanel extends WPlainPanel {

    @Override
    public void add(WWidget w, int x, int y) {
        this.children.add(w);
        w.setParent(this);
        w.setLocation(this.insets.left() + x, this.insets.top() + y);
        if (w.canResize()) {
            w.setSize(18, 18);
        }
    }

    @Override
    public void add(WWidget w, int x, int y, int width, int height) {
        this.children.add(w);
        w.setParent(this);
        w.setLocation(this.insets.left() + x, this.insets.top() + y);
        if (w.canResize()) {
            w.setSize(width, height);
        }
    }

    @Override
    public void validate(GuiDescription c) {
        this.host = c;
        for (WWidget child : this.children) {
            child.validate(c);
        }
    }
}
