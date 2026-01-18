package modal;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Video {
	private int id;
	private String title;
	private String poster;
	private int views;
	private String link;
	private String descriptions;
	private boolean active;
	private boolean vip;
	private String quality;
	private Date dayupload;
	private int times;
	private int idgenre;
	private int totalFavorites; // Thêm
	private int totalShares; // Thêm

	public Video() {
	}

	public Video(int id, String title, String poster, int views, String link, String descriptions, boolean active,
			boolean vip, String quality, Date dayupload, int times, int idgenre) {
		this.id = id;
		this.title = title;
		this.poster = poster;
		this.views = views;
		this.link = link;
		this.descriptions = descriptions;
		this.active = active;
		this.vip = vip;
		this.quality = quality;
		this.dayupload = dayupload;
		this.times = times;
		this.idgenre = idgenre;
		this.totalFavorites = 0;
		this.totalShares = 0;
	}

	// Constructor với totalFavorites và totalShares
	public Video(int id, String title, String poster, int views, String link, String descriptions, boolean active,
			boolean vip, String quality, Date dayupload, int times, int idgenre, int totalFavorites, int totalShares) {
		this(id, title, poster, views, link, descriptions, active, vip, quality, dayupload, times, idgenre);
		this.totalFavorites = totalFavorites;
		this.totalShares = totalShares;
	}

	public String getFormattedDayUpload() {
		if (this.dayupload == null) {
			return "Đang cập nhật";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.dayupload);
	}

	// Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean getVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public Date getDayupload() {
		return dayupload;
	}

	public void setDayupload(Date dayupload) {
		this.dayupload = dayupload;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getIdgenre() {
		return idgenre;
	}

	public void setIdgenre(int idgenre) {
		this.idgenre = idgenre;
	}

	public int getTotalFavorites() {
		return totalFavorites;
	}

	public void setTotalFavorites(int totalFavorites) {
		this.totalFavorites = totalFavorites;
	}

	public int getTotalShares() {
		return totalShares;
	}

	public void setTotalShares(int totalShares) {
		this.totalShares = totalShares;
	}
}