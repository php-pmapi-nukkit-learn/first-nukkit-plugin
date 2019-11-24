package adminCommands.commands;

import adminCommands.utils.Messages;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.data.EntityMetadata;
import cn.nukkit.network.protocol.AddEntityPacket;

public class ShockCommand extends Command {
    public ShockCommand() {
        super("shock", "ударить игрока молнией", "§fИспользуйте: §7/shock <тег игрока>");
        getCommandParameters().put("default", new CommandParameter[] {
                new CommandParameter("player", CommandParamType.TARGET, true)
        });
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 1) {
                if(player.hasPermission("adminCommands.shock")) {
                    Player target = Server.getInstance().getPlayer(args[0]);
                    if(target != null) {
                        if(!target.getName().equals(player.getName())) {
                            strike(target);
                            target.setHealth(target.getHealth() - 5);
                            target.setOnFire(5);
                            player.sendMessage("Вы успешно ударили игрока §c" + target.getName() + "§f молнией");
                            target.sendTitle("§l§9LIGHTING");
                            target.sendMessage("Вас ударил молнией игрок §c" + player.getName());
                        } else player.sendMessage("§7Себя бить молнией запрещено!");
                    } else player.sendMessage("Игрока §a" + args[0] + "§f в данный момент нет на сервере!");
                } else player.sendMessage(Messages.noPermission);
            } else player.sendMessage(this.usageMessage);
        } else Server.getInstance().getLogger().warning(Messages.consoleMessageAboutUsingCommand);
        return false;
    }

    private void strike(Player player) {
        AddEntityPacket addEntityPacket = new AddEntityPacket();
        addEntityPacket.type = 93;
        addEntityPacket.entityRuntimeId = Entity.entityCount++;
        addEntityPacket.metadata = new EntityMetadata();
        addEntityPacket.x = (float) player.x;
        addEntityPacket.y = (float) player.y;
        addEntityPacket.z = (float) player.z;
        for(Player target : Server.getInstance().getOnlinePlayers().values()) {
            target.dataPacket(addEntityPacket);
        }
    }
}