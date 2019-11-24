package yequid.adminCommands;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.data.EntityMetadata;
import cn.nukkit.level.Position;
import cn.nukkit.network.protocol.AddEntityPacket;

final public class API {
    private static API instance;

    private API() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    /**
     * Поджигает игрока
     *
     * @param target Кого поджигать
     */
    public void burn(Player target) {
        burn(target, 60);
    }

    /**
     * Поджигает игрока
     *
     * @param target   Кого поджигать
     * @param fireTime На какое время поджечь
     */
    public void burn(Player target, int fireTime) {
        target.setOnFire(fireTime);
    }

    /**
     * Замораживает игрока
     *
     * @param target Кого заморозили
     */
    public void freeze(Player target) {
        target.setImmobile();
    }

    /**
     * Размораживает игрока
     *
     * @param target Кого размораживает
     */
    public void unfreeze(Player target) {
        target.setImmobile(false);
    }

    /**
     * Бьет игрока молнией
     *
     * @param target По кому ударить молнией
     */
    public void shock(Player target) {
        strike(target.getPosition());
        target.setHealth(target.getHealth() - 5);
        target.setOnFire(5);
    }

    /**
     * Бьет молнией в указанное место
     *
     * @param position Позиция, куда нужно ударить молнией
     */
    public void strike(Position position) {
        AddEntityPacket addEntityPacket = new AddEntityPacket();
        addEntityPacket.type = 93;
        addEntityPacket.entityRuntimeId = Entity.entityCount++;
        addEntityPacket.metadata = new EntityMetadata();
        addEntityPacket.x = (float) position.x;
        addEntityPacket.y = (float) position.y;
        addEntityPacket.z = (float) position.z;
        for (Player target : position.getLevel().getPlayers().values()) {
            target.dataPacket(addEntityPacket);
        }
    }

    /**
     * Возвращает синглтон текущего объекта
     *
     * @return API
     */
    public static API getInstance() {
        if (instance == null) {
            instance = new API();
        }

        return instance;
    }
}
