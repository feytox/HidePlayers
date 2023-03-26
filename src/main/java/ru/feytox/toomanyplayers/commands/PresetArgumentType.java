package ru.feytox.toomanyplayers.commands;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import ru.feytox.toomanyplayers.Presets;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

public class PresetArgumentType implements ArgumentType<String>, Serializable {

    private final boolean with_defaults;

    protected PresetArgumentType(boolean with_defaults) {
        this.with_defaults = with_defaults;
    }

    public static PresetArgumentType preset() {
        return new PresetArgumentType(true);
    }

    public static PresetArgumentType presetWithoutDefaults() {
        return new PresetArgumentType(false);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        Presets.getPresets(this.with_defaults).forEach(builder::suggest);
        return builder.buildFuture();
    }

    @Nullable
    @Override
    public String parse(StringReader reader) {
        int argBeginning = reader.getCursor();
        if(!reader.canRead()){
            reader.skip();
        }
        while(reader.canRead() && reader.peek() != ' ') {
            reader.skip();
        }
        return reader.getString().substring(argBeginning, reader.getCursor());
    }
}
