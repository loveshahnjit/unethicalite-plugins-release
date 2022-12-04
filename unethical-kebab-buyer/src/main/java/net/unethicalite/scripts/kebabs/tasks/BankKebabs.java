package net.unethicalite.scripts.kebabs.tasks;

import net.runelite.api.Item;
import net.unethicalite.api.entities.Players;
import net.unethicalite.api.entities.TileObjects;
import net.unethicalite.api.items.Bank;
import net.unethicalite.api.items.Inventory;
import net.unethicalite.api.movement.Movement;
import net.unethicalite.api.movement.Reachable;
import net.runelite.api.ItemID;
import net.runelite.api.Player;
import net.runelite.api.TileObject;
import net.runelite.api.coords.WorldPoint;


public class BankKebabs implements ScriptTask{
	private static final WorldPoint BANK_TILE = new WorldPoint(2444, 3083, 0);

	Item food1 = Inventory.getFirst(ItemID.SHARK);
	Item food2 = Inventory.getFirst(ItemID.COOKED_KARAMBWAN);
	@Override
	public boolean validate() {
		if (food2.getQuantity() + food1.getQuantity() < 7 || !Inventory.contains(ItemID.SHARK)
				|| !Inventory.contains(ItemID.COOKED_KARAMBWAN)){
			return true;
		}
		return false;
	}

	@Override
	public int execute() {
		Player local = Players.getLocal();
		if (!Bank.isOpen()) {
			if (!Movement.isRunEnabled()) {
				Movement.toggleRun();
				return 1000;
			}

			if (Movement.isWalking()) {
				return 1000;
			}

			TileObject booth = TileObjects.getFirstAt(BANK_TILE, x -> x.hasAction("Bank", "Collect"));
			if (booth == null || booth.distanceTo(local) > 20 || !Reachable.isInteractable(booth)) {
				Movement.walkTo(BANK_TILE);

			}
			booth.interact("Bank");
			return 3000;
		} else {

			if (food1 == null || food1.getQuantity() < 5) {
				Bank.depositInventory();
				Bank.withdraw(ItemID.ZULANDRA_TELEPORT, 10000, Bank.WithdrawMode.ITEM);
				Bank.withdraw(ItemID.RUNE_POUCH, 1, Bank.WithdrawMode.ITEM);
				Bank.withdraw(ItemID.DEATH_RUNE, 10000, Bank.WithdrawMode.ITEM);
				Bank.withdraw(ItemID.EARTH_RUNE, 10000, Bank.WithdrawMode.ITEM);
				Bank.withdraw(ItemID.SHARK, 10, Bank.WithdrawMode.ITEM);
				Bank.withdraw(ItemID.COOKED_KARAMBWAN, 10, Bank.WithdrawMode.ITEM);
				Bank.withdraw(ItemID.TELEPORT_TO_HOUSE, 1, Bank.WithdrawMode.ITEM);
				Bank.withdraw(ItemID.PRAYER_POTION4, 2, Bank.WithdrawMode.ITEM);
				Bank.withdraw(ItemID.SUPER_RANGING_4, 1, Bank.WithdrawMode.ITEM);
				Bank.withdraw(ItemID.SUPER_DEFENCE4, 1, Bank.WithdrawMode.ITEM);
				return 1000;
			}

			if ((Inventory.contains(ItemID.TANZANITE_FANG) || (Inventory.contains(ItemID.SERPENTINE_VISAGE))
					|| (Inventory.contains(ItemID.MAGIC_FANG)) || (Inventory.contains(ItemID.UNCUT_ONYX)) ||
					(Inventory.contains(ItemID.TANZANITE_MUTAGEN)) || (Inventory.contains(ItemID.MAGMA_MUTAGEN)) ||
					(Inventory.contains(ItemID.PET_SNAKELING)))) {
				Bank.depositAll(ItemID.TANZANITE_FANG);
				Bank.depositAll(ItemID.MAGIC_FANG);
				Bank.depositAll(ItemID.SERPENTINE_VISAGE);
				Bank.depositAll(ItemID.UNCUT_ONYX);
				Bank.depositAll(ItemID.TANZANITE_MUTAGEN);
				Bank.depositAll(ItemID.MAGMA_MUTAGEN);
				Bank.depositAll(ItemID.PET_SNAKELING);
				return 1000;
			}
			return 1000;
		}
	}
}