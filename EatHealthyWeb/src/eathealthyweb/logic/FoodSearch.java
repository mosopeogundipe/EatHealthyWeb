/**
 * 
 */
package eathealthyweb.logic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author DELL
 * Holds food search results from the USDA Food Composition Databases API 
 */
public class FoodSearch {

	@SerializedName("list")
	@Expose
	private List list;

	public List getList() {
	return list;
	}

	public void setList(List list) {
	this.list = list;
	}
	
	public class Item {

		@SerializedName("offset")
		@Expose
		private Integer offset;
		@SerializedName("group")
		@Expose
		private String group;
		@SerializedName("name")
		@Expose
		private String name;
		@SerializedName("ndbno")
		@Expose
		private String ndbno;
		@SerializedName("ds")
		@Expose
		private String ds;
		@SerializedName("manu")
		@Expose
		private String manu;

		public Integer getOffset() {
		return offset;
		}

		public void setOffset(Integer offset) {
		this.offset = offset;
		}

		public String getGroup() {
		return group;
		}

		public void setGroup(String group) {
		this.group = group;
		}

		public String getName() {
		return name;
		}

		public void setName(String name) {
		this.name = name;
		}

		public String getNdbno() {
		return ndbno;
		}

		public void setNdbno(String ndbno) {
		this.ndbno = ndbno;
		}

		public String getDs() {
		return ds;
		}

		public void setDs(String ds) {
		this.ds = ds;
		}

		public String getManu() {
		return manu;
		}

		public void setManu(String manu) {
		this.manu = manu;
		}

		}
	
	public class List {

		@SerializedName("q")
		@Expose
		private String q;
		@SerializedName("sr")
		@Expose
		private String sr;
		@SerializedName("ds")
		@Expose
		private String ds;
		@SerializedName("start")
		@Expose
		private Integer start;
		@SerializedName("end")
		@Expose
		private Integer end;
		@SerializedName("total")
		@Expose
		private Integer total;
		@SerializedName("group")
		@Expose
		private String group;
		@SerializedName("sort")
		@Expose
		private String sort;
		@SerializedName("item")
		@Expose
		private java.util.List<Item> item = null;

		public String getQ() {
		return q;
		}

		public void setQ(String q) {
		this.q = q;
		}

		public String getSr() {
		return sr;
		}

		public void setSr(String sr) {
		this.sr = sr;
		}

		public String getDs() {
		return ds;
		}

		public void setDs(String ds) {
		this.ds = ds;
		}

		public Integer getStart() {
		return start;
		}

		public void setStart(Integer start) {
		this.start = start;
		}

		public Integer getEnd() {
		return end;
		}

		public void setEnd(Integer end) {
		this.end = end;
		}

		public Integer getTotal() {
		return total;
		}

		public void setTotal(Integer total) {
		this.total = total;
		}

		public String getGroup() {
		return group;
		}

		public void setGroup(String group) {
		this.group = group;
		}

		public String getSort() {
		return sort;
		}

		public void setSort(String sort) {
		this.sort = sort;
		}

		public java.util.List<Item> getItem() {
		return item;
		}

		public void setItem(java.util.List<Item> item) {
		this.item = item;
		}

		}
}
