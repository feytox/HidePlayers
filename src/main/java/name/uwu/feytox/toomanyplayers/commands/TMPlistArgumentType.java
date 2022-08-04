package name.uwu.feytox.toomanyplayers.commands;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import name.uwu.feytox.toomanyplayers.TMPConfig;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

public class TMPlistArgumentType implements ArgumentType<String>, Serializable {
    final TMPlistTypes listType;

    protected TMPlistArgumentType(TMPlistTypes listType) {
        this.listType = listType;
    }

    public static TMPlistArgumentType whitelist() {
        return new TMPlistArgumentType(TMPlistTypes.WHITELIST);
    }

    public static TMPlistArgumentType blocklist() {
        return new TMPlistArgumentType(TMPlistTypes.BLOCKLIST);
    }

    public static TMPlistArgumentType hideskinlist() {
        return new TMPlistArgumentType(TMPlistTypes.HIDESKINLIST);
    }

    public static <S> String getTMPlistPlayer(CommandContext<S> context, String name) {
        return context.getArgument(name, String.class);
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
    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        switch (this.listType) {
            case WHITELIST -> TMPConfig.whitelist.forEach(builder::suggest);
            case BLOCKLIST -> TMPConfig.blocklist.forEach(builder::suggest);
            case HIDESKINLIST -> TMPConfig.hideskinlist.forEach(builder::suggest);
        }
        return builder.buildFuture();
    }
}

