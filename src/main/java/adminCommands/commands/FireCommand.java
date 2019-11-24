package adminCommands.commands;

import adminCommands.utils.Messages;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;

public class FireCommand extends Command {
    public FireCommand() {
        super("fire", "Поджечь игрока", "Используйте:§7 /fire <тег игрока>");
        getCommandParameters().put("default", new CommandParameter[] {
                new CommandParameter("player", CommandParamType.TARGET, true)
        });
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 1) {
                if(player.hasPermission("adminCommands.fire")) {
                    Player target = Server.getInstance().getPlayer(args[0]);
                    if(target != null) {
                        if(!target.getName().equals(player.getName())) {
                            target.setOnFire(60);
                            target.sendTitle("§l§cOops...", "§l§fВас подожгли -_-");
                            target.sendMessage("Тебя поджег игрок §c" + player.getName());
                            player.sendMessage("Ты поджег игрока §c" + target.getName());
                        } else player.sendMessage("§7Себя поджигать запрещено, иначе будет больно!");
                    } else player.sendMessage("Игрока §a" + args[0] + "§f в данный момент нет на сервере!");
                } else player.sendMessage(Messages.noPermission);
            } else player.sendMessage(this.usageMessage);
        } else Server.getInstance().getLogger().warning(Messages.consoleMessageAboutUsingCommand);
        return false;
    }
}