package katabank.issa.domain;

/**
 * @author ilakkis
 *
 */
public enum Operation {
	ADDITION("+"), SUBSTRACTION("-");

	public final String label;

	public String getLabel() {
		return label;
	}

	private Operation(String label) {
		this.label = label;
	}
}
