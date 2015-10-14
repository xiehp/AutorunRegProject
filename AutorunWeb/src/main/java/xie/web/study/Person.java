package xie.web.study;

import java.io.Serializable;

public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private boolean status;

	public Person() {
		// do nothing
	}

	public Person(int id2, String name2, boolean status2) {
		id = id2;
		name = name2;
		status = status2;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Person)) {
			return false;
		}
		Person value = (Person) obj;
		if (value.id != this.id) {
			return false;
		}
		if (value.name != this.name) {
			return false;
		}
		if (value.status != this.status) {
			return false;
		}

		return true;
	}
	
}
