/**
 * 
 */
package eathealthyweb.logic;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author mosopeogundipe
 *
 */
public class NutrientSearch {

	@SerializedName("report")
	@Expose
	private Report report;

	public Report getReport() {
	return report;
	}

	public void setReport(Report report) {
	this.report = report;
	}
	
	public class Food {

		@SerializedName("ndbno")
		@Expose
		private String ndbno;
		@SerializedName("name")
		@Expose
		private String name;
		@SerializedName("ds")
		@Expose
		private String ds;
		@SerializedName("manu")
		@Expose
		private String manu;
		@SerializedName("ru")
		@Expose
		private String ru;
		@SerializedName("ing")
		@Expose
		private Ing ing;
		@SerializedName("nutrients")
		@Expose
		private List<Nutrient> nutrients = null;

		public String getNdbno() {
		return ndbno;
		}

		public void setNdbno(String ndbno) {
		this.ndbno = ndbno;
		}

		public String getName() {
		return name;
		}

		public void setName(String name) {
		this.name = name;
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

		public String getRu() {
		return ru;
		}

		public void setRu(String ru) {
		this.ru = ru;
		}

		public Ing getIng() {
		return ing;
		}

		public void setIng(Ing ing) {
		this.ing = ing;
		}

		public List<Nutrient> getNutrients() {
		return nutrients;
		}

		public void setNutrients(List<Nutrient> nutrients) {
		this.nutrients = nutrients;
		}

		}
	
	public class Ing {

		@SerializedName("desc")
		@Expose
		private String desc;
		@SerializedName("upd")
		@Expose
		private String upd;

		public String getDesc() {
		return desc;
		}

		public void setDesc(String desc) {
		this.desc = desc;
		}

		public String getUpd() {
		return upd;
		}

		public void setUpd(String upd) {
		this.upd = upd;
		}

		}
	
	public class Measure {

		@SerializedName("label")
		@Expose
		private String label;
		@SerializedName("eqv")
		@Expose
		private Double eqv;
		@SerializedName("eunit")
		@Expose
		private String eunit;
		@SerializedName("qty")
		@Expose
		private Double qty;
		@SerializedName("value")
		@Expose
		private String value;

		public String getLabel() {
		return label;
		}

		public void setLabel(String label) {
		this.label = label;
		}

		public Double getEqv() {
		return eqv;
		}

		public void setEqv(Double eqv) {
		this.eqv = eqv;
		}

		public String getEunit() {
		return eunit;
		}

		public void setEunit(String eunit) {
		this.eunit = eunit;
		}

		public Double getQty() {
		return qty;
		}

		public void setQty(Double qty) {
		this.qty = qty;
		}

		public String getValue() {
		return value;
		}

		public void setValue(String value) {
		this.value = value;
		}

		}
	
	public class Nutrient {

		@SerializedName("nutrient_id")
		@Expose
		private String nutrientId;
		@SerializedName("name")
		@Expose
		private String name;
		@SerializedName("derivation")
		@Expose
		private String derivation;
		@SerializedName("group")
		@Expose
		private String group;
		@SerializedName("unit")
		@Expose
		private String unit;
		@SerializedName("value")
		@Expose
		private String value;
		@SerializedName("measures")
		@Expose
		private List<Measure> measures = null;

		public String getNutrientId() {
		return nutrientId;
		}

		public void setNutrientId(String nutrientId) {
		this.nutrientId = nutrientId;
		}

		public String getName() {
		return name;
		}

		public void setName(String name) {
		this.name = name;
		}

		public String getDerivation() {
		return derivation;
		}

		public void setDerivation(String derivation) {
		this.derivation = derivation;
		}

		public String getGroup() {
		return group;
		}

		public void setGroup(String group) {
		this.group = group;
		}

		public String getUnit() {
		return unit;
		}

		public void setUnit(String unit) {
		this.unit = unit;
		}

		public String getValue() {
		return value;
		}

		public void setValue(String value) {
		this.value = value;
		}

		public List<Measure> getMeasures() {
		return measures;
		}

		public void setMeasures(List<Measure> measures) {
		this.measures = measures;
		}

		}
	
	public class Report {

		@SerializedName("sr")
		@Expose
		private String sr;
		@SerializedName("type")
		@Expose
		private String type;
		@SerializedName("food")
		@Expose
		private Food food;
		@SerializedName("footnotes")
		@Expose
		private List<Object> footnotes = null;

		public String getSr() {
		return sr;
		}

		public void setSr(String sr) {
		this.sr = sr;
		}

		public String getType() {
		return type;
		}

		public void setType(String type) {
		this.type = type;
		}

		public Food getFood() {
		return food;
		}

		public void setFood(Food food) {
		this.food = food;
		}

		public List<Object> getFootnotes() {
		return footnotes;
		}

		public void setFootnotes(List<Object> footnotes) {
		this.footnotes = footnotes;
		}

		}
}
