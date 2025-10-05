package net.ddns.pray.contact.presentation;

import java.util.List;

public class Page<T> {

	private List<T> content;
	private int currentPage;
	private int totalItems;
	private int totalPages;
	private int pageSize;

	public Page(List<T> content, int currentPage, int totalItems, int totalPages, int pageSize) {
		this.content = content;
		this.currentPage = currentPage;
		this.totalItems = totalItems;
		this.totalPages = totalPages;
		this.pageSize = pageSize;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
