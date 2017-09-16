import org.robotframework.javalib.library.AnnotationLibrary;

public class PDFLibrary extends AnnotationLibrary {

	public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

	public PDFLibrary() {
		super("com/github/justinclagg/pdflibrary/keywords/**");
	}

	@Override
	public String getKeywordDocumentation(String keywordName) {
		if (keywordName.equals("__intro__")) {
			return "PDFLibrary is a Robot Framework library for testing PDFs.";
		}
		return super.getKeywordDocumentation(keywordName);
	}

}
