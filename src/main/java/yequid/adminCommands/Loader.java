package yequid.adminCommands;

import yequid.adminCommands.commands.FireCommand;
import yequid.adminCommands.commands.FreezeCommand;
import yequid.adminCommands.commands.ShockCommand;
import yequid.adminCommands.commands.UnfreezeCommand;
import cn.nukkit.command.Command;
import cn.nukkit.plugin.PluginBase;

public class Loader extends PluginBase {
    @Override
    public void onEnable() {
        registerCommands();
    }

    private void registerCommands() {
        Command[] commands = {
                new FreezeCommand(),
                new UnfreezeCommand(),
                new FireCommand(),
                new ShockCommand()
        };

        for (Command command : commands) {
            getServer().getCommandMap().register("adminCommands", command);
        }
    }
}
