package ru.feytox.toomanyplayers.gui;

import io.github.cottonmc.cotton.gui.widget.WTextField;
import net.minecraft.text.Text;

public class FTextField extends WTextField {
    public FTextField(Text suggestion) {
        this.setSuggestion(suggestion);
    }
}
