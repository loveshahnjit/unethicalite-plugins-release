package net.unethicalite.scripts.banks.tasks;

//import net.runelite.api.Item;
import net.unethicalite.api.entities.Players;
import net.unethicalite.api.entities.TileObjects;
import net.unethicalite.api.items.Bank;
//import net.unethicalite.api.items.Inventory;
import net.unethicalite.api.movement.Movement;
//import net.unethicalite.api.movement.Reachable;
import net.runelite.api.ItemID;
import net.runelite.api.Player;
import net.runelite.api.TileObject;
import net.runelite.api.coords.WorldPoint;


public class BankItems implements ScriptTask
{
	private static final WorldPoint BANK_TILE = new WorldPoint(2444, 3083, 0);
	@Override
	public boolean validate()
	{
			return true;

	}

	@Override
	public int execute()
	{
		Player local = Players.getLocal();
		TileObject booth = TileObjects.getFirstAt(BANK_TILE, x -> x.hasAction("Use", "Collect"));
		if (booth.distanceTo(local) > 20)
		{
			Movement.walkTo(BANK_TILE);
			booth.interact("Use");
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
		return 1000;
	}
}



