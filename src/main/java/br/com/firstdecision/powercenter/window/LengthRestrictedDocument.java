package br.com.firstdecision.powercenter.window;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public final class LengthRestrictedDocument extends PlainDocument {

	private static final long serialVersionUID = 2299088888201357194L;
	
	private final int limit;

	public LengthRestrictedDocument(int limit) {
		this.limit = limit;
	}

	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		if (str == null)
			return;

		if ((getLength() + str.length()) <= limit && isNumber(str)) {
			super.insertString(offs, str, a);
		}
	}
	
	private boolean isNumber(String digit) {
		return digit.matches("\\d+");
	}
}