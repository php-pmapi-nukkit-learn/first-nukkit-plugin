package adminCommands;

import adminCommands.commands.FireCommand;
import adminCommands.commands.FreezeCommand;
import adminCommands.commands.ShockCommand;
import adminCommands.commands.UnfreezeCommand;
import cn.nukkit.command.Command;
import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase {
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
        for(Command command : commands)
            getServer().getCommandMap().register("adminCommands", command);
    }
}
