package adminCommands.commands;

import adminCommands.utils.Messages;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;

public class FreezeCommand extends Command {
    public FreezeCommand() {
        super("freeze", "заморозить игрока", "Используйте: §7/freeze <тег игрока>");
        getCommandParameters().put("default", new CommandParameter[] {
           new CommandParameter("player", CommandParamType.TARGET, true)
        });
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 1) {
                if(player.hasPermission("adminCommands.freeze")) {
                    Player target = Server.getInstance().getPlayer(args[0]);
                    if(target != null) {
                        if(!target.getName().equals(player.getName())) {
                            target.setImmobile();
                            player.sendMessage("Вы успешно заморозили игрока§c " + target.getName());
                            target.sendTitle("§l§cSTOP", "§f§lПроверь чат :3");
                            target.sendMessage("Вас заморозил игрок §c" + player.getName());
                        } else player.sendMessage("§7Вы не можете заморозить себя!");
                    } else player.sendMessage("Игрока §a" + args[0] + "§f в данный момент нет на сервере!");
                } else player.sendMessage(Messages.noPermission);
            } else player.sendMessage(this.usageMessage);
        } else Server.getInstance().getLogger().warning(Messages.consoleMessageAboutUsingCommand);
        return false;
    }
}
