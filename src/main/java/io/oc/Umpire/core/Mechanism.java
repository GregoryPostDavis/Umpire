package io.oc.Umpire.core;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Switch;
import org.bukkit.inventory.EquipmentSlot;

import static org.bukkit.Bukkit.getLogger;

public class Mechanism {
    private final Location location;
    private final boolean powered;

    Mechanism(Location location, boolean powered){
        this.location = location;
        this.powered = powered;
    }

    public void start(){
        Block block = location.getBlock();


        BlockData data = block.getBlockData();
        Switch sw = (Switch) data;


        sw.setPowered(powered);
        block.setBlockData(sw,true);
        block.getState().update(true);

        EquipmentSlot hand = EquipmentSlot.HAND;

        Block attached = block.getRelative(sw.getFacing().getOppositeFace());
        Material attachedType = attached.getType();
        attached.setType(Material.AIR, false);//FIXME This is a HACK. Also will break for floating levers I think. But maybe floating levers can't exist anyways cause they get updated? Maybe not a hack...
        attached.setType(attachedType, true);
        attached.getState().update(true);
    }
}
