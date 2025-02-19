package application.Model;

import java.io.Serializable;

public class Answer implements Serializable, Cloneable {

	String content;
	boolean isRight;

	public Answer(String content, boolean TrueOrFalse) {
		this.content = content;
		this.isRight = TrueOrFalse;
	}

	public String getContent() {
		return this.content;
	}

	public boolean getIsRight() {
		return this.isRight;
	}

	public void setRight() {
		this.isRight = true;
	}

	public void setFalse() {
		this.isRight = false;
	}

	public boolean setContent(String cont) {
		if (checkContent(cont)) {
			this.content = cont;
			return true;
		}
		return false;
	}

	public boolean checkContent(String cont) {
		if (cont.toLowerCase().equals(this.content.toLowerCase())) {
			return false;
		}
		return true;

	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Answer)) {
			return false;
		}
		Answer a = (Answer) obj;
		if (this.content.toLowerCase().equals(a.getContent().toLowerCase())) {
			return true;

		}
		return false;
	}

	public String toString() { // To String - print answer;
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("    Answer: " + this.content + " |  " + this.isRight);

		return sBuffer.toString();
	}

}
