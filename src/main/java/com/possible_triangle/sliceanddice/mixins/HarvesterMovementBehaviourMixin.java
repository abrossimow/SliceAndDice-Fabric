package com.possible_triangle.sliceanddice.mixins;

import com.google.common.base.Suppliers;
import com.possible_triangle.sliceanddice.compat.ModCompat;
import com.simibubi.create.content.contraptions.actors.harvester.HarvesterMovementBehaviour;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.function.Supplier;

@Mixin(HarvesterMovementBehaviour.class)
public class HarvesterMovementBehaviourMixin {

    @Unique
    private static final Supplier<ItemStack> sliceanddice$TOOL = Suppliers.memoize(ModCompat.INSTANCE::getHarvesterTool);

    @ModifyVariable(
            require = 0,
            method = "visitNewPosition(Lcom/simibubi/create/content/contraptions/behaviour/MovementContext;Lnet/minecraft/core/BlockPos;)V",
            at = @At(value = "STORE", ordinal = 0)
    )
    private ItemStack overwriteDefaultItem(ItemStack stack) {
        return sliceanddice$TOOL.get();
    }

}
