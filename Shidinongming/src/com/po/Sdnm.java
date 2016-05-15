package com.po;

import java.sql.Date;

public class Sdnm {
	
	private int id;
	private String yuanid;
	private String zyqmj;
	private String bczymj;
	private String zdgsynmj;
	private String zysj;
	private String name;
	private String sex;
	private String relation;
	private String idnum;
	private String hktype;
	private String nonghucode;
	private String birthday;
	private String qian;
	public String getQian() {
		return qian;
	}
	public void setQian(String qian) {
		this.qian = qian;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getYuanid() {
		return yuanid;
	}
	public void setYuanid(String yuanid) {
		this.yuanid = yuanid;
	}
	public String getZyqmj() {
		return zyqmj;
	}
	public void setZyqmj(String zyqmj) {
		this.zyqmj = zyqmj;
	}
	public String getBczymj() {
		return bczymj;
	}
	public void setBczymj(String bczymj) {
		this.bczymj = bczymj;
	}
	public String getZdgsynmj() {
		return zdgsynmj;
	}
	public void setZdgsynmj(String zdgsynmj) {
		this.zdgsynmj = zdgsynmj;
	}
	public String getZysj() {
		return zysj;
	}
	public void setZysj(String zysj) {
		this.zysj = zysj;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getHktype() {
		return hktype;
	}
	public void setHktype(String hktype) {
		this.hktype = hktype;
	}
	public String getNonghucode() {
		return nonghucode;
	}
	public void setNonghucode(String nonghucode) {
		this.nonghucode = nonghucode;
	}
	public Sdnm(int id, String yuanid, String zyqmj, String bczymj,
			String zdgsynmj, String zysj, String name, String sex,
			String relation, String idnum, String hktype, String nonghucode,String birthday,String qian) {
		super();
		this.id = id;
		this.yuanid = yuanid;
		this.zyqmj = zyqmj;
		this.bczymj = bczymj;
		this.zdgsynmj = zdgsynmj;
		this.zysj = zysj;
		this.name = name;
		this.sex = sex;
		this.relation = relation;
		this.idnum = idnum;
		this.hktype = hktype;
		this.nonghucode = nonghucode;
		this.birthday=birthday;
		this.qian=qian;
	}
	
	
	
	

}
