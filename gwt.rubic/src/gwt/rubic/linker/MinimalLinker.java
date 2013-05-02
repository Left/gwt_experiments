package gwt.rubic.linker;

import java.io.IOException;
import java.net.URL;
import java.util.SortedSet;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.gwt.core.ext.LinkerContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.linker.AbstractLinker;
import com.google.gwt.core.ext.linker.ArtifactSet;
import com.google.gwt.core.ext.linker.CompilationResult;
import com.google.gwt.core.ext.linker.LinkerOrder;
import com.google.gwt.thirdparty.guava.common.io.Resources;

@LinkerOrder(LinkerOrder.Order.PRIMARY)
public class MinimalLinker extends AbstractLinker {

	@Override
	public String getDescription() {
		return "Minimal linker";
	}

	@Override
	public ArtifactSet link(TreeLogger logger, LinkerContext context, ArtifactSet artifacts)
			throws UnableToCompleteException {
		URL url = Resources.getResource(MinimalLinker.class, "Minimal.html");
		try {
			String text = Resources.toString(url, Charsets.UTF_8);
			SortedSet<CompilationResult> compilations = artifacts.find(CompilationResult.class);
			final ArtifactSet newSet = new ArtifactSet(super.link(logger, context, artifacts));
			
			for (CompilationResult r : compilations) {
				String res = 
						"var $stats;\nvar $wnd = window;\nvar $doc = $wnd.document;" + r.getJavaScript()[0] + "$wnd.addEventListener('load', gwtOnLoad, true);";
				//
				newSet.add(emitString(logger, text.replace("$$$MINIMAL_SCRIPT$$$", res), context.getModuleName()+ ".html"));
			}
			
			return newSet;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
	}
}
