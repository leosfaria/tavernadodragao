package br.com.tavernadodragao.model;

import java.util.List;

public class GurpsCharactersheet implements Charactersheet{

	private String characterName;
	private String playerName;
	private int points;
	private int unspentPoints;
	private String height;
	private String width;
	private int sizeModifier;
	private String age;
	private String appearance;
	
	private int st;
	private int dx;
	private int iq;
	private int ht;
	private int hp;
	private int currentHp;
	private int will;
	private int per;
	private int fp;
	private int currentFp;
	
//	private List<String> language;
//	private List<String> spoken;
//	private List<String> written;
//	
//	private List<String> advantages;
//	private List<String> disadvantages;
//	
//	private List<String> skills;

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getUnspentPoints() {
		return unspentPoints;
	}

	public void setUnspentPoints(int unspentPoints) {
		this.unspentPoints = unspentPoints;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public int getSizeModifier() {
		return sizeModifier;
	}

	public void setSizeModifier(int sizeModifier) {
		this.sizeModifier = sizeModifier;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAppearance() {
		return appearance;
	}

	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}

	public int getSt() {
		return st;
	}

	public void setSt(int st) {
		this.st = st;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getIq() {
		return iq;
	}

	public void setIq(int iq) {
		this.iq = iq;
	}

	public int getHt() {
		return ht;
	}

	public void setHt(int ht) {
		this.ht = ht;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}

	public int getWill() {
		return will;
	}

	public void setWill(int will) {
		this.will = will;
	}

	public int getPer() {
		return per;
	}

	public void setPer(int per) {
		this.per = per;
	}

	public int getFp() {
		return fp;
	}

	public void setFp(int fp) {
		this.fp = fp;
	}

	public int getCurrentFp() {
		return currentFp;
	}

	public void setCurrentFp(int currentFp) {
		this.currentFp = currentFp;
	}

//	public List<String> getLanguage() {
//		return language;
//	}
//
//	public void setLanguage(List<String> language) {
//		this.language = language;
//	}
//
//	public List<String> getSpoken() {
//		return spoken;
//	}
//
//	public void setSpoken(List<String> spoken) {
//		this.spoken = spoken;
//	}
//
//	public List<String> getWritten() {
//		return written;
//	}
//
//	public void setWritten(List<String> written) {
//		this.written = written;
//	}
//
//	public List<String> getAdvantages() {
//		return advantages;
//	}
//
//	public void setAdvantages(List<String> advantages) {
//		this.advantages = advantages;
//	}
//
//	public List<String> getDisadvantages() {
//		return disadvantages;
//	}
//
//	public void setDisadvantages(List<String> disadvantages) {
//		this.disadvantages = disadvantages;
//	}
//
//	public List<String> getSkills() {
//		return skills;
//	}
//
//	public void setSkills(List<String> skills) {
//		this.skills = skills;
//	}
}
