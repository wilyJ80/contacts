package net.ddns.pray.contact.presentation;

import java.util.List;

public final class Breadcrumbs<T> {

	private List<T> items;

	public Breadcrumbs(List<T> items) {
		this.items = items;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

}
