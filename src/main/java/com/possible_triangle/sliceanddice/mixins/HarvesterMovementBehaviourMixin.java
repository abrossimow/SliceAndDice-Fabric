package com.possible_triangle.sliceanddice.mixins;

import com.possible_triangle.sliceanddice.compat.ModCompat;
import com.simibubi.create.content.contraptions.actors.harvester.HarvesterMovementBehaviour;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import vectorwing.farmersdelight.common.registry.ModItems;

@Mixin(HarvesterMovementBehaviour.class)
public class HarvesterMovementBehaviourMixin {

    @Unique
    private static final ItemStack sliceanddice$TOOL = ModCompat.INSTANCE.ifLoaded(ModCompat.FARMERS_DELIGHT, () -> new ItemStack(ModItems.IRON_KNIFE.get()));

    @ModifyVariable(
            require = 0,
            method = "visitNewPosition(Lcom/simibubi/create/content/contraptions/behaviour/MovementContext;Lnet/minecraft/core/BlockPos;)V",
            at = @At("STORE")
    )
    private ItemStack overwriteDefaultItem(ItemStack stack) {
        if (sliceanddice$TOOL != null) return sliceanddice$TOOL;
        return stack;
    }

}
