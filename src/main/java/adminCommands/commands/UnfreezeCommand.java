package adminCommands.commands;

import adminCommands.utils.Messages;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;

public class UnfreezeCommand extends Command {
    public UnfreezeCommand() {
        super("unfreeze", "разморозить игрока", "Используйте:§7 /unfreeze <тег игрока>");
        getCommandParameters().put("default", new CommandParameter[] {
                new CommandParameter("player", CommandParamType.TARGET, true)
        });
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 1) {
                if(player.hasPermission("adminCommands.unfreeze")) {
                    Player target = Server.getInstance().getPlayer(args[0]);
                    if(target != null) {
                        if(!target.getName().equals(player.getName())) {
                            target.setImmobile(false);
                            target.sendTitle("§l§aSTART", "§lВы были разморожены :3");
                            player.sendMessage("Вы успешно разморозили игрока§c " + target.getName());
                            target.sendMessage("Вас разморозил игрок §c" + player.getName());
                        } else player.sendMessage("§7Себя размораживать запрещено!");
                    } else player.sendMessage("Игрока §a" + args[0] + "§f в данный момент нет на сервере!");
                } else player.sendMessage(Messages.noPermission);
            } else player.sendMessage(this.usageMessage);
        } else Server.getInstance().getLogger().warning(Messages.consoleMessageAboutUsingCommand);
        return false;
    }
}
