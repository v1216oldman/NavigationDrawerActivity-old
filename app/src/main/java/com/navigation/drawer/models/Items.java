package com.navigation.drawer.models;

/**
 * @author dipenp
 *
 */
public class Items {

	private int itemName;
	private int itemDesc;
	private long iconId;

	public Items(int itemName, int itemDesc, long iconId) {
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.iconId = iconId;
	}

	public int getItemName() {
		return itemName;
	}

	public void setItemName(int itemName) {
		this.itemName = itemName;
	}

	public int getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(int itemDesc) {
		this.itemDesc = itemDesc;
	}

	public long getIconId() {
		return iconId;
	}

	public void setIconId(long iconId) {
		this.iconId = iconId;
	}
}
