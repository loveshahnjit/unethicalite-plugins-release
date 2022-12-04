package net.unethicalite.scripts.kebabs.tasks;

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


public class BankKebabs implements ScriptTask {
	private static final WorldPoint BANK_TILE = new WorldPoint(2444, 3083, 0);

	@Override
	public boolean validate() {
		return !Inventory.contains(ItemID.SHARK) || !Inventory.contains(ItemID.COOKED_KARAMBWAN);
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

			TileObject booth = TileObjects.getFirstAt(BANK_TILE, x -> x.hasAction("Use", "Collect"));
			if (booth == null || booth.distanceTo(local) > 20 || !Reachable.isInteractable(booth)) {
				Movement.walkTo(BANK_TILE);
				return 1000;
			}

			booth.interact("Use");
			Bank.depositAllExcept(
					ItemID.DEATH_RUNE,
					ItemID.EARTH_RUNE,
					ItemID.RUNE_POUCH,
					ItemID.ZULANDRA_TELEPORT);

			Bank.withdraw(ItemID.SHARK, 10, Bank.WithdrawMode.ITEM);
			Bank.withdraw(ItemID.COOKED_KARAMBWAN, 10, Bank.WithdrawMode.ITEM);
			Bank.withdraw(ItemID.TELEPORT_TO_HOUSE, 1, Bank.WithdrawMode.ITEM);
			Bank.withdraw(ItemID.PRAYER_POTION4, 2, Bank.WithdrawMode.ITEM);
			Bank.withdraw(ItemID.SUPER_RANGING_4, 1, Bank.WithdrawMode.ITEM);
			Bank.withdraw(ItemID.SUPER_DEFENCE4, 1, Bank.WithdrawMode.ITEM);

			return 3000;
		}
		return 1000;
	}

}
