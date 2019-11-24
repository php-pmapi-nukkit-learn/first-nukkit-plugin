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
        getCommandParameters().put("default", new CommandParameter[]{
                new CommandParameter("player", CommandParamType.TARGET, true)
        });
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Server.getInstance().getLogger().warning(Messages.consoleMessageAboutUsingCommand);
            return false;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("adminCommands.fire")) {
            player.sendMessage(Messages.noPermission);
            return false;
        }

        if (args.length < 1) {
            player.sendMessage(this.usageMessage);
            return false;
        }

        Player target = Server.getInstance().getPlayer(args[0]);
        if (target == null) {
            player.sendMessage("Игрока §a" + args[0] + "§f в данный момент нет на сервере!");
            return false;
        }

        if (target.getName().equals(player.getName())) {
            player.sendMessage("§7Себя поджигать запрещено, иначе будет больно!");
            return false;
        }

        target.setOnFire(60);
        target.sendTitle("§l§cOops...", "§l§fВас подожгли -_-");
        target.sendMessage("Тебя поджег игрок §c" + player.getName());
        player.sendMessage("Ты поджег игрока §c" + target.getName());

        return false;
    }
}
